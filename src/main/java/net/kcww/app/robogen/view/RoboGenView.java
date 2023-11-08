package net.kcww.app.robogen.view;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.upload.FileRejectedEvent;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;
import net.kcww.app.common.view.MainLayout;
import net.kcww.app.robogen.input.entity.UserInput;
import net.kcww.app.robogen.input.service.UserInputService;
import net.kcww.app.robogen.processor.TaskProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

@PageTitle("Robot TestScript Generator")
@Route(value = "robot-gen", layout = MainLayout.class)
@AnonymousAllowed
@Tag("robogen-view")
@JsModule("./views/robogen/robogen-view.ts")
@Slf4j
public class RoboGenView extends LitTemplate implements HasStyle, BeforeEnterObserver {

  private final UserInputService userInputService;
  private final TaskProcessor<UserInput> taskProcessor;

  private final UserInput userInput;

  private boolean featureFileUploaded = false;
  private boolean xmlFileUploaded = false;

  private @Id Upload featureFileUpload;
  private @Id Upload xmlFileUpload;
  private @Id Upload xsdFileUpload;
  private @Id Span maxFeatureFileSizeLabel;
  private @Id Span maxXmlFileSizeLabel;
  private @Id Span maxXsdFileSizeLabel;
  private @Id Button submit;

  public RoboGenView(UserInputService userInputService, TaskProcessor<UserInput> taskProcessor) {
    this.userInputService = userInputService;
    this.taskProcessor = taskProcessor;
    this.userInput = new UserInput();

    addClassName("robogen-view");
    setupUploadComponents();
    registerListeners();
    updateSubmitButtonState();
  }

  private void setupUploadComponents() {
    int maxFileSize = 1024 * 256; // 256 KB
    setupUpload(featureFileUpload, maxFileSize, maxFeatureFileSizeLabel, "application/feature", ".feature");
    setupUpload(xmlFileUpload, maxFileSize, maxXmlFileSizeLabel, "application/xml", ".xml");
    setupUpload(xsdFileUpload, maxFileSize, maxXsdFileSizeLabel, "application/xsd", ".xsd");
  }

  private void setupUpload(Upload upload, int maxFileSize, HasText maxFileSizeLabel, String... fileType) {
    var buffer = new MemoryBuffer();
    upload.setReceiver(buffer);
    upload.setMaxFileSize(maxFileSize);
    upload.setAcceptedFileTypes(fileType);
    var fileExt = fileType[1].substring(1).toUpperCase();
    var i18n = new FileUploadI18N();
    i18n.getAddFiles().setOne("Upload " + fileExt + "...");
    i18n.getDropFiles().setOne("Drop " + fileExt + " file here");
    upload.setI18n(i18n);
    maxFileSizeLabel.setText(maxFileSize / 1024 + i18n.getUnits().getSize().get(1));
  }

  private void registerListeners() {
    addUploadSuccessListener(featureFileUpload, userInput::setFeatureFileName,
      bytes -> {
        userInput.setFeatureFile(bytes);
        featureFileUploaded = true;
        updateSubmitButtonState();
      });

    addUploadSuccessListener(xmlFileUpload, userInput::setXmlFileName,
      bytes -> {
        userInput.setXmlFile(bytes);
        xmlFileUploaded = true;
        updateSubmitButtonState();
      });

    addUploadSuccessListener(xsdFileUpload, userInput::setXsdFileName, userInput::setXsdFile);

    registerFileRemoveListeners();

    featureFileUpload.addFileRejectedListener(this::onFileUploadRejected);
    xmlFileUpload.addFileRejectedListener(this::onFileUploadRejected);
    xsdFileUpload.addFileRejectedListener(this::onFileUploadRejected);

    submit.addClickListener(event -> {
      submit.setEnabled(false);
      userInputService.save(userInput);
      Notification.show("Files uploaded successfully.");

      // TODO: process user inputs
      taskProcessor.process(userInput);
    });
  }

  private void addUploadSuccessListener(Upload upload, Consumer<String> setFileName, Consumer<byte[]> setFileBytes) {
    upload.addSucceededListener(event -> {
      var fileName = event.getFileName();
      var buffer = (MemoryBuffer) event.getSource().getReceiver();
      processUploadedFile(fileName, buffer, setFileName, setFileBytes);
    });
  }

  private void processUploadedFile(String fileName,
                                   MemoryBuffer buffer,
                                   Consumer<String> setFileName,
                                   Consumer<byte[]> setFileBytes) {
    var truncatedFileName = getTruncatedFileName(fileName);
    setFileName.accept(truncatedFileName);

    try (InputStream inputStream = buffer.getInputStream()) {
      byte[] fileContent = inputStream.readAllBytes();
      setFileBytes.accept(fileContent);
    } catch (IOException e) {
      log.error("Error reading file: {}", fileName, e);
      Notification.show("Error reading file: " + fileName);
    }
  }

  private String getTruncatedFileName(String fileName) {
    int fileNameLength = fileName.length();
    if (fileNameLength <= UserInput.MAX_FILE_NAME_LENGTH) return fileName;

    String fileExtension = extractFileExtension(fileName);
    int fileExtensionLength = fileExtension.length();

    String baseName = fileName.substring(0, fileNameLength - fileExtensionLength);
    int maxBaseLength = UserInput.MAX_FILE_NAME_LENGTH - fileExtensionLength;
    return baseName.substring(0, maxBaseLength) + fileExtension;
  }

  private String extractFileExtension(String fileName) {
    int lastDotIndex = fileName.lastIndexOf('.');
    if (lastDotIndex == -1) return "";
    int secondLastDotIndex = fileName.lastIndexOf('.', lastDotIndex - 1);
    if (secondLastDotIndex == -1) return fileName.substring(lastDotIndex);
    return fileName.substring(secondLastDotIndex);
  }

  private void updateSubmitButtonState() {
    submit.setEnabled(featureFileUploaded && xmlFileUploaded);
  }

  private void registerFileRemoveListeners() {
    var eventType = "file-remove";

    featureFileUpload.getElement().addEventListener(eventType, event -> handleFileRemove(
      userInput::setFeatureFileName,
      userInput::setFeatureFile,
      () -> featureFileUploaded = false));

    xmlFileUpload.getElement().addEventListener(eventType, event -> handleFileRemove(
      userInput::setXmlFileName,
      userInput::setXmlFile,
      () -> xmlFileUploaded = false));

    xsdFileUpload.getElement().addEventListener(eventType, event -> handleFileRemove(
      userInput::setXsdFileName,
      userInput::setXsdFile,
      () -> {
      }));
  }

  private void handleFileRemove(Consumer<String> setFileName, Consumer<byte[]> setFileBytes, Runnable onRemoved) {
    setFileName.accept(null);
    setFileBytes.accept(null);
    onRemoved.run();
    Notification.show("File removed.");
  }

  private void onFileUploadRejected(FileRejectedEvent event) {
    String errorMessage = event.getErrorMessage();
    Notification notification = Notification.show(errorMessage, 5000, Notification.Position.MIDDLE);
    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent event) {
  }
}

package net.kcww.app.views.addressbook;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import java.util.Optional;
import net.kcww.app.data.entity.SampleAddress;
import net.kcww.app.data.service.SampleAddressService;
import net.kcww.app.views.MainLayout;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Address Book")
@Route(value = "address-book/:sampleAddressID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
@Tag("address-book-view")
@JsModule("./views/addressbook/address-book-view.ts")
public class AddressBookView extends LitTemplate implements HasStyle, BeforeEnterObserver {

    private final String SAMPLEADDRESS_ID = "sampleAddressID";
    private final String SAMPLEADDRESS_EDIT_ROUTE_TEMPLATE = "address-book/%s/edit";

    // This is the Java companion file of a design
    // You can find the design file inside /frontend/views/
    // The design can be easily edited by using Vaadin Designer
    // (vaadin.com/designer)

    @Id
    private Grid<SampleAddress> grid;

    @Id
    private TextField street;
    @Id
    private TextField postalCode;
    @Id
    private TextField city;
    @Id
    private TextField state;
    @Id
    private TextField country;

    @Id
    private Button cancel;
    @Id
    private Button save;

    private BeanValidationBinder<SampleAddress> binder;

    private SampleAddress sampleAddress;

    private final SampleAddressService sampleAddressService;

    public AddressBookView(SampleAddressService sampleAddressService) {
        this.sampleAddressService = sampleAddressService;
        addClassNames("address-book-view");
        grid.addColumn(SampleAddress::getStreet).setHeader("Street").setSortProperty("street").setAutoWidth(true);
        grid.addColumn(SampleAddress::getPostalCode).setHeader("Postal Code").setSortProperty("postalCode")
                .setAutoWidth(true);
        grid.addColumn(SampleAddress::getCity).setHeader("City").setSortProperty("city").setAutoWidth(true);
        grid.addColumn(SampleAddress::getState).setHeader("State").setSortProperty("state").setAutoWidth(true);
        grid.addColumn(SampleAddress::getCountry).setHeader("Country").setSortProperty("country").setAutoWidth(true);
        grid.setItems(query -> sampleAddressService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEADDRESS_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(AddressBookView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(SampleAddress.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.sampleAddress == null) {
                    this.sampleAddress = new SampleAddress();
                }
                binder.writeBean(this.sampleAddress);
                sampleAddressService.update(this.sampleAddress);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(AddressBookView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> sampleAddressId = event.getRouteParameters().get(SAMPLEADDRESS_ID).map(Long::parseLong);
        if (sampleAddressId.isPresent()) {
            Optional<SampleAddress> sampleAddressFromBackend = sampleAddressService.get(sampleAddressId.get());
            if (sampleAddressFromBackend.isPresent()) {
                populateForm(sampleAddressFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested sampleAddress was not found, ID = %s", sampleAddressId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(AddressBookView.class);
            }
        }
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(SampleAddress value) {
        this.sampleAddress = value;
        binder.readBean(this.sampleAddress);

    }
}

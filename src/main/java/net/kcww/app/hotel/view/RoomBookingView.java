package net.kcww.app.hotel.view;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.Getter;
import net.kcww.app.common.view.MainLayout;
import net.kcww.app.hotel.entity.*;
import net.kcww.app.hotel.service.impl.BookingConfirmationServiceImpl;
import net.kcww.app.hotel.service.impl.CountryServiceImpl;
import net.kcww.app.hotel.service.impl.RoomTypeServiceImpl;
import org.springframework.context.MessageSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@PageTitle("Hotel Room Booking")
@Route(value = "room-booking", layout = MainLayout.class)
@AnonymousAllowed
@Tag("room-booking-view")
@JsModule("./views/roombooking/room-booking-view.ts")
public class RoomBookingView extends LitTemplate implements HasStyle, BeforeEnterObserver {

    private @Id TextField firstName;
    private @Id TextField lastName;
    private @Id TextField email;
    private @Id TextField phone;
    private @Id ComboBox<Country> country;
    private @Id DatePicker checkinDate;
    private @Id DatePicker checkoutDate;
    private @Id IntegerField adults;
    private @Id IntegerField children;
    private @Id Select<RoomType> roomType;
    private @Id RadioButtonGroup<SmokingPref> smokingPref;
    private @Id Checkbox lateCheckout;
    private @Id Checkbox earlyCheckin;
    private @Id Checkbox roomUpgrade;
    private @Id TextField otherRequest;
    private @Id Checkbox termsAgreed;
    private @Id Button submit;
    private @Id Button reset;

    private final BookingConfirmationServiceImpl bookingService;
    private final CountryServiceImpl countryService;
    private final RoomTypeServiceImpl roomTypeService;
    private final MessageSource messageSource;

    @Getter
    private BeanValidationBinder<BookingConfirmation> binder;
    private Binder.Binding<BookingConfirmation, LocalDate> checkinDateBinding;
    private Binder.Binding<BookingConfirmation, LocalDate> checkoutDateBinding;
    private BookingConfirmation booking;
    private List<Country> countries;
    private List<RoomType> roomTypes;

    public RoomBookingView(BookingConfirmationServiceImpl bookingConfirmationService,
                           CountryServiceImpl countryService,
                           RoomTypeServiceImpl roomTypeService,
                           MessageSource messageSource) {
        this.bookingService = bookingConfirmationService;
        this.countryService = countryService;
        this.roomTypeService = roomTypeService;
        this.messageSource = messageSource;

        addClassNames("room-booking-view");
        initializeComponents();
        bindData();
        registerListeners();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
    }

    private void initializeComponents() {
        setupCountryComboBox();
        setupDatePickers();
        setupRoomTypeSelect();
        setupSmokingPrefRadioButtonGroup();
        submit.setEnabled(false);
    }

    private Country getDefaultCountry(List<Country> countries) {
        return countries.stream().filter(c -> c.getIsoCode().equals("TH")).findFirst().orElse(countries.get(0));
    }

    private void setupCountryComboBox() {
        country.setItemLabelGenerator(Country::getName);
        if (countries == null) {
            countries = countryService.list();
        }
        Optional.ofNullable(countries).filter(list -> !list.isEmpty()).ifPresent(list -> {
            country.setItems(list);
            country.setValue(getDefaultCountry(list));
        });
    }

    private void setupDatePickers() {
        var tomorrow = LocalDate.now().plusDays(1);
        var dayAfterTomorrow = tomorrow.plusDays(1);
        checkinDate.setMin(tomorrow);
        checkinDate.setValue(tomorrow);
        checkoutDate.setMin(dayAfterTomorrow);
        checkoutDate.setValue(dayAfterTomorrow);
    }

    private void setupRoomTypeSelect() {
        roomType.setItemLabelGenerator(RoomType::getName);
        if (roomTypes == null) {
            roomTypes = roomTypeService.list();
        }
        Optional.ofNullable(roomTypes).filter(list -> !list.isEmpty()).ifPresent(list -> {
            roomType.setItems(list);
            roomType.setValue(list.get(0));
        });
    }

    private void setupSmokingPrefRadioButtonGroup() {
        smokingPref.setItemLabelGenerator(SmokingPref::getLabel);
        smokingPref.setItems(SmokingPref.values());
        smokingPref.setValue(SmokingPref.NON_SMOKING);
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }

    private void bindData() {
        binder = new BeanValidationBinder<>(BookingConfirmation.class);

        SerializablePredicate<String> nameLength = name -> name.length() <= 50;
        binder.forField(firstName)
                .asRequired(getMessage("room_booking.first_name.mandatory"))
                .withValidator(nameLength, getMessage("room_booking.first_name.size"))
                .bind("user.firstName");

        binder.forField(lastName)
                .asRequired(getMessage("room_booking.last_name.mandatory"))
                .withValidator(nameLength, getMessage("room_booking.last_name.size"))
                .bind("user.lastName");

        binder.forField(email)
                .asRequired(getMessage("room_booking.email.mandatory"))
                .withValidator(new EmailValidator(getMessage("room_booking.email.invalid")))
                .withValidator(email -> email.length() <= 100, getMessage("room_booking.email.max"))
                .bind("user.email");

        binder.forField(phone)
                .asRequired(getMessage("room_booking.phone.mandatory"))
                .withValidator(phone -> phone.length() <= 20, getMessage("room_booking.phone.size"))
                .bind("user.phone");

        binder.forField(country)
                .asRequired(getMessage("room_booking.country.mandatory"))
                .bind("user.country");

        var today = LocalDate.now();
        checkinDateBinding = binder.forField(checkinDate)
                .asRequired(getMessage("room_booking.checkin_date.mandatory"))
                .withValidator(checkin -> checkin.isAfter(today), getMessage("room_booking.checkin_date.min"))
                .withValidator(checkin -> {
                    var checkout = checkoutDate.getValue();
                    return checkout != null && checkin.isBefore(checkout);
                }, getMessage("room_booking.checkin_date.before_checkout"))
                .bind("bookingInfo.checkinDate");

        checkoutDateBinding = binder.forField(checkoutDate)
                .asRequired(getMessage("room_booking.checkout_date.mandatory"))
                .withValidator(checkout -> {
                    var checkin = checkinDate.getValue();
                    return checkin != null && checkout.isAfter(checkin);
                }, getMessage("room_booking.checkout_date.after_checkin"))
                .withValidator(checkout -> checkout.isAfter(today.plusDays(1)), getMessage("room_booking.checkout_date.min"))
                .bind("bookingInfo.checkoutDate");

        SerializablePredicate<Integer> maxGuests = guests -> guests <= 100;
        binder.forField(adults)
                .asRequired(getMessage("room_booking.adults.mandatory"))
                .withValidator(adults -> adults >= 1, getMessage("room_booking.adults.min"))
                .withValidator(maxGuests, getMessage("room_booking.adults.max"))
                .bind("bookingInfo.adults");

        binder.forField(children)
                .asRequired(getMessage("room_booking.children.mandatory"))
                .withValidator(children -> children == null || children >= 0, getMessage("room_booking.children.min"))
                .withValidator(maxGuests, getMessage("room_booking.children.max"))
                .bind("bookingInfo.children");

        binder.forField(roomType)
                .asRequired(getMessage("room_booking.room_type.mandatory"))
                .bind("bookingInfo.roomType");

        binder.forField(smokingPref)
                .asRequired(getMessage("room_booking.smoking_pref.mandatory"))
                .withConverter(SmokingPref.SMOKING::equals,
                        smoking -> smoking ? SmokingPref.SMOKING : SmokingPref.NON_SMOKING)
                .bind("bookingInfo.smokingPref");

        binder.forField(lateCheckout).bind("specialRequest.lateCheckout");
        binder.forField(earlyCheckin).bind("specialRequest.earlyCheckin");
        binder.forField(roomUpgrade).bind("specialRequest.roomUpgrade");

        binder.forField(otherRequest)
                .withValidator(request -> request.length() <= 255, getMessage("room_booking.other_request.size"))
                .bind("specialRequest.others");
    }

    private void validateAndCorrectDate(DatePicker datePicker, LocalDate minimumDate, String messageKey) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate == null || selectedDate.isBefore(minimumDate)) {
            Notification.show(getMessage(messageKey));
            datePicker.setValue(minimumDate);
        }
    }

    private BookingConfirmation newBookingConfirmation() {
        return BookingConfirmation.builder()
                .user(new PersonalDetail())
                .bookingInfo(new BookingInfo())
                .specialRequest(new SpecialRequest())
                .build();
    }

    private void addGuestFieldListener(IntegerField field, int minGuests, String mandatoryMsg, String minMsg, String maxMsg) {
        field.addBlurListener(event -> {
            var maxGuests = 100;
            if (field.isEmpty()) {
                Notification.show(getMessage(mandatoryMsg));
                field.setValue(minGuests);
            } else if (field.getValue() < minGuests) {
                Notification.show(getMessage(minMsg));
                field.setValue(minGuests);
            } else if (field.getValue() > maxGuests) {
                Notification.show(getMessage(maxMsg));
                field.setValue(maxGuests);
            }
        });
    }

    private void registerListeners() {
        checkinDate.addBlurListener(event -> {
            var minDate = LocalDate.now().plusDays(1);
            validateAndCorrectDate(checkinDate, minDate, "room_booking.checkin_date.min");
            checkoutDateBinding.validate();
        });

        checkoutDate.addBlurListener(event -> {
            var minDate = LocalDate.now().plusDays(2);
            validateAndCorrectDate(checkoutDate, minDate, "room_booking.checkout_date.min");
            checkinDateBinding.validate();
        });

        addGuestFieldListener(adults, 1, "room_booking.adults.mandatory",
                "room_booking.adults.min", "room_booking.adults.max");

        addGuestFieldListener(children, 0, "room_booking.children.mandatory",
                "room_booking.children.min", "room_booking.children.max");

        submit.addClickListener(e -> {
            try {
                if (!binder.validate().isOk()) {
                    Notification.show(getMessage("room_booking.error.invalidation"));
                    return;
                }
                if (booking == null) {
                    booking = newBookingConfirmation();
                    booking.getBookingInfo().setUser(booking.getUser());
                }
                binder.writeBean(booking);
                bookingService.save(booking);
                Notification.show(getMessage("room_booking.success.saving"));
                resetForm();
//      } catch (ObjectOptimisticLockingFailureException ex) {
//        var notification = Notification.show("Error saving the data. The record has updated before saving.");
//        notification.setPosition(Notification.Position.MIDDLE);
//        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//      } catch (ValidationException validationException) {
//        Notification.show("Failed to save the data. Please check that all inputs are valid.");
            } catch (Exception ex) {
                Notification.show(getMessage("room_booking.error.saving"));
            }
        });

        reset.addClickListener(e -> {
            resetForm();
            Notification.show(getMessage("room_booking.reset"));
        });

        termsAgreed.addValueChangeListener(event -> submit.setEnabled(event.getValue() && binder.isValid()));

        binder.addStatusChangeListener(event -> {
            submit.setEnabled(termsAgreed.getValue() && binder.isValid());
        });
    }

    private void resetForm() {
        binder.readBean(null);
        termsAgreed.clear();

        country.setValue(getDefaultCountry(countries));

        var tomorrow = LocalDate.now().plusDays(1);
        checkinDate.setValue(tomorrow);
        checkoutDate.setValue(tomorrow.plusDays(1));

        adults.setValue(1);
        children.setValue(0);
        roomType.setValue(roomTypes.get(0));
        smokingPref.setValue(SmokingPref.NON_SMOKING);

        List.<HasValidation>of(checkinDate, checkoutDate).forEach(field -> {
            field.setErrorMessage(null);
            field.setInvalid(false);
        });

        firstName.focus();
    }

}
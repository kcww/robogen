package net.kcww.app.hotel.view;

import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import net.kcww.app.common.view.MainLayout;
import net.kcww.app.hotel.entity.BookingConfirmation;
import net.kcww.app.hotel.service.impl.BookingConfirmationServiceImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@PageTitle("Hotel Room Booking Management")
@Route(value = "room-booking-management/:bookingID?/:action?(edit)", layout = MainLayout.class)
@AnonymousAllowed
@Tag("room-booking-list-view")
@JsModule("./views/roombookinglist/room-booking-list-view.ts")
public class RoomBookingListView extends LitTemplate implements HasStyle, BeforeEnterObserver {

    private final String BOOKING_ID = "bookingID";
    private final String BOOKING_EDIT_ROUTE_TEMPLATE = "room-booking-management/%s/edit";

    private @Id RoomBookingView bookingView;
    private @Id Grid<BookingConfirmation> grid;

    private final BookingConfirmationServiceImpl service;
    private BookingConfirmation booking;

    public RoomBookingListView(BookingConfirmationServiceImpl service) {
        this.service = service;
        addClassNames("room-booking-list-view");

        grid.addColumn(BookingConfirmation::getCreatedAt)
                .setHeader("Created At")
                .setSortProperty("createdAt")
                .setAutoWidth(true);
        grid.addColumn(booking -> booking.getUser().getFirstName())
                .setHeader("First Name")
                .setSortProperty("user.firstName")
                .setAutoWidth(true);
        grid.addColumn(booking -> booking.getUser().getLastName())
                .setHeader("Last Name")
                .setSortProperty("user.lastName")
                .setAutoWidth(true);
        grid.addColumn(booking -> booking.getUser().getEmail())
                .setHeader("Email")
                .setSortProperty("user.email")
                .setAutoWidth(true);
        grid.addColumn(booking -> booking.getUser().getPhone())
                .setHeader("Phone")
                .setSortProperty("user.phone")
                .setAutoWidth(true);

        grid.setItems(query -> service.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query))).stream());

        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(BOOKING_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(RoomBookingListView.class);
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> bookingID = event.getRouteParameters().getLong(BOOKING_ID);
        if (bookingID.isPresent()) {
            Optional<BookingConfirmation> bookingFromBackend = service.get(bookingID.get());
            if (bookingFromBackend.isPresent()) {
                populateForm(bookingFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested booking was not found, ID = %d", bookingID.get()), 3000,
                        Notification.Position.BOTTOM_START);
                refreshGrid();
                event.forwardTo(RoomBookingListView.class);
            }
        } else {
            clearForm();
        }
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(BookingConfirmation booking) {
        this.booking = booking;
        bookingView.getBinder().readBean(this.booking);
    }

}

package net.kcww.app.hotel.helper;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import net.kcww.app.hotel.entity.*;
import net.kcww.app.hotel.repository.BookingConfirmationRepository;
import net.kcww.app.hotel.repository.CountryRepository;
import net.kcww.app.hotel.repository.RoomTypeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Random;

@Service
@Profile("!test")
@RequiredArgsConstructor
public final class DatabaseSeeder {

    private final BookingConfirmationRepository bookingConfirmationRepository;
    private final CountryRepository countryRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public void seedDatabase() {
        List<Country> countries = countryRepository.findAll();
        List<RoomType> roomTypes = roomTypeRepository.findAll();

        for (int i = 0; i < 100; i++) {
            Country country = getRandomElement(countries);
            RoomType roomType = getRandomElement(roomTypes);
            PersonalDetail personalDetail = createPersonalDetail(country);
            BookingInfo bookingInfo = createBookingInfo(personalDetail, roomType);
            SpecialRequest specialRequest = createSpecialRequest();
            createBookingConfirmation(personalDetail, bookingInfo, specialRequest);
        }
    }

    private <T> T getRandomElement(List<T> list) {
        return list.get(random.nextInt(list.size()));
    }

    private PersonalDetail createPersonalDetail(Country country) {
        PersonalDetail personalDetail = new PersonalDetail();

        // Ensure first name is <= 50 characters
        String firstName = faker.name().firstName();
        if (firstName.length() > 50) {
            firstName = firstName.substring(0, 50);
        }
        personalDetail.setFirstName(firstName);

        // Ensure last name is <= 50 characters
        String lastName = faker.name().lastName();
        if (lastName.length() > 50) {
            lastName = lastName.substring(0, 50);
        }
        personalDetail.setLastName(lastName);

        // Ensure email is <= 100 characters
        String email = faker.internet().emailAddress();
        if (email.length() > 100) {
            // Adjust the local part of the email to ensure overall length <= 100
            email = email.substring(0, 100 - email.split("@")[1].length() - 1) + "@" + email.split("@")[1];
        }
        personalDetail.setEmail(email);

        // Ensure phone number is <= 20 characters
        String phoneNumber = faker.phoneNumber().phoneNumber();
        if (phoneNumber.length() > 20) {
            phoneNumber = phoneNumber.substring(0, 20);
        }
        personalDetail.setPhone(phoneNumber);

        personalDetail.setCountry(country);
        return personalDetail;
    }

    private BookingInfo createBookingInfo(PersonalDetail personalDetail, RoomType roomType) {
        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setUser(personalDetail);
        bookingInfo.setCheckinDate(faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        bookingInfo.setCheckoutDate(faker.date().future(60, 30, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        bookingInfo.setAdults(faker.number().numberBetween(1, 5));
        bookingInfo.setChildren(faker.number().numberBetween(0, 5));
        bookingInfo.setSmokingPref(faker.bool().bool());
        bookingInfo.setRoomType(roomType);
        return bookingInfo;
    }

    private SpecialRequest createSpecialRequest() {
        SpecialRequest specialRequest = new SpecialRequest();
        specialRequest.setLateCheckout(faker.bool().bool());
        specialRequest.setEarlyCheckin(faker.bool().bool());
        specialRequest.setRoomUpgrade(faker.bool().bool());
        specialRequest.setOthers(faker.lorem().characters(255));
        return specialRequest;
    }

    private void createBookingConfirmation(PersonalDetail personalDetail, BookingInfo bookingInfo, SpecialRequest specialRequest) {
        BookingConfirmation bookingConfirmation = new BookingConfirmation();
        bookingConfirmation.setUser(personalDetail);
        bookingConfirmation.setBookingInfo(bookingInfo);
        bookingConfirmation.setSpecialRequest(specialRequest);
        bookingConfirmationRepository.save(bookingConfirmation);
    }
}


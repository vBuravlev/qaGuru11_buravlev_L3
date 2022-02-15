package guru.qa.tests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

import static guru.qa.utils.RandomUtils.getRandomString;

public class TestData {

    //configuration parameters

    //    Faker faker = new Faker();
    static Faker faker = new Faker(new Locale("ru"));
    public static String firstName = faker.name().firstName();
    public static String userEmail = faker.internet().emailAddress(getRandomString(10));
    public static String lastName = faker.name().lastName();
    public static String currentAddress = faker.rickAndMorty().quote();
    public static String userNumber = faker.phoneNumber().subscriberNumber(10);
    static Date datePast = new Date(01, 00, 01);
    static Date dateFuture = new Date(200, 11, 31);
    static Date newDate = faker.date().between(datePast, dateFuture);
    static SimpleDateFormat formatterMonth = new SimpleDateFormat("LLLL", Locale.ENGLISH);
    public static String calendarMonth = formatterMonth.format(newDate);
    static SimpleDateFormat formatterDay = new SimpleDateFormat("dd", Locale.ENGLISH);
    public static String calendarDay = formatterDay.format(newDate);
    static SimpleDateFormat formatterYear = new SimpleDateFormat("YYYY", Locale.ENGLISH);
    public static String calendarYear = formatterYear.format(newDate);

    public static String

            subjectsFullText = "Maths",
            subjectsShortText = "Hi",
            subjectsShortTextSelect = "History",
            genderRoleName = "Male",
            path = "src/test/resources/",
            fileName = "History.PNG",
            textModalWindow = "Thanks for submitting the form",
            stateName = "NCR",
            cityName = "Delhi";
    public static String[] hobbiesUser = new String[]{"Reading", "Sports", "Music"};
}

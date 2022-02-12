package guru.qa.tests;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class StudentRegistrationFormTests {

    //configuration parameters
    static String urlStudentRegistrationForm = "https://demoqa.com/automation-practice-form";
    static String sizeBrowser = "1920x1080";
    static String firstName = "StudentFirstName";
    static String lastName = "StudentLastName";
    static String userEmail = "email@email.com";
    static String userNumber = "8800123456";  // number of characters = 10
    static String subjectsFullText = "Maths";
    static String subjectsShortText = "Hi";
    static String subjectsShortTextSelect = "History";
    static String calendarYear = "2023"; // "yyyy" format
    static String calendarMonth = "May"; // number of characters = 3
    static String calendarDay = "10";  // "dd" format
    static String genderRoleName = "Male"; //"Male", "Female", "Other"
    static String fileName = "History.PNG";
    static String currentAddress = "Moscow";
    String[] hobbiesUser = new String[]{"Reading", "Sports", "Music"};  //"Reading", "Sports", "Music"
    static String stateName = "NCR";
    static String cityName = "Delhi";


    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = sizeBrowser;
    }

    @Test
    void successTest() {
        open(urlStudentRegistrationForm);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);

        $x("//label[text()='" + genderRoleName + "']").click();

        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(calendarMonth);
        $(".react-datepicker__year-select").selectOption(calendarYear);
        $(".react-datepicker__day--0" + calendarDay).click();

        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subjectsFullText).pressEnter();
        $("#subjectsInput").click();
        $("#subjectsInput").setValue(subjectsShortText);
        $(byText(subjectsShortTextSelect)).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/" + fileName));

        for (String user : hobbiesUser) {
            $x("//label[text()='" + user + "']").click();
        }

        $("#currentAddress").setValue(currentAddress);

        $("#state").click();
        $(byText(stateName)).click();

        $("#city").click();
        $(byText(cityName)).click();

        $("#submit").click();


        //checking the results in the registration form
        $x("//td[contains(.,'Student Name')]/following-sibling::td").shouldHave(text(firstName + " " + lastName));
        $x("//td[contains(.,'Student Email')]/following-sibling::td").shouldHave(text(userEmail));
        $x("//td[contains(.,'Gender')]/following-sibling::td").shouldHave(text(genderRoleName));
        $x("//td[contains(.,'Mobile')]/following-sibling::td").shouldHave(text(userNumber));
        $x("//td[contains(.,'Date of Birth')]/following-sibling::td").shouldHave(text(calendarDay + " " + calendarMonth + "," + calendarYear));
        $x("//td[contains(.,'Subjects')]/following-sibling::td").shouldHave(text(subjectsFullText));
        $x("//td[contains(.,'Subjects')]/following-sibling::td").shouldHave(text(subjectsShortTextSelect));

        for (String user : hobbiesUser) {
            $x("//td[contains(.,'Hobbies')]/following-sibling::td").shouldHave(text(user));
        }

        $x("//td[contains(.,'Picture')]/following-sibling::td").shouldHave(text(fileName));
        $x("//td[contains(.,'Address')]/following-sibling::td").shouldHave(text(currentAddress));
        $x("//td[contains(.,'State and City')]/following-sibling::td").shouldHave(text(stateName + " " + cityName));

        $("#closeLargeModal").click();
        $("#closeLargeModal").shouldNotBe(visible);

        //registration form cleanup check
        $("#firstName").setValue("");
        $("#lastName").setValue("");
        $("#userEmail").setValue("");
        $("#userNumber").setValue("");
        $("#subjectsInput").setValue("");
        $("#currentAddress").setValue("");

        $("#submit").click();
        $("#closeLargeModal").shouldNotBe(visible);

    }
}

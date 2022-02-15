package guru.qa.tests;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.RegistrationPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static guru.qa.tests.TestData.*;
import static java.lang.String.format;


public class StudentRegistrationFormElementCollectionTests extends TestBase {
    //import
    private RegistrationPage registrationPage = new RegistrationPage();

    Map<String, String> expectedData = new HashMap<String, String>() {{
        put("Student Name", firstName + " " + lastName);
        put("Student Email", userNumber);
        put("Gender", genderRoleName);
    }};

//    @Test
//    void successTest() {
//        //input data in form
//        registrationPage.openPage()
//                .setFirstName(firstName)
//                .setLastName(lastName)
//                .setGender(genderRoleName)
//                .setEmailName(userEmail)
//                .setUserNumber(userNumber)
//                .setCurrentAddress(currentAddress)
//                .setHobbiesUser(hobbiesUser)
//                .uploadPicture(path, fileName)
//                .setSubjectsFull(subjectsFullText)
//                .setSubjectsShort(subjectsShortText, subjectsShortTextSelect)
//                .selectState(stateName)
//                .selectCity(cityName)
//                .setBirthDate(calendarDay, calendarMonth, calendarYear)
//                .openResultDataForm(textModalWindow);
//
//        //checking the results in the registration form
//        registrationPage
//                .checkForm("Student Name", firstName + " " + lastName)
//                .checkForm("Student Email", userEmail)
//                .checkForm("Gender", genderRoleName)
//                .checkForm("Mobile", userNumber)
//                .checkForm("Date of Birth", calendarDay + " " + calendarMonth + "," + calendarYear)
//                .checkForm("Subjects", subjectsFullText)
//                .checkForm("Subjects", subjectsShortTextSelect)
//                .checkFormIteration("Hobbies", hobbiesUser)
//                .checkForm("Picture", fileName)
//                .checkForm("Address", currentAddress)
//                .checkForm("State and City", stateName + " " + cityName)
//                .closeResultDataForm();

    @Test
    void successfulRegistrationWithSoftAssertsTest() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(userEmail);
        $("[name=gender][value=Other]").parent().click();
        $("#userNumber").val("1231231231");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("2005");
        $(".react-datepicker__day--028:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").val("Math").pressEnter();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("./img/1.png");
        $("#currentAddress").val("Qa guru street 7");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));

        ElementsCollection lines = $$(".table-responsive tbody tr").snapshot();

        SoftAssertions softly = new SoftAssertions();

        for (SelenideElement line : lines) {
            String key = line.$("td").text(); // Student Name
            String expectedValue = expectedData.get(key);
            String actualValue = line.$("td", 1).text();

            softly.assertThat(actualValue)
                    .as(format("Result in line %s was %s, but expected %s", key, actualValue, expectedValue))
                    .isEqualTo(expectedValue);
        }
        softly.assertAll();
    }
}


package guru.qa.tests;


import com.google.gson.internal.bind.util.ISO8601Utils;
import guru.qa.pages.RegistrationPage;
import guru.qa.utils.RandomUtils;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.util.Date;

import static guru.qa.tests.TestData.*;
import static guru.qa.utils.RandomUtils.*;


public class StudentRegistrationFormRandomUtilsTests extends TestBase {
    //import
    private RegistrationPage registrationPage = new RegistrationPage();
    LocalDate date = LocalDate.now();


    String
            firstName = getRandomString(10),
            lastName = getRandomString(10),
            userEmail = getRandomEmail(),
            userNumber = getRandomPhone(),
            currentAddress = getRandomString(10)+" "+getRandomString(10),
            calendarDay = Integer.toString(getRandomInt(1, 28)),
            calendarYear = Integer.toString(date.getYear() - getRandomInt(14, 130));

    @Test
    void successTest() {
        System.out.println(calendarYear);
        //input data in form
        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(genderRoleName)
                .setEmailName(userEmail)
                .setUserNumber(userNumber)
                .setCurrentAddress(currentAddress)
                .setHobbiesUser(hobbiesUser)
                .uploadPicture(path, fileName)
                .setSubjectsFull(subjectsFullText)
                .setSubjectsShort(subjectsShortText, subjectsShortTextSelect)
                .selectState(stateName)
                .selectCity(cityName)
                .setBirthDate(calendarDay, calendarMonth, calendarYear)
                .openResultDataForm(textModalWindow);

        //checking the results in the registration form
        registrationPage
                .checkForm("Student Name", firstName + " " + lastName)
                .checkForm("Student Email", userEmail)
                .checkForm("Gender", genderRoleName)
                .checkForm("Mobile", userNumber)
                .checkForm("Date of Birth", calendarDay + " " + calendarMonth + "," + calendarYear)
                .checkForm("Subjects", subjectsFullText)
                .checkForm("Subjects", subjectsShortTextSelect)
                .checkFormIteration("Hobbies", hobbiesUser)
                .checkForm("Picture", fileName)
                .checkForm("Address", currentAddress)
                .checkForm("State and City", stateName + " " + cityName)
                .closeResultDataForm();

    }
}

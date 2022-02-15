package guru.qa.tests;


import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;


public class StudentRegistrationFormWithTestDataTests extends TestBase {
    //import
    private RegistrationPage registrationPage = new RegistrationPage();


    @Test
    void successTest() {

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

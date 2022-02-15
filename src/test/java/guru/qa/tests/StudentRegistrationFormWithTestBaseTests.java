package guru.qa.tests;


import com.codeborne.selenide.Configuration;
import guru.qa.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class StudentRegistrationFormWithTestBaseTests extends TestBase {
    //import
    private RegistrationPage registrationPage = new RegistrationPage();

    //configuration parameters
    static String firstName = "StudentFirstName";
    static String lastName = "StudentLastName";
    static String userEmail = "email@email.com";
    static String userNumber = "8800123456";  // number of characters = 10
    static String subjectsFullText = "Maths";
    static String subjectsShortText = "Hi";
    static String subjectsShortTextSelect = "History";
    static String calendarYear = "1987"; // "yyyy" format
    static String calendarMonth = "November"; // number of characters = 3
    static String calendarDay = "14";  // "dd" format
    static String genderRoleName = "Male"; //"Male", "Female", "Other"
    static String path = "src/test/resources/";
    static String fileName = "History.PNG";
    static String currentAddress = "Novosibirsk";
    static String textModalWindow = "Thanks for submitting the form";
    String[] hobbiesUser = new String[]{"Reading", "Sports", "Music"};  //"Reading", "Sports", "Music"
    static String stateName = "NCR";
    static String cityName = "Delhi";


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

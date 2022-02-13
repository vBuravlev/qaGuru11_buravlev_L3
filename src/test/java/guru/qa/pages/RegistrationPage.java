package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.Calendar;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;


public class RegistrationPage {
    private Calendar calendarComponent = new Calendar();

    private SelenideElement headerTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderRoleSelect = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            uploadPictureInput = $("#uploadPicture"),
            hobbiesUserSelect = $("#hobbiesWrapper"),
            currentAddressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            stateSelectList = $("#stateCity-wrapper"),
            citySelect = $("#city"),
            citySelectList = $("#stateCity-wrapper"),
            getButtonDataFormOpen = $("#submit"),
            resultTables = $(".table-responsive"),
            getButtonDataFormClose = $("#closeLargeModal"),
            textModalWindow = $("#example-modal-sizes-title-lg");

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        headerTitle.shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmailName(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String gender) {
        genderRoleSelect.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setBirthDate(String day, String month, String year) {
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationPage setSubjectsFull(String inputText) {
        subjectsInput.click();
        subjectsInput.setValue(inputText).pressEnter();
        return this;
    }

    public RegistrationPage setSubjectsShort(String inputText, String listText) {
        subjectsInput.click();
        subjectsInput.setValue(inputText);
        $(byText(listText)).click();
        return this;
    }

    public RegistrationPage uploadPicture(String path, String fileName) {
        uploadPictureInput.uploadFile(new File(path + fileName));
        return this;
    }

    public RegistrationPage setHobbiesUser(String[] hobbiesUser) {
        for (String user : hobbiesUser) {
            hobbiesUserSelect.$(byText(user)).click();
        }
        return this;
    }

    public RegistrationPage setCurrentAddress(String Address) {
        currentAddressInput.setValue(Address);
        return this;
    }

    public RegistrationPage selectState(String state) {
        stateSelect.click();
        stateSelectList.$(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        citySelect.click();
        citySelectList.$(byText(city)).click();
        return this;
    }

    public RegistrationPage checkForm(String contentLable, String contentValues) {
        resultTables.$(byText(contentLable)).parent().shouldHave(text(contentValues));
        return this;
    }

    public RegistrationPage checkFormIteration(String contentLable, String[] contentValues) {
        for (String value : contentValues) {
            resultTables.$(byText(contentLable)).parent().shouldHave(text(value));
        }
        return this;
    }

    public RegistrationPage openResultDataForm(String textModal) {
        getButtonDataFormOpen.click();
        textModalWindow.shouldHave(text(textModal));
        return this;
    }


    public RegistrationPage closeResultDataForm() {
        getButtonDataFormClose.click();
        getButtonDataFormClose.shouldNotBe(visible);
        return this;
    }


}

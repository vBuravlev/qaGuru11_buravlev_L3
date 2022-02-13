package guru.qa.pages.components;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Calendar {
    private static String dayCalendar;
    private SelenideElement calendarSelect = $("#dateOfBirthInput");
    private SelenideElement monthSelect = $(".react-datepicker__month-select");
    private SelenideElement yearSelect = $(".react-datepicker__year-select");

    public Calendar setDate (String day, String month, String year) {
        calendarSelect.click();
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        $(".react-datepicker__day--0" + day).click();
        return this;
    }
}

package qa.guru.firstLesson;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests {

    @BeforeAll
    static void beforeAll () {
        Configuration.browserSize="1920x1080";
    }

    @Test
    void successTest() {
        open("https://demoqa.com/text-box");
        $("#userName").setValue("Alex");
        $("#userEmail").setValue("email@email.com");
        $("#currentAddress").setValue("Some adress");
        $("#permanentAddress").setValue("Another addres");
        $("#submit").click();
        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text("Alex"));
        $("#output #email").shouldHave(text("email@email.com"));
        $("#output #currentAddress").shouldHave(text("Some adress"));
        $("#output #permanentAddress").shouldHave(text("Another addres"));

    }


}

package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class BrowserstackTests extends TestBase {

    @Test
    @Tag("browserstack")
    @DisplayName("Verify Successful Search Results in Wikipedia App")
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @Tag("browserstack")
    @DisplayName("Verify Error Message for Unsuccessful Search in Wikipedia App")
    void unsuccessfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Java");
        });
        step("Open article", () -> {
            $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click();
        });
        step("Checking for error text", () -> {
            $(id("org.wikipedia.alpha:id/view_wiki_error_text")).shouldBe(visible);
        });
    }

    @Test
    @Tag("browserstack")
    @DisplayName("Checking the presence of the Log in button")
    void checkLogInButtonTest() {

        step("Click on the navigation button", () -> {
            $(id("org.wikipedia.alpha:id/menu_overflow_button")).click();
        });

        step("Check the presence of the Log in to Wikipedia button", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_account_name")).shouldHave(text("Log in to Wikipedia"));
        });

        step("Click on the Log in to Wikipedia button", () -> {
            $(id("org.wikipedia.alpha:id/explore_overflow_account_name")).click();
        });

        step("Check the presence of the Log in button on the opened authentication form", () -> {
            $(id("org.wikipedia.alpha:id/login_button")).shouldHave(text("Log in"));
        });
    }
}

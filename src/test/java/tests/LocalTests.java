package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class LocalTests extends TestBase {


    SelenideElement titleText = $(id("org.wikipedia.alpha:id/primaryTextView")),
            forwardButton = $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")),
            languageButton = $(id("org.wikipedia.alpha:id/addLanguageButton")),
            secondaryText = $(id("org.wikipedia.alpha:id/secondaryTextView"));

    @Test
    @Tag("local")
    @DisplayName("Проверка стартовых страниц в википедии")
    void wikiTest() {

        step("Проверяем заголовок первой странички", () -> {
            step("Проверяем текст заголовка первой страницы", () -> {
                titleText.shouldHave(text("The Free Encyclopedia"));
            });
            step("Проверка кнопки добавления языка текста", () -> {
                languageButton.shouldBe(visible);
            });
            step("Кликаем на кнопку Вперед для перехода на вторую страничку", () ->
                forwardButton.click());

        });
        step("Проверяем заголовок второй странички", () -> {
            step("Проверяем текст заголовка второй страницы", () -> {
                titleText.shouldHave(text("New ways to explore"));
            });
            step("Проверка видимости подзаголовка", () -> {
                secondaryText.shouldBe(visible);
            });
            step("Кликаем на кнопку Вперед для перехода на третью страничку", () ->
                forwardButton.click());

        });
        step("Проверяем заголовок третьей странички", () -> {
            step("Проверяем текст заголовка третьей страницы", () -> {
                titleText.shouldHave(text("Reading lists with sync"));
            });
            step("Проверяем видимость текста подзаголовка", () -> {
                secondaryText.shouldBe(visible);
            });
            step("Кликаем на кнопку Вперед для перехода на четвертую страничку", () ->
                forwardButton.click());

        });
        step("Проверяем заголовок четвертой странички", () -> {
            step("Проверяем текст заголовка четвертой страницы", () -> {
                titleText.shouldHave(text("Send anonymous data"));
            });
            step("Проверяем видимость текста подзаголовка", () -> {
                secondaryText.shouldBe(visible);
            });
        });

    }
}



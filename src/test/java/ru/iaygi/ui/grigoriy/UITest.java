package ru.iaygi.ui.grigoriy;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Owner("galyamskiy")
@Severity(NORMAL)
@Tag("ui_test")
@Epic("homework")
@Feature("Обучение")
public class UITest {

    @BeforeAll
    public static void init() {}

    @AfterEach
    public void clear() {}

    @AfterAll
    public static void tearDown() {}

    @Test
    @Tag("UI")
    @DisplayName("Проверка сайта ГПН")
    @Description("Проверить условия поступления на стажировку")
    public void checkInternshipPage() {

        step("Открыть страницу с вакансиями", () -> {
            open("https://career.gazprom-neft.ru/");
        });

        step("Перейти по ссылки со стажировками", () -> {
            $(byLinkText("ПОДРОБНЕЕ")).scrollTo().click();
        });

        step("Проверить условия поступления на стажировку", () -> {
            $("#heading-kto-mozhet-postupit-na-stazhirovku").click();
            $("#collapse-101").shouldHave(text("Студент 4 курса бакалавриата"));
        });
    }
}

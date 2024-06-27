package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthPage extends BasePage {
    @FindBy(css = "#userSelect [value='2']")
    private WebElement userSelect;
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    public AuthPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Вход в аккаунт Harry Potter")
    public AccountPage selectUserHarryPotter() {
        userSelect.click();
        loginButton.click();
        return new AccountPage();
    }
}

package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

public class MainPage extends BasePage {
    @FindBy(css = "[ng-click='customer()']")
    private WebElement customerLoginButton;
    @FindBy(css = "[ng-click='manager()']")
    private WebElement managerLoginButton;

    public MainPage() {
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver, this);
    }

    @Step("Выбор роли пользователя")
    public AuthPage choosingRoleCustomer() {
        customerLoginButton.click();
        return new AuthPage();
    }

    @Step("Выбор роли менеджера")
    public AuthPage choosingRoleManager() {
        managerLoginButton.click();
        return new AuthPage();
    }

}
package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage{
    @FindBy(css = "[ng-click='customer()']")
    private By customerLoginButton;
    @FindBy(css = "[ng-click='manager()']")
    private By managerLoginButton;

    public MainPage(){
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login;");
        PageFactory.initElements(driver,this);
    }

}

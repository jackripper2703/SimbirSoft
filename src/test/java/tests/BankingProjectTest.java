package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class BankingProjectTest extends TestBase {


    @Test
    public void startTest() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login;");
        driver.findElement(By.cssSelector("[ng-click='customer()']")).click();
        Thread.sleep(120000);

    }
}



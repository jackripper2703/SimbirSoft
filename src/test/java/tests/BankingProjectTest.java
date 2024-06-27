package tests;

import core.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class BankingProjectTest extends BaseTest {


    @Test
    public void startTest() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login;");
        driver.findElement(By.cssSelector("[ng-click='customer()']")).click();
        driver.findElement(By.cssSelector("#userSelect [value='2']")).click();
        driver.findElement(By.cssSelector("[type='submit']")).click();
        driver.findElement(By.cssSelector("[ng-click='deposit()']")).click();
        driver.findElement(By.cssSelector("[ng-submit='deposit()'] input")).sendKeys("100");

        driver.findElement(By.cssSelector("[ng-click='withdrawl()']")).click();
        driver.findElement(By.cssSelector("[ng-submit='withdrawl()'] input")).sendKeys("100");
//[ng-submit='deposit()'] input
        Thread.sleep(120000);

    }
}



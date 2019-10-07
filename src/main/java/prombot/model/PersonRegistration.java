package prombot.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class PersonRegistration {
    private WebDriver driver;

    public PersonRegistration(WebDriver driver) {
        this.driver = driver;
    }
    private By loginField = By.xpath("//input[@class='lp-vertical-form__input' and @placeholder='Как вас зовут?']");
    private By emailField = By.xpath("//input[@class='lp-vertical-form__input' and @placeholder='Введите email']");
    private By passwordField = By.xpath("//input[@class='lp-vertical-form__input' and @placeholder='Придумайте пароль']");
    private By submitButton = By.xpath("//button[@type='submit' and @class='lp-button lp-button_width_full lp-button_theme_blue lp-button_height_50']//b[text()='Зарегистрироваться']");


    public PersonRegistration getLoginName(String userName){
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.findElement(loginField).sendKeys(userName);
        return this;
    }

    public PersonRegistration getEmailName(String userEmail){
        driver.findElement(emailField).sendKeys(userEmail);
        return this;
    }

    public PersonRegistration getPassword(String userPassword){
        driver.findElement(passwordField).sendKeys(userPassword);
        return this;
    }
    public RegisteredPage regButton(){
        driver.findElement(submitButton).click();
        return new RegisteredPage(driver);
    }
    public PersonRegistration register(String userName, String userEmail, String userPassword){
        this.getLoginName(userName);
        this.getEmailName(userEmail);
        this.getPassword(userPassword);
        this.regButton();
        return new PersonRegistration(driver);
    }
}


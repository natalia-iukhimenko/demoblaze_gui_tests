package gui.elements;

import gui.base.GuiEntity;
import gui.pages.HomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpForm extends GuiEntity {
    @FindBy(id = "sign-username")
    private WebElement usernameField;
    @FindBy(id = "sign-password")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@onclick='register()']")
    private WebElement signUpButton;

    public SignUpForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignUpForm setUsername(String username) {
        clearAndTypeIn(usernameField, username);
        return this;
    }

    public SignUpForm setPassword(String password) {
        clearAndTypeIn(passwordField, password);
        return this;
    }

    public SignUpForm confirmSignUp() {
        clickOn(signUpButton);
        return this;
    }

    public Alert registerAs(String username, String password) {
        setUsername(username);
        setPassword(password);
        confirmSignUp();
        waitForAlertIsPresent(5); //todo
        return driver.switchTo().alert();
    }

    public HomePage accept(Alert alert) {
        alert.accept();
        return new HomePage(driver);
    }

    public HomePage close() {
        WebElement closeButton = driver.findElement(By.xpath("//*[@id='signInModal']//*[text()='Close']")); // to refactor
        clickOn(closeButton);
        return new HomePage(driver);
    }

    public boolean isPasswordFieldMasked() {
        return isMaskedInput(passwordField);
    }

    public boolean isUsernameFieldEmpty() {
        return !usernameField.getText().equals("");
    }

    public boolean isPasswordFieldEmpty() {
        return !passwordField.getText().equals("");
    }
}

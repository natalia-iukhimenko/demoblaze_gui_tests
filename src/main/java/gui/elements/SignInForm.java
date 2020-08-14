package gui.elements;

import gui.base.GuiEntity;
import gui.pages.HomePage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInForm extends GuiEntity {
    @FindBy(id = "loginusername")
    private WebElement usernameField;
    @FindBy(id = "loginpassword")
    private WebElement passwordField;
    @FindBy(xpath = "//*[@onclick='logIn()']")
    private WebElement logInButton;

    public SignInForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignInForm setUsername(String username) {
        clearAndTypeIn(usernameField, username);
        return this;
    }

    public SignInForm setPassword(String password) {
        clearAndTypeIn(passwordField, password);
        return this;
    }

    public SignInForm confirmSignIn() {
        clickOn(logInButton);
        return this;
    }

    public HomePage signInWith(String username, String password) {
        setUsername(username);
        setPassword(password);
        confirmSignIn();
        return new HomePage(driver);
    }

    public Alert failSignInWith(String username, String password) {
        setUsername(username);
        setPassword(password);
        confirmSignIn();
        waitForAlertIsPresent(MAX_TIMEOUT);
        return driver.switchTo().alert();
    }

    public boolean isPasswordFieldMasked() {
        return isMaskedInput(passwordField);
    }

    public HomePage accept(Alert alert) {
        alert.accept();
        return new HomePage(driver);
    }
}

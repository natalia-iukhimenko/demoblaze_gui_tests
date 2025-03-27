package ru.iukhimenko.demoblaze.ui.pages;

import ru.iukhimenko.demoblaze.ui.base.GuiEntity;
import ru.iukhimenko.demoblaze.ui.elements.SignInForm;
import ru.iukhimenko.demoblaze.ui.elements.SignUpForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends GuiEntity {
    @FindBy(id = "signin2")
    private WebElement signUpButton;
    @FindBy(id = "login2")
    private WebElement signInButton;
    @FindBy (id = "signInModal")
    private WebElement signUpForm;
    @FindBy (id = "logInModal")
    private WebElement signInForm;
    @FindBy (id = "nameofuser")
    private WebElement welcomeLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public SignUpForm openSignUpForm() {
        clickOn(signUpButton);
        waitForVisibilityOf(signUpForm, MAX_TIMEOUT);
        return new SignUpForm(driver);
    }

    public SignInForm openSignInForm() {
        clickOn(signInButton);
        waitForVisibilityOf(signInForm, MAX_TIMEOUT);
        return new SignInForm(driver);
    }

    public String getWelcomeMessage() {
        waitForVisibilityOf(welcomeLink, 5);
        return welcomeLink.getText();
    }
}

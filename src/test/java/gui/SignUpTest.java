package gui;

import api.SignUpService;
import gui.base.GuiTest;
import gui.elements.SignUpForm;
import gui.pages.HomePage;
import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static gui.base.DataGenerator.*;
import static org.testng.Assert.*;

public class SignUpTest extends GuiTest {
    private SignUpForm signUpForm;

    @BeforeMethod
    public void openSignUpForm() {
        signUpForm = openHomePage()
                .openSignUpForm();
    }

    @Test
    public void successfulSignUp() {
        Alert successfulSignUp = signUpForm.registerAs(getUniqueUsername(), getValidPassword());
        assertEquals(successfulSignUp.getText(), "Sign up successful.", "Alert text assertion failed, ");
        signUpForm.accept(successfulSignUp);
    }

    @Test
    public void signUpWithNotUniqueUsername() {
        String username = getUniqueUsername(), password = getValidPassword();
        SignUpService.signUpAs(username, password);

        Alert userAlreadyExists = signUpForm.registerAs(username, getValidPassword());
        assertEquals(userAlreadyExists.getText(), "This user already exist.", "Alert text assertion failed, ");
        signUpForm.accept(userAlreadyExists);
    }

    @Test(dataProvider = "emptyUsernameOrPasswordProvider")
    public void signUpWithEmptyCredentials(String username, String password) {
        Alert emptyUsernameOrPassword = signUpForm.registerAs(username, password);
        assertEquals(emptyUsernameOrPassword.getText(), "Please fill out Username and Password.", "Alert text assertion failed, ");
        signUpForm.accept(emptyUsernameOrPassword);
    }

    @Test
    public void allFieldsAreClearedOnFormClose() {
        HomePage home = signUpForm
                .setUsername("test username")
                .setPassword("test password")
                .close();
        SignUpForm reopenedForm = home.openSignUpForm();
        SoftAssert softly = new SoftAssert();
        softly.assertTrue(signUpForm.isUsernameFieldEmpty(), "Username is cleared on form close");
        softly.assertTrue(signUpForm.isPasswordFieldEmpty(), "Password is cleared on form close");
        softly.assertAll("'Sign Up' form fields are not cleared on form close");
    }

    @Test
    public void signUpFormIsHiddenOnCloseButtonClick() {

    }

    @Test
    public void signUpFormIsHiddenOnCrossButtonClick() {

    }

    @Test
    public void passwordFieldIsMaskedTest() {
        signUpForm.setPassword(getValidPassword());
        assertTrue(signUpForm.isPasswordFieldMasked());
    }

    @DataProvider
    private Object[][] emptyUsernameOrPasswordProvider() {
        return new Object[][] {
                {"", getValidPassword()},
                {getUniqueUsername(), ""}
        };
    }
}


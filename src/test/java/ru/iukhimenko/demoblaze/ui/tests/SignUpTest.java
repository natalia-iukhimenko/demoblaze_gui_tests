package ru.iukhimenko.demoblaze.ui.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.iukhimenko.demoblaze.api.SignUpService;
import ru.iukhimenko.demoblaze.ui.base.GuiTest;
import ru.iukhimenko.demoblaze.ui.elements.SignUpForm;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static ru.iukhimenko.demoblaze.ui.base.DataGenerator.getUniqueUsername;
import static ru.iukhimenko.demoblaze.ui.base.DataGenerator.getValidPassword;

public class SignUpTest extends GuiTest {
    private SignUpForm signUpForm;

    @BeforeMethod
    public void openSignUpForm() {
        signUpForm = openHomePage().openSignUpForm();
    }

    @Test
    public void checkSuccessfulSignUpMessage() {
        Alert successfulSignUp = signUpForm.registerAs(getUniqueUsername(), getValidPassword());
        assertEquals(successfulSignUp.getText(), "Sign up successful.", "Info message after successful sign up doesn't match the expected value");
        signUpForm.accept(successfulSignUp);
    }

    @Test
    public void signUpWithNotUniqueUsername() {
        String username = getUniqueUsername(), password = getValidPassword();
        SignUpService.signUpAs(username, password);

        Alert userAlreadyExists = signUpForm.registerAs(username, getValidPassword());
        assertEquals(userAlreadyExists.getText(), "This user already exist.", "Error message after signing up with not unique username doesn't match the expected value");
        signUpForm.accept(userAlreadyExists);
    }

    @Test(dataProvider = "emptyUsernameOrPasswordProvider")
    public void signUpWithEmptyCredentials(String username, String password) {
        Alert emptyUsernameOrPassword = signUpForm.registerAs(username, password);
        assertEquals(emptyUsernameOrPassword.getText(), "Please fill out Username and Password.", "Error message after signing up with empty credentials doesn't match the expected value");
        signUpForm.accept(emptyUsernameOrPassword);
    }

    @Test
    public void allFieldsAreClearedOnSignUpFormClose() {
        signUpForm.setUsername("test username")
                .setPassword("test password")
                .close();
        SoftAssert softly = new SoftAssert();
        softly.assertTrue(signUpForm.isUsernameFieldEmpty(), "Username is cleared on form close");
        softly.assertTrue(signUpForm.isPasswordFieldEmpty(), "Password is cleared on form close");
        softly.assertAll("'Sign Up' form fields are not cleared after the form is closed");
    }

    @Test
    public void passwordFieldIsMaskedTest() {
        signUpForm.setPassword(getValidPassword());
        assertTrue(signUpForm.isPasswordFieldMasked(), "Password field is not masked on 'Sign Up' form");
    }

    @DataProvider
    private Object[][] emptyUsernameOrPasswordProvider() {
        return new Object[][] {
                {"", getValidPassword()},
                {getUniqueUsername(), ""}
        };
    }
}
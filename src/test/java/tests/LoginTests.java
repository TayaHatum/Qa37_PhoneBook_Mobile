package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){
//        boolean result =new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result= new AuthenticationScreen(driver)
                .fillEmail("noa@gmail.com")
                .fillPassword("Nnoa12345$")
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModel(){
//        boolean result = new SplashScreen(driver)
//                .checkCurrentVersion("Version 1.0.0")
        boolean result = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list");
        Assert.assertTrue(result);

    }

    @Test
    public void loginSuccessModel2(){
        Assert.assertTrue( new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("noa@gmail.com").password("Nnoa12345$").build())
                .submitLogin()
                .isActivityTitleDisplayed("Contact list"));

    }

    @Test
    public void loginWrongEmail(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("noagmail.com").password("Nnoa12345$").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }
    @Test
    public void loginWrongPassword(){
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("noa@gmail.com").password("Nnoa123").build())
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }
@AfterMethod
    public void posCondition(){
        new ContactListScreen(driver).logout();
}
}

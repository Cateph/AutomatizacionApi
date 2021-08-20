package com.tsoft.bot.frontend.steps.login;

import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.pages.pages.login.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    private WebDriver driver;
    private final LoginPage login = new LoginPage(driver);

    public LoginSteps() {
        this.driver = Hook.getDriver();
    }

    @Given("^Usuario ingresa url selecciona Dealer User y dealer login\"([^\"]*)\"$")
    public void usuarioIngresaUrlSeleccionaDealerUserYDealerLogin(String setNumberUrl) throws Throwable {
        login.enterUrl(setNumberUrl);
    }

    @When("^Usuario ingresa usuario y contrasena \"([^\"]*)\"$")
    public void usuarioIngresaUsuarioYContrasena(String setNumberCredentials) throws Throwable {
        login.login(setNumberCredentials);
    }

    @Then("^Se valida el inicio de sesion$")
    public void seValidaElInicioDeSesion() throws Exception {
        login.validarLogin();
    }
}

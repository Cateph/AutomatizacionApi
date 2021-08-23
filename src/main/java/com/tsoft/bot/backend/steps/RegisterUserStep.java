package com.tsoft.bot.backend.steps;
import com.tsoft.bot.backend.pages.pages.api.pages.RegisterUser.RegisterUserTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterUserStep {

    private final RegisterUserTest register = new RegisterUserTest();

    @Given("^El usuario envía el body y los headers para registrar un nuevo usuario \"([^\"]*)\"$")
    public void elUsuarioEnvíaElBodyYLosHeadersParaRegistrarUnNuevoUsuario(String urlbody) throws Throwable {
        register.RegisterUser(urlbody);
    }

    @When("^Se verifica que el esquema jason del registro sea correcto$")
    public void seVerificaQueElEsquemaJasonDelRegistroSeaCorrecto() {
        register.ValidateSchema();
    }

    @And("^El token de la respuesta de salida es válido$")
    public void elTokenDeLaRespuestaDeSalidaEsVálido() {
        register.ValidateToken();
    }

    @And("^El id de la respuesta de salida es correcto$")
    public void elIdDeLaRespuestaDeSalidaEsCorrecto() {
        register.ValidateID();
    }

    @Then("^El estado de la solicitud es SC_OK$")
    public void elEstadoDeLaSolicitudEsSC_OK() {
        register.ValidateStatus();
    }
}

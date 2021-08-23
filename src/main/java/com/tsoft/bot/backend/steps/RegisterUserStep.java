package com.tsoft.bot.backend.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RegisterUserStep {
    @Given("^El usuario envía el body y los headers para registrar un nuevo usuario \"([^\"]*)\"$")
    public void elUsuarioEnvíaElBodyYLosHeadersParaRegistrarUnNuevoUsuario(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^Se verifica que el esquema jason del registro sea correcto$")
    public void seVerificaQueElEsquemaJasonDelRegistroSeaCorrecto() {
    }

    @And("^El token de la respuesta de salida es válido$")
    public void elTokenDeLaRespuestaDeSalidaEsVálido() {
    }

    @And("^El id de la respuesta de salida es correcto$")
    public void elIdDeLaRespuestaDeSalidaEsCorrecto() {
    }

    @Then("^El estado de la solicitud es SC_OK$")
    public void elEstadoDeLaSolicitudEsSC_OK() {
    }
}

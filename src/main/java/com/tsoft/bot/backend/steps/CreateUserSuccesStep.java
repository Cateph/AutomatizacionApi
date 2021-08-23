package com.tsoft.bot.backend.steps;

import com.tsoft.bot.backend.pages.pages.api.pages.CreateUser.CreateUserTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;


public class CreateUserSuccesStep {

    private final CreateUserTest create = new CreateUserTest();

    @Given("^El usuario envía el body y los headers para crear un nuevo usuario \"([^\"]*)\"$")
    public void elUsuarioEnvíaElBodyYLosHeadersParaCrearUnNuevoUsuario(String urlbody) throws Throwable {
        create.CreateUser(urlbody);
    }

    @When("^El esquema del jason sea correcto$")
    public void elEsquemaDelJasonSeaCorrecto() {

        create.ValidateSchema();
    }

    @And("^El name de la respuesta de salida es igual al dato enviado$")
    public void elNameDeLaRespuestaDeSalidaEsIgualAlDatoEnviado() {

        create.ValidateName();
    }

    @And("^El job de la respuesta de salida es igual al dato enviado$")
    public void elJobDeLaRespuestaDeSalidaEsIgualAlDatoEnviado() {

        create.ValidateJob();
    }

    @And("^La fecha de creación de usuario es igual a la fecha del Sitema opertivo$")
    public void laFechaDeCreaciónDeUsuarioEsIgualALaFechaDelSitemaOpertivo() {

        create.ValidateCreateDate();
    }

    @Then("^El estado de la solicitud es SC_CREATED$")
    public void elEstadoDeLaSolicitudEsSC_CREATED() {

        create.ValidateStatus();

    }
}

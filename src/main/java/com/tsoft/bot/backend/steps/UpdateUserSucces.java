package com.tsoft.bot.backend.steps;

import com.tsoft.bot.backend.pages.pages.api.pages.UpdateUser.UpdateUserTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateUserSucces {
    private final UpdateUserTest update = new UpdateUserTest();

    @Given("^El usuario envía el body y los headers para actualizar un determinado usuario \"([^\"]*)\"$")
    public void elUsuarioEnvíaElBodyYLosHeadersParaActualizarUnDeterminadoUsuario(String urlbody) throws Throwable {
        update.UpdateUser(urlbody);
    }

    @When("^Se valida el esquema jason correcto de respuesta$")
    public void seValidaElEsquemaJasonCorrectoDeRespuesta() {
        update.ValidateSchema();
    }

    @And("^Se valida que el name de la respuesta de salida es igual al dato enviado$")
    public void seValidaQueElNameDeLaRespuestaDeSalidaEsIgualAlDatoEnviado() {
        update.ValidateName();
    }

    @And("^Se valida que el job de la respuesta de salida es igual al dato enviado$")
    public void seValidaQueElJobDeLaRespuestaDeSalidaEsIgualAlDatoEnviado() {
        update.ValidateJob();
    }

    @And("^Se valida la fecha de actualización de usuario es igual a la fecha del Sistema opertivo$")
    public void seValidaLaFechaDeActualizaciónDeUsuarioEsIgualALaFechaDelSistemaOpertivo() {
        update.ValidateUpdateDate();
    }

    @Then("^El estado de la solicitud update es SC_OK$")
    public void elEstadoDeLaSolicitudUpdateEsSC_OK() {
        update.ValidateStatus();
    }
}

package com.tsoft.bot.backend.steps;

import com.tsoft.bot.backend.pages.pages.api.pages.GetResources.GetResourceTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetResourcesStep {

    private final GetResourceTest getresource = new GetResourceTest();

    @Given("^El usuario envía los headers para obtener la lista de recursos \"([^\"]*)\"$")
    public void elUsuarioEnvíaLosHeadersParaObtenerLaListaDeRecursos(String url) throws Throwable {
        getresource.GetResources(url);
    }

    @When("^Se verifica que el esquema jason de la solicitud sea correcto$")
    public void seVerificaQueElEsquemaJasonDeLaSolicitudSeaCorrecto() {
        getresource.ValidateSchema();
    }

    @And("^Se verifica la existencia de un recurso por ID$")
    public void seVerificaLaExistenciaDeUnRecursoPorID() {
        getresource.GetResourcesByID();
    }

    @And("^Se verifica la existencia de un recurso por Nombre$")
    public void seVerificaLaExistenciaDeUnRecursoPorNombre() {
        getresource.GetResourceByName();
    }

    @Then("^El estado de la solicitud debe ser SC_OK$")
    public void elEstadoDeLaSolicitudDebeSerSC_OK() {
        getresource.ValidateStatus();
    }
}

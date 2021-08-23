Feature: Requerimiento GET para Obtener la lista de Recursos

  @GetResource
  Scenario Outline: Request para Obtner la lista de Recursos registrados
    Given El usuario envía los headers para obtener la lista de recursos "<caso_prueba>"
    When Se verifica que el esquema jason de la solicitud sea correcto
    And Se verifica la existencia de un recurso por ID
    And Se verifica la existencia de un recurso por Nombre
    Then El estado de la solicitud debe ser SC_OK
    Examples:
      | caso_prueba|
      |           1|
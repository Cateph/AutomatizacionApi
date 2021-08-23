Feature: Requerimiento POST para Registrar Usuario

  @Register
  Scenario Outline: Request Registro de usuario correcto
    Given El usuario envía el body y los headers para registrar un nuevo usuario "<caso_prueba>"
    When Se verifica que el esquema jason del registro sea correcto
    And El token de la respuesta de salida es válido
    And El id de la respuesta de salida es correcto
    Then El estado de la solicitud es SC_OK
    Examples:
      | caso_prueba|
      |           1|
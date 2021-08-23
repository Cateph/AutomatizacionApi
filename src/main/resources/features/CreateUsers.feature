Feature: Requerimiento POST para Crear Usuario

  @Create
  Scenario Outline: Request Creación de usuario correcta
    Given El usuario envía el body y los headers para crear un nuevo usuario "<caso_prueba>"
    When El esquema del jason sea correcto
    And El name de la respuesta de salida es igual al dato enviado
    And El job de la respuesta de salida es igual al dato enviado
    And La fecha de creación de usuario es igual a la fecha del Sitema opertivo
    Then El estado de la solicitud es SC_CREATED
    Examples:
      | caso_prueba|
      |           1|
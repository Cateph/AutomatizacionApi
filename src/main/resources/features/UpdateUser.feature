Feature: Requerimiento PUT para Actualizar Usuario

  @Update
  Scenario Outline: Request Actualizar correctamente los datos del usuario
    Given El usuario envía el body y los headers para actualizar un determinado usuario "<caso_prueba>"
    When Se valida el esquema jason correcto de respuesta
    And Se valida que el name de la respuesta de salida es igual al dato enviado
    And Se valida que el job de la respuesta de salida es igual al dato enviado
    And Se valida la fecha de actualización de usuario es igual a la fecha del Sistema opertivo
    Then El estado de la solicitud update es SC_OK
    Examples:
      | caso_prueba|
      |           1|
package com.tsoft.bot.frontend.pages.pages.login;
import com.tsoft.bot.frontend.base.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.List;
import com.tsoft.bot.frontend.pages.objects.ExcelDataObjects;
import com.tsoft.bot.frontend.pages.objects.McssObjects;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BaseClass {
    private WebDriver driver;
    private static GenerateWord generateWord = new GenerateWord();
    String mensaje;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = Hook.getDriver();
    }

    private List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME);
    }

    public void enterUrl(String urlTestCase) throws Throwable {
        mensaje = "Se seleccionó la opción Dealer User y se da clic en Dealer Login";
        try {
            int countPage = Integer.parseInt(urlTestCase) - 1;
            String url = getData().get(countPage).get(ExcelDataObjects.COLUMN_URL);
            driver.get(url);
            click(driver, McssObjects.RBTN_DEALER);
            stepPass(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            System.out.println("SE INGRESO A LA URL");
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            stepFail(driver,"Fallo en tiempo de respuesta : " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void login(String casoPrueba) throws Throwable {
        mensaje = "Se ingresó el documento y contraseña, seguidamente clic en el botón Buscar";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(McssObjects.LNK_DEALER_LOGIN));
            click(driver, McssObjects.LNK_DEALER_LOGIN);
            int countPage = Integer.parseInt(casoPrueba) - 1;
            String document = getData().get(countPage).get(ExcelDataObjects.COLUMN_USER);
            String passwd = getData().get(countPage).get(ExcelDataObjects.COLUMN_PASWWORD);
            clear(driver, McssObjects.TXT_USER);
            clear(driver, McssObjects.TXT_PASSWORD);
            typeText(driver, McssObjects.TXT_USER, document);
            typeText(driver, McssObjects.TXT_PASSWORD, passwd);
            sleep(3500);
            System.out.println(mensaje);
            stepPass(driver, mensaje);
            generateWord.sendText(mensaje);
            generateWord.addImageToWord(driver);
            click(driver, McssObjects.BTN_BUSCAR);
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("Fallo en tiempo de respuesta: " + we.getMessage());
            stepFail(driver, "Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

    public void validarLogin() throws Exception {
        mensaje = "Se valida que el usuario ha sido logueado correctamente";
        try {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(McssObjects.LBL_CLIENTE));
            Boolean cliente = isDisplayed(driver, McssObjects.LBL_CLIENTE);
            if (Boolean.TRUE.equals(cliente))
            {
                stepPass(driver, mensaje);
                generateWord.sendText(mensaje);
                generateWord.addImageToWord(driver);

            }
            else
            {
                System.out.println("No se pudo validar el login");
                stepFail(driver,"Usuario o contraseña inválidos");
                generateWord.sendText("Usuario o contraseña inválidos");
                generateWord.addImageToWord(driver);
            }
        }
        catch (Exception we)
        {
            ExcelReader.writeCellValue(ExcelDataObjects.EXCEL_DOC, ExcelDataObjects.PAGE_NAME, 1, 19, "FAIL");
            System.out.println("No se pudo validar el login");
            stepFail(driver,"Fallo en tiempo de respuesta: " + we.getMessage());
            generateWord.sendText("Fallo en tiempo de respuesta");
            generateWord.addImageToWord(driver);
        }
    }

}

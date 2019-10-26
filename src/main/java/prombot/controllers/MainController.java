package prombot.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import prombot.MainApp;
import prombot.model.AddBacket;
import prombot.model.PersonRegistration;
import prombot.model.Product;
import prombot.util.JDBCMySQLConnection;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {

    @FXML
    private TextField vendorCodeField;

    @FXML
    private TextArea productDescriptionArea;

    @FXML
    private Button clearReferenceField2;

    @FXML
    private Button searchButton;

    @FXML
    private Button clearReferenceField1;

    @FXML
    private TextField yourEmailField;

    @FXML
    private Button clearReferenceField;

    @FXML
    private Button addToBacketButton;

    @FXML
    private Tooltip tooltipProd;

    @FXML
    private Button copyProductDescriptionArea;

    @FXML
    private Button clearProductDescriptionArea;

    @FXML
    private Tooltip tooltipRef;

    @FXML
    private TextField yourPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField putReferenceField;

    @FXML
    private TextField yourNameField;

    @FXML
    private Tooltip tooltipArea;

    @FXML
    private TextArea backetValue;

    @FXML
    private Button copyReferenceField;

    @FXML
    private TextField loginField;

    private WebDriver driver;
    private MainApp mainApp;
    private boolean isClicked = false;
    private Stage dialogStage;
    private final static String chromeProperties = "C:/Users/Остап/IdeaProjects/Selenium/drivers/chromedriver.exe";
    private final static String MAIN_URL = "https://prom.ua/";
    private final static String REG_URL = "https://prom.ua/join-customer?source_id=txt.register.customer";
    private PersonRegistration personRegistration;

    public MainController() {
    }

    @FXML
    void initialize() {

    }

    @FXML
    private void handleSearchButtonClick() throws IOException, SQLException, ClassNotFoundException {
        if (isInputValid()) {
            productDescriptionArea.setText(product(putReferenceField.getText()));
            vendorCodeField.setText(vendorCode(putReferenceField.getText()));
            isClicked = true;
        }
    }
    private String vendorCode(String url) throws IOException {
        Product productos = new Product(url);
        return productos.getVendorCode(url);
    }


    private String product(String url) throws IOException, SQLException, ClassNotFoundException {
        StringBuilder sb = new StringBuilder();
        Product productsss = new Product(url);
        JDBCMySQLConnection jdbcMySQLConnection = new JDBCMySQLConnection();
        jdbcMySQLConnection.insertTable(
                productsss.getTitle(url),
                productsss.getPrice(url),
                productsss.getAvailability(url),
                productsss.getColor(url),
                productsss.getDescription(url),
                productsss.getVendorCode(url));
        sb.append("Название товара=" + " " + productsss.getTitle(url)).append("\n");
        sb.append("Цена товара=" + " " + productsss.getPrice(url)).append("\n");
        sb.append("Наличие товара=" + " " + productsss.getAvailability(url)).append("\n");
        sb.append("Цвет товара=" + " " + productsss.getColor(url)).append("\n");
        sb.append("Описание товара=" + " " + productsss.getDescription(url)).append("\n");
        sb.append("Артикул товара=" + " " + productsss.getVendorCode(url)).append("\n");
        return sb.toString();

    }


    private boolean isInputValid() {
        String errorMessage = "";
        if (putReferenceField.getText() == null || putReferenceField.getText().length() == 0) {
            errorMessage += "Не правильно введённая строка";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Не правильно ввёденная ссылка");
            alert.setHeaderText("Пожалуйста исправте текст ссылки на такой \n https://prom.ua/p30215758-nabor-zhenskih-nosovyh.html");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleButtonSubmit() throws SQLException, ClassNotFoundException {
        seleniumDriverConnection();
        personRegistr();
        loginField.setText(yourNameField.getText());
        JDBCMySQLConnection jdbcMySQLConnection = new JDBCMySQLConnection();
        jdbcMySQLConnection.insertRegistrValueTable(yourNameField.getText(), yourEmailField.getText(), yourPasswordField.getText());

    }

    public PersonRegistration personRegistr() {
        PersonRegistration promFirstPage = new PersonRegistration(driver);

        promFirstPage.register(yourNameField.getText(), yourEmailField.getText(), yourPasswordField.getText());

        promFirstPage.regButton();
        return new PersonRegistration(driver);
    }

    @FXML
    private void handleAddToBacket() throws IOException, SQLException, ClassNotFoundException {
        if (isInputValid()) {
//            String x = product(vendorCodeField.getText());
//            productDescriptionArea.setText(String.valueOf(x));
            AddBacket addBacket = new AddBacket(driver);
            driver.get(putReferenceField.getText());
            addBacket.getButtonClick();
            JDBCMySQLConnection jdbc = new JDBCMySQLConnection();
            jdbc.insertToProductInfoTable(yourNameField.getText(),
                    putReferenceField.getText(),vendorCodeField.getText());
        }
    }

    private void seleniumDriverConnection() {
        System.setProperty("webdriver.chrome.driver", chromeProperties);
        driver = new ChromeDriver();
        driver.get(MAIN_URL);
        driver.get(REG_URL);
    }

    @FXML
    private void handleClearSearchInput() {
        putReferenceField.clear();
        putReferenceField.getOnMouseMoved();
    }

    @FXML
    private void handleCopySearchInput() {
        putReferenceField.copy();
    }

    @FXML
    private void handlePasteSearchInput() {
        putReferenceField.paste();
    }

    @FXML
    private void handleClearProductArea() {
        productDescriptionArea.clear();
    }

    @FXML
    private void handleCopyProductArea() {
        productDescriptionArea.copy();
    }

    @FXML
    private void handlePasteProductArea() {
        productDescriptionArea.paste();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isClicked() {
        return isClicked;
    }

}

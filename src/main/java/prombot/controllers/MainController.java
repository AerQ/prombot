package prombot.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import prombot.MainApp;
import prombot.model.AddBacket;
import prombot.model.PersonRegistration;
import prombot.model.Product;
import prombot.util.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private SearchService searchService;
    private Stage dialogStage;
    private final static String chromeProperties = "C:/Users/Остап/IdeaProjects/Selenium/drivers/chromedriver.exe";
    private final static String MAIN_URL = "https://prom.ua/";
    private final static String REG_URL = "https://prom.ua/join-customer?source_id=txt.register.customer";
    private PersonRegistration personRegistration;

    public MainController() {
    }

    @FXML
    void initialize() {
        searchService = new SearchService();

    }

    @FXML
    private void handleSearchButtonClick() throws IOException {
        if (isInputValid()) {
            List<Product> x = search(putReferenceField.getText());
            productDescriptionArea.setText(String.valueOf(x));
            vendorCodeField.setText(putReferenceField.getText());

            isClicked = true;
        }
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
    private void handleButtonSubmit() {
        selen();
        firstLoad();
        loginField.setText(yourNameField.getText());
    }
    public PersonRegistration firstLoad() {
        PersonRegistration promFirstPage = new PersonRegistration(driver);
        promFirstPage.register(yourNameField.getText(), yourEmailField.getText(), yourPasswordField.getText());

        promFirstPage.regButton();
        return new PersonRegistration(driver);
    }
    @FXML
    private void handleAddToBacket() throws IOException {
        if (isInputValid()) {
            List<Product> x = search(vendorCodeField.getText());
            productDescriptionArea.setText(String.valueOf(x));

            AddBacket addBacket = new AddBacket(driver);
            driver.get(putReferenceField.getText());
            addBacket.getButtonClick();
        }
    }
    private void selen() {
        System.setProperty("webdriver.chrome.driver", chromeProperties);
        driver = new ChromeDriver();
        driver.get(MAIN_URL);
        driver.get(REG_URL);
    }

    private List<Product> search(String url) throws IOException {
        SearchService searchService = new SearchService();
        return searchService.marshall(url);
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

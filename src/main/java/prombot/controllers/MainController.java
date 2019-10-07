package prombot.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import prombot.MainApp;
import prombot.util.ProductParser;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML
    private TextField VendorCodeField;

    @FXML
    private TextArea productDescriptionArea;

    @FXML
    private Button searchButton;

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
    private PasswordField yourPasswordField;

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


    private MainApp mainApp;
    private boolean isClicked = false;
    private SearchService searchService;
    private ProductParser productParser;
    private Stage dialogStage;
    @FXML
    void initialize() {
        searchService = new SearchService();
    }
    @FXML
    private void handleSearchButtonClick() throws IOException {
        if (isInputValid()){
            List<ProductParser> x = search(putReferenceField.getText());
            productDescriptionArea.setText(String.valueOf(x));

            isClicked=true;
        }
    }
    private boolean isInputValid(){
        String errorMessage ="";
        if (putReferenceField.getText() ==null || putReferenceField.getText().length() ==0){
                errorMessage+="Не правильно введённая строка";
        }
        if (errorMessage.length()==0){
            return true;
        }else {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Не правильно ввёденная ссылка");
            alert.setHeaderText("Пожалуйста исправте текст ссылки на такой \n https://prom.ua/p30215758-nabor-zhenskih-nosovyh.html");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
         }
    }
    @FXML
    private void handleClearSearchInput(){
                putReferenceField.clear();
                putReferenceField.getOnMouseMoved();
    }
    @FXML
    private void handleCopySearchInput(){
        putReferenceField.copy();
    }
    @FXML
    private void handlePasteSearchInput(){
        putReferenceField.paste();
    }
    @FXML
    private void handleClearProductArea(){
        productDescriptionArea.clear();
    }
    @FXML
    private void handleCopyProductArea(){
        productDescriptionArea.copy();
    }
    @FXML
    private void handlePasteProductArea(){
        productDescriptionArea.paste();
    }
    private List<ProductParser> search (String url) throws IOException {
        SearchService searchService = new SearchService();
        return searchService.marshall(url);
    }
//    private String splitProductInfo(ProductParser holder) throws IOException {
//        StringBuilder builder = new StringBuilder();
//
//
//        builder.append("Title: " + holder.getName() + "\n");
//        builder.append("Title: " + holder.getPrice() + "\n");
//        builder.append("Title: " + holder.getAvailability() + "\n");
//        builder.append("Title: " + holder.getColor() + "\n");
//        builder.append("Title: " + holder.getDescription() + "\n");
//
//       return builder.toString();
//
//    }

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

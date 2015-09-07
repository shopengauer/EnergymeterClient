package ru.matritca.energymeterclient.fxmlcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Created by Vasiliy on 07.09.2015.
 */
public class FXMLTabPaneController {


    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tab1;
     @FXML
    private Tab tab2;


    public TabPane getViewTabPane() {
        return tabPane;
    }
}

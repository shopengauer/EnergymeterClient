package ru.matritca.energymeterclient;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.matritca.energymeterclient.fxmlcontrollers.FXMLMainController;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.matritca.energymeterclient.*"})
public class EnergyMeterClientApplication extends AbstractJavaFxApplicationSupport{

    private Logger logger = LoggerFactory.getLogger(EnergyMeterClientApplication.class);
    @Autowired
    private FXMLMainController mainController;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene((Parent)mainController.getView(), 1000, 700);


        //  primaryStage.setFullScreen(true);
        //  primaryStage.setResizable(false);


        //  scene.getStylesheets().add("/styles/fxmlschema.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Energymeter communication client");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launchApp(EnergyMeterClientApplication.class, args);
    }


}

package ru.matritca.energymeterclient.springconfigs;


import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.matritca.energymeterclient.fxmlcontrollers.FXMLMainController;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by vasiliy on 15.06.15.
 */
@Configuration
public class FXMLControllersConfig {

    private Logger logger = LoggerFactory.getLogger(FXMLMainController.class);

    @Bean
    @Qualifier(value = "fxmlMain")
    public FXMLMainController fxmlController() throws IOException {
        System.out.println("Load controller");
         return (FXMLMainController) loadController("/fxml/FXMLMainSchema.fxml");

    }

    protected Object loadController(String url) throws IOException {
        try
                (InputStream fxmlStream = getClass().getResourceAsStream(url)) {
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return loader.getController();
        }
    }

    @Bean
    public ObservableMap<Object,Object> observableComPortMapProps(){
        return FXCollections.observableMap(new HashMap<Object, Object>());
    }


}

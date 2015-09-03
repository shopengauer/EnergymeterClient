package ru.matritca.energymeterclient.fxmlcontrollers;


import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import ru.matritca.energymeterclient.domain.SerialPortObject;
import ru.matritca.energymeterclient.domain.SerialPortObjectStringConverter;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by vasiliy on 15.06.15.
 */

public class FXMLMainController implements Initializable,ApplicationEventPublisherAware {

    private Logger logger = LoggerFactory.getLogger(FXMLMainController.class);

    private Scene mainScene;

    @FXML
    private AnchorPane mainView;


    @FXML
    private BorderPane borderPane;

    /**
     * Комбо бокс для хранения объектов ком порта
     */

    @FXML
    private ComboBox<String> comPortNameCombo;

    /**
     *  обьект Map для хранения свойств ком портов
     *  ключ - String "Имя ком порта" значение - SerialPortObject
     */
    private ObservableMap<Object,Object> observableComPortMapProps; //

    /**
     * объект для хранения актуального списка ком портов
     */
    private  ObservableSet<String> comPortSet;


    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem comPortsConfig;

    @FXML
    private MenuItem comPortsSettings;

    @FXML
    private Button refreshComPorts;

    @FXML
    private Button openPortBtn;

    @FXML
    private Label infoLabel;

    private double dragOffSetX;
    private double dragOffSetY;

//    @Autowired
//    private FXMLTabController fxmlTabController;


    private ApplicationEventPublisher eventPublisher;



    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    private void initMainWindow(){

        // Получаем Map контейнер для хранения свойств всех ком портов
        observableComPortMapProps = comPortNameCombo.getProperties();


        // Получаем список имен всех доступных ком портов
        comPortSet = FXCollections.observableSet(SerialPortList.getPortNames());

        // помещаем все имена ком портов в наш контейнер
        for (String s : comPortSet) {
            observableComPortMapProps.put(s,new SerialPortObject(s));
        }
        // добавляем их имена в комбобокс
        comPortNameCombo.getItems().addAll(comPortSet);
        // устанавливаем первый элемент в списке
        comPortNameCombo.getSelectionModel().selectFirst();

        // инициализируем слушателя comPortSet для внесения изменей в comPortNameCombo
        comPortSet.addListener(new SetChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                if (change.wasAdded()) {
                    String elementAdded = change.getElementAdded();
                    comPortNameCombo.getItems().add(elementAdded);
                    observableComPortMapProps.put(elementAdded, new SerialPortObject(elementAdded));
                    comPortNameCombo.getSelectionModel().selectFirst();
                }
                if (change.wasRemoved()) {
                    String elementRemoved = change.getElementRemoved();
                    comPortNameCombo.getItems().remove(elementRemoved);
                    observableComPortMapProps.remove(elementRemoved);
                    comPortNameCombo.getSelectionModel().selectFirst();
                }

            }
        });


        comPortNameCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(newValue);
                    infoLabel.setText((serialPortObject.isOpened() ? " открыт" : " закрыт"));
                } else {
                    infoLabel.setText("");
                }
            }
        });

        SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(comPortNameCombo.getValue());
        infoLabel.setText(comPortNameCombo.getValue() + (serialPortObject.isOpened() ? " открыт" : " закрыт"));


//
//

//        infoLabel.textProperty().bindBidirectional(comPortNameCombo.valueProperty(), new SerialPortObjectStringConverter());
//
//        this.setComPortsToCombo();
//        borderPane.setCenter(beanTabPane);
//        Platform.runLater(() ->
//                mainScene = new Scene((Parent) fxmlComPortConfigController.getView(), 350, 500, Color.AQUAMARINE));

    }


    @FXML
    void handleComPortsSettings(ActionEvent event) {

        
        
        
    }
    

    @FXML
    void handleComPortsConfig(ActionEvent event) {

//        logger.info(Thread.currentThread().getName());
//
//        if (!isComPortSelect()){
//            infoLabel.setText("Please, select COM port!");
//
//        }else{
//            Stage comPortConfigStage =  new Stage(StageStyle.UTILITY);
//            comPortConfigStage.initModality(Modality.APPLICATION_MODAL);
//            comPortConfigStage.setOpacity(1);
//            String selectedComPort = comPortNameCombo.getValue().getPortname();
//
//            // todo Save data about com port to the label properties
//            fxmlComPortConfigController.getComPortLabel().getProperties().put("selectedComPort", selectedComPort);
//
//             SerialPortObject serialPortObject = (SerialPortObject)observableComPortMapProps.get(selectedComPort);
//
//             fxmlComPortConfigController.getBaudRateCombo().setValue(serialPortObject.getBaudRate().getLabel());
//             fxmlComPortConfigController.getDataBitsCombo().setValue( serialPortObject.getDatabits().getLabel());
//             fxmlComPortConfigController.getStopBitsCombo().setValue(serialPortObject.getStopbits().getLabel());
//             fxmlComPortConfigController.getParityCombo().setValue(serialPortObject.getParity().getLabel());
//
//
//            /**
//             *
//             */
//             mainScene.setOnMousePressed(new EventHandler<MouseEvent>() {
//                 @Override
//                 public void handle(MouseEvent event) {
//
//                 }
//             });
//
//            mainScene.setOnMousePressed((MouseEvent e) -> {
//                {
//                    dragOffSetX = e.getScreenX() - comPortConfigStage.getX();
//                    dragOffSetY = e.getScreenY() - comPortConfigStage.getY();
//                }
//            });
//
//            mainScene.setOnMouseDragged((MouseEvent e) -> {
//                {
//                    comPortConfigStage.setX(e.getScreenX() - dragOffSetX);
//                    comPortConfigStage.setY(e.getScreenY() - dragOffSetY);
//                }
//            });
//            comPortConfigStage.setResizable(false);
//            comPortConfigStage.setScene(mainScene);
//            comPortConfigStage.setTitle(selectedComPort + " Port config");
//            comPortConfigStage.show();
//
//        }
//
//

    }

    @FXML
    void handleOpenPortBtn(ActionEvent event) {

         String comPortName = comPortNameCombo.getValue();


      if(comPortName != null) {
          // получаем набор свойств для выбранного ком порта
          SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(comPortName);

          try {
              // пытаемся открыть выбранный компорт


              serialPortObject.getSerialPort().openPort();
           // infoLabel.setText(serialPortObject.isOpened() ? " открыт" : " закрыт");
//                OpenComPortEvent comPortEvent = new OpenComPortEvent(this, serialPortObject,"Port is open");
//                eventPublisher.publishEvent(comPortEvent);

          } catch (SerialPortException e) {
              // выводим сообщение об ошибке если кинуто исключение
//               infoLabel.setText(e.getExceptionType());
//                OpenComPortEvent comPortEvent = new OpenComPortEvent(this, serialPortObject,e.getExceptionType());
//                eventPublisher.publishEvent(comPortEvent);
          }
      }
            // logger.info("Com port is opened: {}", serialPortObject.isOpened());

//

//            Tab tab = new Tab(comPortNameCombo.getValue());
//            ObservableMap<Object, Object> tabProperties = tab.getProperties();
//            AnchorPane tabAnchor = new AnchorPane();
//
//            Pane pane  = new Pane();
//            Button closeBtn = new Button("Close port");
//            closeBtn.setLayoutX(50);
//            closeBtn.setLayoutY(50);
//            // todo: продумать логику закрытия ком порта
//            closeBtn.setOnAction(e -> beanTabPane.getTabs().removeAll(tab));
//            pane.getChildren().add(closeBtn);
//          //  pane.getChildren().addAll(new TextArea());
//
//            tabAnchor.getChildren().add(pane);
//            tab.setContent(tabAnchor);
//            // todo: close com port when tab closing
//            // tab.setOnClosed(event1 -> {});
//            //mainTabPain.getTabs().add(tab);
//            beanTabPane.getTabs().add(tab);

//        } else {
//            infoLabel.setText("Com port was not selected!");
//        }


         //Tab tab = fxmlTabController.getTab();
        //  beanTabPane.getTabs().filtered(tab1 -> tab1.getText().equals())



    }

    @FXML
    void handleCloseItem(ActionEvent event)
    {
        Platform.exit();

    }

    @FXML
    void handleRefreshComPorts(ActionEvent event) {

        ObservableSet<String> currentComPortSet = FXCollections.observableSet(SerialPortList.getPortNames());
       // comPortSet.clear();

        for (String s : comPortSet) {
           if(!currentComPortSet.contains(s)){
                comPortSet.remove(s);
            }
        }
        comPortSet.addAll(currentComPortSet);

    }

    public AnchorPane getView() {
        return mainView;
    }

    private void setComPortsToCombo(){

        /**
         * Get existing com ports
         */
         ObservableSet<String> comPortSet = FXCollections.observableSet(SerialPortList.getPortNames());

         comPortSet.addListener(new SetChangeListener<String>() {
             @Override
             public void onChanged(Change<? extends String> change) {
                if(change.wasRemoved()){
                    infoLabel.setText("ComPorts removed");
                }
                 if(change.wasAdded()){
                     infoLabel.setText("ComPorts added");
                 }
             }
         });

        /**
         *  If observableActualComPortSet not contain founded com ports in comPortSet,
         *  remove one from the observableActualComPortSet
         *  This is done for retain current configs of com ports. Com
         */
//        for (String s : observableActualComPortSet) {
//            if(!comPortSet.contains(s)){
//                observableActualComPortSet.remove(s);
//            }
//
//        }
       // observableActualComPortSet.clear();
      //  observableActualComPortSet.

//        observableActualComPortSet.stream().noneMatch(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return comPortSet.contains(s);
//            }
//        });

      //  observableActualComPortSet.stream().filter(s -> comPortSet.contains(s));//.collect();

        /**
         * And then add comPortSet to actual Comport Set(add only if comport not already exist)
         */
//         observableActualComPortSet.addAll(comPortSet);


    }


    private boolean isComPortSelect(){
       return (comPortNameCombo.getValue() != null);
    }





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger.info("Initilize port config Mainscene");

    }


    public ObservableMap<Object, Object> getObservableComPortMapProps() {
        return observableComPortMapProps;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }
}

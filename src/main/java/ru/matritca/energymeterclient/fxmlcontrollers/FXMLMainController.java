package ru.matritca.energymeterclient.fxmlcontrollers;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Screen;
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

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

/**
 * Created by vasiliy on 15.06.15.
 */

public class FXMLMainController implements Initializable,ApplicationEventPublisherAware {

    private Logger logger = LoggerFactory.getLogger(FXMLMainController.class);

    private Scene portConfigScene;

    @FXML
    private AnchorPane mainView;


    @FXML
    private BorderPane borderPane;

    /**
     * Комбо бокс для хранения и выбора имен ком порта
     */

    @FXML
    private ComboBox<String> comPortNameCombo;

    /**
     *  обьект Map для хранения свойств ком портов SerialPortObject
     *  ключ - String "Имя ком порта" значение - SerialPortObject
     */
    private ObservableMap<Object,Object> observableComPortMapProps; //

    /**
     * объект для хранения текущего списка ком портов полученного с ПК
     */
    private  ObservableSet<String> comPortSet;

    @FXML
    private Label exceptionLabel;

    @FXML
    private Button portConfigBtn;

    @FXML
    private Button closePortBtn;

    @FXML
    private Circle circleOpenPortInfo;

    @FXML
    private Label comPortInfo;

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

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private FXMLComPortConfigController fxmlComPortConfigController;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @PostConstruct
    private void initMainWindow(){ //блок первичной настройки приложения

        Screen primaryScreen = Screen.getPrimary();
        double dpi = primaryScreen.getDpi();
        System.out.println(dpi);

        ObservableList<Screen> screenList = Screen.getScreens();
        for(Screen screen: screenList) {
            System.out.println("DPI: " + screen.getDpi());
            System.out.print("Screen Bounds: ");
            Rectangle2D bounds = screen.getBounds();
            System.out.println(bounds);
            System.out.print("Screen Visual Bounds: ");
            Rectangle2D visualBounds = screen.getVisualBounds();
            System.out.println(visualBounds);
            System.out.println("-----------------------");
        }

        observableComPortMapProps = comPortNameCombo.getProperties(); // Получаем Map контейнер для хранения свойств всех ком портов
        comPortSet = FXCollections.observableSet(SerialPortList.getPortNames());// Получаем Set список имен всех доступных ком портов

        // помещаем все имена ком портов в наш контейнер
        // Создаем объекты SerialPortObject для каждого найденного компорта
        // добавляем listener на свойство открыт ли ком порт в SerialPortObject
        // помещаем serialPortObject в контейнер для хранения
        for (String s : comPortSet) {
           SerialPortObject serialPortObject = new SerialPortObject(s); // порты создаются с настройками по умолчанию
           addIsComPortOpenListener(serialPortObject);
           observableComPortMapProps.put(s,serialPortObject);
         }

        comPortNameCombo.getItems().addAll(comPortSet); // добавляем их имена в комбобокс
        comPortNameCombo.getSelectionModel().selectFirst();// устанавливаем первый элемент в списке
        comPortInfo.setText(comPortNameCombo.getSelectionModel().getSelectedItem());// добавляем имя выбранного ком порта в информационное окно


        // добавляем listener для comPortSet. При изменении comPortSet изменения вносятся comPortNameCombo
        comPortSet.addListener(new SetChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> change) {
                if (change.wasAdded()) {
                    String elementAdded = change.getElementAdded();
                    comPortNameCombo.getItems().add(elementAdded);
                    SerialPortObject serialPortObject = new SerialPortObject(elementAdded);
                    addIsComPortOpenListener(serialPortObject);
                    observableComPortMapProps.put(elementAdded, serialPortObject);
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

         // listener для изменеия данных в информационном углу при смене компорта
        comPortNameCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(newValue);
                    if(serialPortObject.isOpenPortProperty().get()) {
                        circleOpenPortInfo.setFill(Paint.valueOf("green"));}
                    else {
                        circleOpenPortInfo.setFill(Paint.valueOf("red"));
                        }
                    comPortInfo.setText(serialPortObject.getPortname());
                    exceptionLabel.setText("");
                } else {
                    comPortInfo.setText("Порт не выбран");
                    circleOpenPortInfo.setFill(Paint.valueOf("red"));
                }
            }
        });


//

//        infoLabel.textProperty().bindBidirectional(comPortNameCombo.valueProperty(), new SerialPortObjectStringConverter());
//
//        this.setComPortsToCombo();
//        borderPane.setCenter(beanTabPane);
        Platform.runLater(() ->
                portConfigScene = new Scene((Parent) fxmlComPortConfigController.getView(), 412, 330, Color.BEIGE));

    }


    @FXML
    void handleComPortsSettings(ActionEvent event) {


        
    }
    

    @FXML
    void handleComPortsConfig(ActionEvent event) {

        logger.info(Thread.currentThread().getName());

        if (!isComPortSelect()){
            exceptionLabel.setText("Please, select COM port!");

        }else{
            Stage comPortConfigStage =  new Stage(StageStyle.UTILITY);
            comPortConfigStage.initModality(Modality.APPLICATION_MODAL);
            comPortConfigStage.setOpacity(1);
            String selectedComPort = comPortNameCombo.getSelectionModel().getSelectedItem();

            // todo Save data about com port to the label properties
            fxmlComPortConfigController.getComPortLabel().getProperties().put("selectedComPort", selectedComPort);

             SerialPortObject serialPortObject = (SerialPortObject)observableComPortMapProps.get(selectedComPort);

             fxmlComPortConfigController.getBaudRateCombo().setValue(serialPortObject.getBaudRate().getLabel());
             System.out.println(serialPortObject.getBaudRate().getLabel());
             fxmlComPortConfigController.getDataBitsCombo().setValue( serialPortObject.getDatabits().getLabel());
             fxmlComPortConfigController.getStopBitsCombo().setValue(serialPortObject.getStopbits().getLabel());
             fxmlComPortConfigController.getParityCombo().setValue(serialPortObject.getParity().getLabel());


            /**
             *
             */
             portConfigScene.setOnMousePressed(new EventHandler<MouseEvent>() {
                 @Override
                 public void handle(MouseEvent event) {

                 }
             });

            portConfigScene.setOnMousePressed((MouseEvent e) -> {
                {
                    dragOffSetX = e.getScreenX() - comPortConfigStage.getX();
                    dragOffSetY = e.getScreenY() - comPortConfigStage.getY();
                }
            });

            portConfigScene.setOnMouseDragged((MouseEvent e) -> {
                {
                    comPortConfigStage.setX(e.getScreenX() - dragOffSetX);
                    comPortConfigStage.setY(e.getScreenY() - dragOffSetY);
                }
            });
            comPortConfigStage.setResizable(false);
            comPortConfigStage.setScene(portConfigScene);
            comPortConfigStage.setTitle(selectedComPort + " Port config");
            comPortConfigStage.show();

        }



    }

    @FXML
    void handleOpenPortBtn(ActionEvent event) {

        String comPortName = comPortNameCombo.getValue();

      if(comPortName != null) {
          // получаем набор свойств для выбранного ком порта
          SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(comPortName);

          try {
              // пытаемся открыть выбранный компорт
              serialPortObject.openPort();

              serialPortObject.setParams();
             //  устанавливаем свойство
            //  serialPortObject.isOpenedProperty().set(true);
              exceptionLabel.setText("");
          } catch (SerialPortException e) {
              // выводим сообщение об ошибке если кинуто исключение
              exceptionLabel.setText(e.getExceptionType());
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

       Iterator<String> iterator = comPortSet.iterator();

        while(iterator.hasNext()){
            String cps = iterator.next();
            if(!currentComPortSet.contains(cps)){
                iterator.remove();
                observableComPortMapProps.remove(cps);
            }

        }
          comPortSet.addAll(currentComPortSet);

    }

    public AnchorPane getView() {
        return mainView;
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

    public void addIsComPortOpenListener(SerialPortObject serialPortObject){
      serialPortObject.isOpenPortProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true){
                    circleOpenPortInfo.setFill(Paint.valueOf("green"));
                }else{
                    circleOpenPortInfo.setFill(Paint.valueOf("red"));
                }
            }
        });

   }


    @FXML
    void handlePortConfigBtn(ActionEvent event) {
        if (!isComPortSelect()){
            exceptionLabel.setText("Please, select COM port!");

        }else{
            Stage comPortConfigStage =  new Stage(StageStyle.UTILITY);
            comPortConfigStage.initModality(Modality.APPLICATION_MODAL);
            comPortConfigStage.setOpacity(1);
            String selectedComPort = comPortNameCombo.getSelectionModel().getSelectedItem();

            // todo Save data about com port to the label properties
            fxmlComPortConfigController.getComPortLabel().getProperties().put("selectedComPort", selectedComPort);

            SerialPortObject serialPortObject = (SerialPortObject)observableComPortMapProps.get(selectedComPort);

            fxmlComPortConfigController.getBaudRateCombo().setValue(serialPortObject.getBaudRate().getLabel());
            System.out.println(serialPortObject.getBaudRate().getLabel());
            fxmlComPortConfigController.getDataBitsCombo().setValue( serialPortObject.getDatabits().getLabel());
            fxmlComPortConfigController.getStopBitsCombo().setValue(serialPortObject.getStopbits().getLabel());
            fxmlComPortConfigController.getParityCombo().setValue(serialPortObject.getParity().getLabel());


            /**
             *
             */
            portConfigScene.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                }
            });

            portConfigScene.setOnMousePressed((MouseEvent e) -> {
                {
                    dragOffSetX = e.getScreenX() - comPortConfigStage.getX();
                    dragOffSetY = e.getScreenY() - comPortConfigStage.getY();
                }
            });

            portConfigScene.setOnMouseDragged((MouseEvent e) -> {
                {
                    comPortConfigStage.setX(e.getScreenX() - dragOffSetX);
                    comPortConfigStage.setY(e.getScreenY() - dragOffSetY);
                }
            });
            comPortConfigStage.setResizable(false);
            comPortConfigStage.setScene(portConfigScene);
            comPortConfigStage.setTitle(selectedComPort + " Port config");
            comPortConfigStage.show();

        }



    }

    @FXML
    void handleClosePortBtn(ActionEvent event) {
        String comPortName = comPortNameCombo.getValue();

        if(comPortName != null) {
            // получаем набор свойств для выбранного ком порта
            SerialPortObject serialPortObject = (SerialPortObject) observableComPortMapProps.get(comPortName);

            try {
                // пытаемся открыть выбранный компорт
                serialPortObject.closePort();
                exceptionLabel.setText("");
            } catch (SerialPortException e) {
                exceptionLabel.setText(e.getExceptionType());

            }
        }
    }
}

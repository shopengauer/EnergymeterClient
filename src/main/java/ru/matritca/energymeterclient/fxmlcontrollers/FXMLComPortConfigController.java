package ru.matritca.energymeterclient.fxmlcontrollers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import ru.matritca.energymeterclient.domain.SerialPortObject;
import ru.matritca.energymeterclient.serialportproperties.Baudrate;
import ru.matritca.energymeterclient.serialportproperties.Databits;
import ru.matritca.energymeterclient.serialportproperties.Parity;
import ru.matritca.energymeterclient.serialportproperties.Stopbits;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by vasiliy on 15.06.15.
 */

public class FXMLComPortConfigController implements Initializable,ApplicationEventPublisherAware {


    private ApplicationEventPublisher publisher;

    @FXML
    private AnchorPane view;


    @FXML
    private ComboBox<String> baudRateCombo;
    @FXML
    private ComboBox<String> dataBitsCombo;
    @FXML
    private ComboBox<String> stopBitsCombo;
    @FXML
    private ComboBox<String> parityCombo;
    @FXML
    private Button closeBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Label infoPortLabel;





    @Autowired
    private FXMLMainController fxmlMainController;

    private ObservableMap<Object,Object> observableComPortMapProps;


    private Logger logger = LoggerFactory.getLogger(FXMLComPortConfigController.class);

    @FXML
    void handleCloseBtn(ActionEvent event) {
       Stage stage = (Stage)view.getScene().getWindow();
       stage.close();
    }

    @FXML
    void handleResetBtn(ActionEvent event) {
       // System.out.println((String) comPortLabel.getProperties().get("com"));
       // comPortLabel.setText((String) comPortLabel.getProperties().get("com"));
    }

    @FXML
    void handleSetBtn(ActionEvent event) {
        /**
         * Set new value to the comport properties
         */
        observableComPortMapProps =  fxmlMainController.getObservableComPortMapProps();
        String selectedComPort =  (String)getComPortLabel().getProperties().get("selectedComPort");
        SerialPortObject sendSerialPortObject = (SerialPortObject)observableComPortMapProps.get(selectedComPort);


        Baudrate newBaudrate = Baudrate.getBaudrateByLabel(baudRateCombo.getValue());
        System.out.println(newBaudrate.getLabel());
        Databits newDatabits = Databits.getDatabitsByLabel(dataBitsCombo.getValue());
        Stopbits newStopbits = Stopbits.getStopbitsByLabel(stopBitsCombo.getValue());
        Parity newParity = Parity.getParityByLabel(parityCombo.getValue());

        //SerialPortObject newSerialPortObject =  new SerialPortObject(new SerialPort(selectedComPort),newBaudrate,newDatabits,newStopbits,newParity);
        try {
             // можно устанавливать только если компорт открыт
            sendSerialPortObject.getSerialPort().setParams(newBaudrate.getIntValue(), newDatabits.getIntValue(), newStopbits.getIntValue(), newParity.getIntValue());
            sendSerialPortObject.setBaudRate(newBaudrate);
            sendSerialPortObject.setDatabits(newDatabits);
            sendSerialPortObject.setStopbits(newStopbits);
            sendSerialPortObject.setParity(newParity);


        }catch (SerialPortException e){
            System.out.println(e.getExceptionType());
            // если компорт закрыт
            sendSerialPortObject.setBaudRate(newBaudrate);
            sendSerialPortObject.setDatabits(newDatabits);
            sendSerialPortObject.setStopbits(newStopbits);
            sendSerialPortObject.setParity(newParity);

        }
        observableComPortMapProps.replace(selectedComPort, sendSerialPortObject);


       // System.out.println((String) comPortLabel.getProperties().get("com"));
        // comPortLabel.setText((String) comPortLabel.getProperties().get("com"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {

    }

    @PostConstruct
    private void init(){
        /**
         * Init comport config comboboxes
         */


        ObservableList<String> olBaudRate = FXCollections.observableArrayList();
        olBaudRate.addAll(Baudrate.BAUDRATE_1200.getLabel(),
                Baudrate.BAUDRATE_4800.getLabel(),
                Baudrate.BAUDRATE_9600.getLabel(),
                Baudrate.BAUDRATE_14400.getLabel(),
                Baudrate.BAUDRATE_19200.getLabel());
        baudRateCombo.setItems(olBaudRate);

        ObservableList<String> olDatabits = FXCollections.observableArrayList();
        olDatabits.addAll(Databits.DATABITS_5.getLabel(),
                Databits.DATABITS_6.getLabel(),
                Databits.DATABITS_7.getLabel(),
                Databits.DATABITS_8.getLabel());
        dataBitsCombo.setItems(olDatabits);

        ObservableList<String> olStopbits = FXCollections.observableArrayList();
        olStopbits.addAll(Stopbits.STOPBITS_1.getLabel(),
                Stopbits.STOPBITS_1_5.getLabel(),
                Stopbits.STOPBITS_2.getLabel());
        stopBitsCombo.setItems(olStopbits);

        ObservableList<String> olParity = FXCollections.observableArrayList();
        olParity.addAll(Parity.PARITY_NONE.getLabel(),
                Parity.PARITY_ODD.getLabel(),
                Parity.PARITY_SPACE.getLabel(),
                Parity.PARITY_MARK.getLabel(),
                Parity.PARITY_EVEN.getLabel());
        parityCombo.setItems(olParity);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public AnchorPane getView() {
        return view;
    }

    public Label getComPortLabel() {
        return infoPortLabel;
    }

    public ComboBox<String> getBaudRateCombo() {
        return baudRateCombo;
    }

    public void setBaudRateCombo(ComboBox<String> baudRateCombo) {
        this.baudRateCombo = baudRateCombo;
    }

    public ComboBox<String> getDataBitsCombo() {
        return dataBitsCombo;
    }

    public ComboBox<String> getStopBitsCombo() {
        return stopBitsCombo;
    }

    public ComboBox<String> getParityCombo() {
        return parityCombo;
    }
}

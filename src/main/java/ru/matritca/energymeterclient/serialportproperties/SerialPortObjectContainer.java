package ru.matritca.energymeterclient.serialportproperties;

import javafx.collections.ObservableMap;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vasiliy on 10.06.2015.
 */
@Component
public class SerialPortObjectContainer {

    private Logger logger = LoggerFactory.getLogger(SerialPortObjectContainer.class);

    private ObservableMap<Object,Object> observableComPortMapProps;

    public ObservableMap<Object, Object> getObservableComPortMapProps() {
        return observableComPortMapProps;
    }

    public void setObservableComPortMapProps(ObservableMap<Object, Object> observableComPortMapProps) {
        this.observableComPortMapProps = observableComPortMapProps;
    }
}

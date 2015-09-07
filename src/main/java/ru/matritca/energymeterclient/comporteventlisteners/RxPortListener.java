package ru.matritca.energymeterclient.comporteventlisteners;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.matritca.energymeterclient.domain.SerialPortObject;
import ru.matritca.energymeterclient.serialportproperties.SerialPortObjectContainer;

/**
 * Created by Vasiliy on 07.09.2015.
 */
@Component
public class RxPortListener implements SerialPortEventListener{

    private Logger logger = LoggerFactory.getLogger(RxPortListener.class);

    @Autowired
    private SerialPortObjectContainer serialPortObjectContainer;

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
         logger.info("{}",serialPortEvent.getEventType());
         logger.info("{}",serialPortEvent.getEventValue());
         logger.info("{}",serialPortEvent.getPortName());

        if(serialPortEvent.isRXCHAR()){
        SerialPortObject serialPortObject = (SerialPortObject)serialPortObjectContainer.getObservableComPortMapProps().get(serialPortEvent.getPortName());
            try {
                String data =  serialPortObject.getSerialPort().readString();
                logger.info("Data recieve {}",data);
            } catch (SerialPortException e) {
                logger.error(e.getExceptionType());

            }

        }
    }
}

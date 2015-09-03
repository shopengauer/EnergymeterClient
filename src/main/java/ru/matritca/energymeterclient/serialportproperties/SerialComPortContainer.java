package ru.matritca.energymeterclient.serialportproperties;

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
public class SerialComPortContainer {

    private Logger logger = LoggerFactory.getLogger(SerialComPortContainer.class);

    private List<SerialPort> serialPorts = new ArrayList<>();

    public void addSerialPort(SerialPort serialPort) {
        serialPorts.add(serialPort);
    }

//    public void deleteSerialPort(SerialPort serialPort) {
//        serialPorts.remove(serialPort);
//    }

    public List<SerialPort> getSerialPorts() {
        return serialPorts;
    }

    public SerialPort getSerialPortByComNumber(String comPortNumber){

        Iterator<SerialPort> serialPortIterator = serialPorts.iterator();
        SerialPort sp = null;
        while(serialPortIterator.hasNext())
        {
            sp = serialPortIterator.next();
            if(sp.getPortName().equals(comPortNumber)){
                return sp;
            }
        }

        return null;
    }

    public void closeAllPorts(){
        Iterator<SerialPort> serialPortIterator = serialPorts.iterator();
        SerialPort sp = null;
        while(serialPortIterator.hasNext())
        {
            sp = serialPortIterator.next();
            if(sp.isOpened()){
                try {
                    sp.closePort();
                } catch (SerialPortException e) {
                    logger.error("Error close com port: {}", sp.getPortName());
                }
            }
        }

    }



}

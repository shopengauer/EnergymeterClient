package ru.matritca.energymeterclient.domain;

/**
 * Created by Vasiliy on 18.06.2015.
 */

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import jssc.SerialPort;
import jssc.SerialPortException;
import ru.matritca.energymeterclient.serialportproperties.Baudrate;
import ru.matritca.energymeterclient.serialportproperties.Databits;
import ru.matritca.energymeterclient.serialportproperties.Parity;
import ru.matritca.energymeterclient.serialportproperties.Stopbits;

public class SerialPortObject {

    private SerialPort serialPort;
    private String portname;
    private Baudrate baudRate;
    private Databits databits;
    private Stopbits stopbits;
    private Parity parity;

    private ReadOnlyBooleanWrapper isOpenPort = new ReadOnlyBooleanWrapper(this,"isOpenPort",false);

    public SerialPortObject(String portname) {
        this.portname = portname;
        serialPort = new SerialPort(portname);
        baudRate = Baudrate.BAUDRATE_9600;
        databits = Databits.DATABITS_8;
        stopbits = Stopbits.STOPBITS_1;
        parity = Parity.PARITY_NONE;

    }

    public SerialPortObject(String portname, Baudrate baudRate, Databits databits, Stopbits stopbits, Parity parity) {
        this.portname = portname;
        serialPort = new SerialPort(portname);
        this.baudRate = baudRate;
        this.databits = databits;
        this.stopbits = stopbits;
        this.parity = parity;
    }


    public boolean isOpened() {
        return serialPort.isOpened();
    }

    public void setParams() throws SerialPortException {
        serialPort.setParams(this.baudRate.getIntValue(), this.baudRate.getIntValue(),
                this.stopbits.getIntValue(), this.parity.getIntValue());
    }


    public void openPort() throws SerialPortException {
       serialPort.openPort();
       isOpenPort.set(true);
    }

    public void closePort() throws SerialPortException {
        serialPort.closePort();
        isOpenPort.set(false);
    }


    public ReadOnlyBooleanProperty isOpenPortProperty() {
        return isOpenPort.getReadOnlyProperty();
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SerialPortObject)) return false;

        SerialPortObject that = (SerialPortObject) o;

        return getPortname().equals(that.getPortname());

    }

    @Override
    public int hashCode() {
        return getPortname().hashCode();
    }



    public String getPortname() {
        return portname;
    }
    public void setPortname(String portname) {
        this.portname = portname;
    }

    public Baudrate getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Baudrate baudRate) {
        this.baudRate = baudRate;
    }

    public Databits getDatabits() {
        return databits;
    }

    public void setDatabits(Databits databits) {
        this.databits = databits;
    }

    public Stopbits getStopbits() {
        return stopbits;
    }

    public void setStopbits(Stopbits stopbits) {
        this.stopbits = stopbits;
    }

    public Parity getParity() {
        return parity;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

}

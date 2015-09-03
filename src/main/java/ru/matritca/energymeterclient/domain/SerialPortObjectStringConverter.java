package ru.matritca.energymeterclient.domain;

import javafx.util.StringConverter;

/**
 * Created by Vasiliy on 03.09.2015.
 */
public class SerialPortObjectStringConverter extends StringConverter<SerialPortObject> {

    @Override
    public String toString(SerialPortObject object) {
        return object.getPortname() + "  " + (object.isOpened() ? "open" : "close");
    }

    @Override
    public SerialPortObject fromString(String string) {
        return null;
    }
}

package ru.matritca.energymeterclient.serialportproperties;

/**
 * Created by Vasiliy on 18.06.2015.
 */
public enum Baudrate {

    BAUDRATE_110(110, "110"),
    BAUDRATE_300(300, "300"),
    BAUDRATE_600(600, "600"),
    BAUDRATE_1200(1200, "1200"),
    BAUDRATE_4800(4800, "4800"),
    BAUDRATE_9600(9600, "9600"),
    BAUDRATE_14400(14400, "14400"),
    BAUDRATE_19200(19200, "19200"),
    BAUDRATE_38400(38400, "38400"),
    BAUDRATE_57600(57600, "57600"),
    BAUDRATE_115200(115200, "115200"),
    BAUDRATE_128000(128000, "128000"),
    BAUDRATE_256000(256000, "256000");

    private int intValue;
    private String label;

    private Baudrate(int intValue,String label) {
        this.label = label;
        this.intValue = intValue;
    }

    public String getLabel() {
        return label;
    }

    public int getIntValue() {
        return intValue;
    }

    public static int getValueByLabel(String label){

        Baudrate[] values = Baudrate.values();
        for (Baudrate s : values) {
            if(label.equals(s.getLabel())){
                return s.getIntValue();
            }
        }
        return 9600;
    }


    public static Baudrate getBaudrateByLabel(String label){
        Baudrate[] values = Baudrate.values();
        for (Baudrate s : values) {
            if(label.equals(s.getLabel())){
                return s;
            }
        }
        return BAUDRATE_9600;
    }
}

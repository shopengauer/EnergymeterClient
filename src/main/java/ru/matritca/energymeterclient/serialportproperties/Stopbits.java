package ru.matritca.energymeterclient.serialportproperties;

/**
 * Created by Vasiliy on 22.06.2015.
 */
public enum Stopbits {

    STOPBITS_1(1,"1"),
    STOPBITS_2(2,"2"),
    STOPBITS_1_5(3,"1.5");

    private int intValue;
    private String label;


    Stopbits(int intValue,String label) {
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

      Stopbits[] values = Stopbits.values();
        for (Stopbits s : values) {
             if(label.equals(s.getLabel())){
                 return s.getIntValue();
             }
        }
       return 1;
    }

    public static Stopbits getStopbitsByLabel(String label){

      Stopbits[] values = Stopbits.values();
        for (Stopbits s : values) {
             if(label.equals(s.getLabel())){
                 return s;
             }
        }
       return STOPBITS_1;
    }
}

package ru.matritca.energymeterclient.serialportproperties;

/**
 * Created by Vasiliy on 22.06.2015.
 */
public enum Databits {

    DATABITS_5(5, "5"),
    DATABITS_6(6, "6"),
    DATABITS_7(7, "7"),
    DATABITS_8(8, "8");


    private int intValue;
    private String label;

    Databits(int intValue, String label) {
        this.intValue = intValue;
        this.label = label;
    }

    public int getIntValue() {
        return intValue;
    }

    public String getLabel() {
        return label;
    }

    public static int getValueByLabel(String label){

        Databits[] values = Databits.values();
        for (Databits s : values) {
            if(label.equals(s.getLabel())){
                return s.getIntValue();
            }
        }
        return 8;
    }

    public static Databits getDatabitsByLabel(String label){

        Databits[] values = Databits.values();
        for (Databits s : values) {
            if(label.equals(s.getLabel())){
                return s;
            }
        }
        return DATABITS_8;
    }


}

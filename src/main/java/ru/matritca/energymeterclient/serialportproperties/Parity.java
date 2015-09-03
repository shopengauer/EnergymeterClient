package ru.matritca.energymeterclient.serialportproperties;

/**
 * Created by Vasiliy on 23.06.2015.
 */
public enum Parity {

    PARITY_NONE(0,"None"),PARITY_ODD(1,"Even"),
    PARITY_EVEN(2,"Even"),PARITY_MARK(3,"Mark"),PARITY_SPACE(4,"Space");

    private int intValue;
    private String label;

    Parity(int intValue, String label) {
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

        Parity[] values = Parity.values();
        for (Parity s : values) {
            if(label.equals(s.getLabel())){
                return s.ordinal();
            }
        }
        return 0;
    }

    public static Parity getParityByLabel(String label){

        Parity[] values = Parity.values();
        for (Parity s : values) {
            if(label.equals(s.getLabel())){
                return s;
            }
        }
        return PARITY_NONE;
    }

}

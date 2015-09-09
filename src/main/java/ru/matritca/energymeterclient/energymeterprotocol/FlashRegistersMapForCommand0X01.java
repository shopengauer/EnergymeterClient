package ru.matritca.energymeterclient.energymeterprotocol;

/**
 * Created by Vasiliy on 07.09.2015.
 */
public enum FlashRegistersMapForCommand0X01 {

    ENERGY_METER_TYPE((byte)0x00,1,"Тип счетчика"),
    CURRENT_SENSITIVITY((byte)0x01,1,"Чувствительность тока (0.001А)"),
    VOLTAGE_SENSITIVITY((byte)0x02,2,"Чувсвительность напряжения (0.01В)"),
    RATED_VOLTAGE((byte)0x03,2,"Номинальное напряжение"),
    POWER_COEFF((byte)0x04,2,"Коэффициент мощности"),
    PHASE_CORRECTION_CURRENT_CHANNEL((byte)0x05,2,"Фазавая коррекция канала тока"),
    VOLTAGE_COEFF((byte)0x06,2,"Коэффициент напряжения"),
    TEMPERATURE_SENSOR_COEFF((byte)0x07,2,"Коэффициент температурного датчика"),
    DC_OFFSET_TEMPERATURE_SENSOR((byte)0x08,2,"DC смещение температурного датчика"),
    BATTERY_VOLTAGE_COEFF((byte)0x09,2,"Коэффициент напряжения на батарее"),
    BATTERY_THRESHOLD_VOLTAGE((byte)0x0a,2,"Порог напряжения на батарее (0.01В)"),
    CONSTATANT_FREQ_ERROR_32768((byte)0x0b,2,"Постоянная ошибка частоты 32768Гц"),
    CONFIG_REGISTER((byte)0x0c,1,"Апаратный регистр конфигурации"),
    INDICATION_COFIG_REGISTER((byte)0x0d,1,"Регистр конфигурации индикации"),
    FACTORY_WRITE_PASSWORD((byte)0x0e,4,"Заводской пароль на запись"),
    FACTORY_ID((byte)0x0f,4,"Заводской номер"),
    DELAY_BETWEEN_BYTES_IN_PACKET((byte)0x10,2,"Дополнительная задержка между байтами в пакете"),
    DAILY_TIME_COMPENSATION((byte)0x11,2,"Суточная коррекция времени");


    private byte address;
    private int responseLength;
    private String description;


    // CONFIG_REGISTER
    public static final int CONFIG_REGISTER_Button_Mask = 1;  // кнопка (0 - есть/1 - нет)
    public static final int CONFIG_REGISTER_Electronic_Seal1_Mask = 2; // ЭП1 (0 - есть/1 - нет)
    public static final int CONFIG_REGISTER_Electronic_Seal2_Mask = 4;// ЭП2 (0 - есть/1 - нет)
    public static final int CONFIG_REGISTER_Lightening = 8; // подсветка (0 - есть/1 - нет)

    // Регистр конфинурации индикации(индикация энергии единицы младшего разряда)
    public static final int INDICATION_COFIG_REGISTER_0_01kwth_Mask = 3; // 0.01кВт*ч
    public static final int INDICATION_COFIG_REGISTER_0_1kwth_Mask = 2; // 0.1кВт*ч
    public static final int INDICATION_COFIG_REGISTER_1kwth_Mask = 1; // 1кВт*ч

    FlashRegistersMapForCommand0X01(byte address, int responseLength, String description) {
       this.address = address;
       this.responseLength = responseLength;
       this.description = description;

    }

    public byte getAddress() {
        return address;
    }

    public void setAddress(byte address) {
        this.address = address;
    }

    public int getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(int responseLength) {
        this.responseLength = responseLength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

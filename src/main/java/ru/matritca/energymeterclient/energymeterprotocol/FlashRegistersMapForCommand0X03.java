package ru.matritca.energymeterclient.energymeterprotocol;

import org.springframework.stereotype.Component;

/**
 * Created by Vasiliy on 08.09.2015.
 */

public enum FlashRegistersMapForCommand0X03 {

    PROGRAMM_CONFIG_REGISTER((byte)0x00,1,"Программный регистр конфигурации"),
    TIME_OF_INDICATION_MODE_BY_BUTTON((byte)0x01,1,"Время режима индикации по кнопке (сек)"),
    MAXIMUM_CURRENT((byte)0x02,4,"Максимальный ток (RMS)"),
    MAXIMUM_OPERATING_VOLTAGE((byte)0x03,2,"Максимальное рабочее напряжение (RMS)"),
    MINIMUM_OPERATING_VOLTAGE((byte)0x04,2,"Минимальное рабочее напряжение (RMS)"),
    INDICATION_MODES((byte)0x05,24,"Режимы индикации"),
    INDICATION_MODES_BY_BUTTON((byte)0x06,12,"Режимы индикации по кнопке"),
    POWER_PROFILE_TYPE((byte)0x07,1,"Тип сохраняемого профиля мощности"),
    NUMBER_OF_TARIFFS_IN_TARIFFICATOR((byte)0x08,1,"Число тарифов в тарификаторе"),
    PASSWORD_FOR_READING((byte)0x09,4,"Пароль на чтение"),
    PASSWORD_FOR_WRITING((byte)0x0a,4,"Пароль на запись"),
    DEFAULT_DATE((byte)0x0b,6,"Дата по умолчанию");

    private byte address;
    private int responseLength;
    private String description;

    //PROGRAMM_CONFIG_REGISTER
    public static final int AUTOMATIC_TRANSLATION_TIME = 0x01; // Автоматический перевод времени лето/зима(0 - включено, 1 - выключено)
    public static final int INDICATION_FROM_BATTERY = 0x02; // Работа индикации от батарейки (0 - включено, 1 - выключено)
    public static final int WRONG_PASSWORD_CONTROL = 0x04; // Контроль неверных паролей (0 - включено, 1 - выключено)

    public static final int BUTTON_LIGHTENING = 0x08; // работа подсветки 01 - включена постоянно
    public static final int CONSTANT_LIGHTENING = 16; // 10 - включается по нажатию на кнопку
                                                      // выключается по выходу из режима индикации по кнопке
    public static final int OFF_LIGHTENING = 24; // 00 - выключена
    public static final int WORK_WITH_TARRIFFICATOR_IN_EEPROM = 32; // Рбота с тарификатором в EEPROM (0 - включено, 1 - выключено)
    public static final int WORK_WITH_PROFILES_IN_EEPROM = 64; // работа с профилями в EEPROM (0 - включено, 1 - выключено)

    // типы профилей мощностей POWER_PROFILE_TYPE
    public static final int POWER_PROFILE_1_MIN = 0x00;
    public static final int POWER_PROFILE_2_MIN = 0x01;
    public static final int POWER_PROFILE_3_MIN = 0x02;
    public static final int POWER_PROFILE_4_MIN = 0x03;
    public static final int POWER_PROFILE_5_MIN = 0x04;
    public static final int POWER_PROFILE_6_MIN = 0x05;
    public static final int POWER_PROFILE_10_MIN = 0x06;
    public static final int POWER_PROFILE_12_MIN = 0x07;
    public static final int POWER_PROFILE_15_MIN = 0x08;
    public static final int POWER_PROFILE_20_MIN = 0x09;
    public static final int POWER_PROFILE_30_MIN = 0x0a;
    public static final int POWER_PROFILE_60_MIN = 0x0b;

    //INDICATION_MODES
    public static final int INDICATION_MODE_DATE = 1;
    public static final int INDICATION_MODE_TIME = 2;
    public static final int INDICATION_MODE_CURRENT_RMS = 3;
    public static final int INDICATION_MODE_VOLTAGE_RMS = 4;
    public static final int INDICATION_MODE_ACTIVE_POWER = 5;
    public static final int INDICATION_MODE_REACTIVE_POWER = 6;
    public static final int INDICATION_MODE_VOLTAGE_FREQ = 7;
    public static final int INDICATION_MODE_POWER_COEFF = 8;
    public static final int INDICATION_MODE_ACTIVE_ENERGY_TARRIFF_0 = 9;
    public static final int INDICATION_MODE_ACTIVE_ENERGY_ACTIVE_TARRIFF = 10;
    public static final int INDICATION_MODE_REACTIVE_ENERGY_TARRIFF_0 = 11;
    public static final int INDICATION_MODE_TESTING = 12;
    public static final int INDICATION_MODE_SOFTWARE_VERSION_AND_CRC16 = 13;

    FlashRegistersMapForCommand0X03(byte address, int responseLength, String description) {
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

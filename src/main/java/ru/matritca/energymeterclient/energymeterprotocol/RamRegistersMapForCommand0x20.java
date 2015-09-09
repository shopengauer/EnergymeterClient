package ru.matritca.energymeterclient.energymeterprotocol;

/**
 * Created by Vasiliy on 08.09.2015.
 */
public enum RamRegistersMapForCommand0x20 {

    DATE_TIME(0x00,7,"Дата/Время"),
    NUMBER_OF_TARIFFS_IN_TARIFFCATOR(0x01,1,"Число тарифов в тарификаторе"),
    CURRENT_TARIFF(0x02,1,"Текущий тариф"),
    ACTIVE_ENERGY_TARIFF_0(0x03,4,"Активная энергия по 0 тарифу"),
    ACTIVE_ENERGY_TARIFF_1(0x04,4,"Активная энергия по 1 тарифу"),
    ACTIVE_ENERGY_TARIFF_2(0x05,4,"Активная энергия по 2 тарифу"),
    ACTIVE_ENERGY_TARIFF_3(0x06,4,"Активная энергия по 3 тарифу"),
    ACTIVE_ENERGY_TARIFF_4(0x07,4,"Активная энергия по 4 тарифу"),
    REACTIVE_ENERGY_TARIFF_0(0x08,4,"Реактивная энергия по 0 тарифу"),
    RMS_CURRENT(0x09,4,"RMS тока"),
    RMS_VOLTAGE(0x0a,2,"RMS напряжения"),
    ACTIVE_POWER(0x0b,4,"Активная мощность"),
    REACTIVE_POWER(0x0c,4,"Реактивная мощность"),
    SUM_POWER(0x0d,4,"Полная мощность"),
    POWER_COEFF(0x0e,2,"Коэффициент мощности"),
    VOLTAGE_FREQ(0x0f,2,"Чатота сетевого напряжения"),
    NUMBER_OF_MEASURE_FROM_INIT(0x10,1,"Номер измерения от инициализации"),
    TEMPERATURE(0x11,1,"Температура"),
    BATTERY_VOLTAGE(0x12,2,"Напряжение на батарее"),
    TIME_SECONDS(0x13,4,"Время наработки"),
    WRITE_PARAMETERS_PAGE_FLASH1(0x14,8,"Параметры записи страницы Flash1"),
    WRITE_PARAMETERS_PAGE_FLASH2(0x15,8,"Параметры записи страницы Flash2"),
    WRITE_PARAMETERS_PAGE_FLASH3(0x16,8,"Параметры записи страницы Flash3"),
    USER_STATUS_REGISTER(0x17,2,"Регистр статусов для пользователя");

    private int address;
    private int responseLength;
    private String description;




    RamRegistersMapForCommand0x20(int address, int responseLength, String description) {
        this.address = address;
        this.responseLength = responseLength;
        this.description = description;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
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

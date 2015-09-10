package ru.matritca.energymeterclient.energymeterprotocol;

/**
 * Created by Vasiliy on 09.09.2015.
 */
public enum EmeterRegisters {

    ENERGY_METER_TYPE((byte)0x00,1,"Тип счетчика"),
    CURRENT_SENSITIVITY((byte)0x01,1,"Чувствительность тока (0.001А)"),
    VOLTAGE_SENSITIVITY((byte)0x02,2,"Чувсвительность напряжения (0.01В)"),
    RATED_VOLTAGE((byte)0x03,2,"Номинальное напряжение"),
    POWER_COEFF_FLASH((byte)0x04,2,"Коэффициент мощности"),
    PHASE_CORRECTION_CURRENT_CHANNEL((byte)0x05,2,"Фазавая коррекция канала тока"),
    VOLTAGE_COEFF((byte)0x06,2,"Коэффициент напряжения"),
    TEMPERATURE_SENSOR_COEFF((byte)0x07,2,"Коэффициент температурного датчика"),
    DC_OFFSET_TEMPERATURE_SENSOR((byte)0x08,2,"DC смещение температурного датчика"),
    BATTERY_VOLTAGE_COEFF((byte)0x09,2,"Коэффициент напряжения на батарее"),
    BATTERY_THRESHOLD_VOLTAGE((byte)0x0a,2,"Порог напряжения на батарее (0.01В)"),
    CONSTATANT_FREQ_ERROR_32768((byte)0x0b,2,"Постоянная ошибка частоты 32768Гц"),
    CONFIG_REGISTER((byte)0x0c,1,"Апаратный регистр конфигурации"),
    INDICATION_CONFIG_REGISTER((byte)0x0d,1,"Регистр конфигурации индикации"),
    FACTORY_WRITE_PASSWORD((byte)0x0e,4,"Заводской пароль на запись"),
    FACTORY_ID((byte)0x0f,4,"Заводской номер"),
    DELAY_BETWEEN_BYTES_IN_PACKET((byte)0x10,2,"Дополнительная задержка между байтами в пакете"),
    DAILY_TIME_COMPENSATION((byte)0x11,2,"Суточная коррекция времени"),

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
    DEFAULT_DATE((byte)0x0b,6,"Дата по умолчанию"),

    DATE_TIME((byte)0x00,7,"Дата/Время"),
    NUMBER_OF_TARIFFS_IN_TARIFFCATOR((byte)0x01,1,"Число тарифов в тарификаторе"),
    CURRENT_TARIFF((byte)0x02,1,"Текущий тариф"),
    ACTIVE_ENERGY_TARIFF_0((byte)0x03,4,"Активная энергия по 0 тарифу"),
    ACTIVE_ENERGY_TARIFF_1((byte)0x04,4,"Активная энергия по 1 тарифу"),
    ACTIVE_ENERGY_TARIFF_2((byte)0x05,4,"Активная энергия по 2 тарифу"),
    ACTIVE_ENERGY_TARIFF_3((byte)0x06,4,"Активная энергия по 3 тарифу"),
    ACTIVE_ENERGY_TARIFF_4((byte)0x07,4,"Активная энергия по 4 тарифу"),
    REACTIVE_ENERGY_TARIFF_0((byte)0x08,4,"Реактивная энергия по 0 тарифу"),
    RMS_CURRENT((byte)0x09,4,"RMS тока"),
    RMS_VOLTAGE((byte)0x0a,2,"RMS напряжения"),
    ACTIVE_POWER((byte)0x0b,4,"Активная мощность"),
    REACTIVE_POWER((byte)0x0c,4,"Реактивная мощность"),
    SUM_POWER((byte)0x0d,4,"Полная мощность"),
    POWER_COEFF_RAM((byte)0x0e,2,"Коэффициент мощности"),
    VOLTAGE_FREQ((byte)0x0f,2,"Чатота сетевого напряжения"),
    NUMBER_OF_MEASURE_FROM_INIT((byte)0x10,1,"Номер измерения от инициализации"),
    TEMPERATURE((byte)0x11,1,"Температура"),
    BATTERY_VOLTAGE((byte)0x12,2,"Напряжение на батарее"),
    TIME_SECONDS((byte)0x13,4,"Время наработки"),
    WRITE_PARAMETERS_PAGE_FLASH1((byte)0x14,8,"Параметры записи страницы Flash1"),
    WRITE_PARAMETERS_PAGE_FLASH2((byte)0x15,8,"Параметры записи страницы Flash2"),
    WRITE_PARAMETERS_PAGE_FLASH3((byte)0x16,8,"Параметры записи страницы Flash3"),
    USER_STATUS_REGISTER((byte)0x17,2,"Регистр статусов для пользователя");



    private byte address;
    private int registerLength;
    private String description;

    EmeterRegisters(byte address, int registerLength, String description) {
        this.address = address;
        this.registerLength = registerLength;
        this.description = description;
    }

    public byte getAddress() {
        return address;
    }

    public int getRegisterLength() {
        return registerLength;
    }
}

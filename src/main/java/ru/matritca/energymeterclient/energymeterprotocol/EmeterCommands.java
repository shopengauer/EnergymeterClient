package ru.matritca.energymeterclient.energymeterprotocol;

/**
 * Created by Vasiliy on 07.09.2015.
 */
public enum EmeterCommands {

    CHANNEL_TEST((byte)0x00,"Тест канала связи"),
    READ_FLASH_BY_ID((byte)0x01,"Читать данные из Flash по id"),
    WRITE_FLASH_BY_ID((byte)0x02,"Записать данные во Flash по id"),
    READ_DEFAULT_CONFIG_FROM_FLASH_BY_ID((byte)0x03,"Читать конфигурацию по умолчанию из Flash по Id"),
    WRITE_DEFAULT_CONFIG_TO_FLAH_BY_ID((byte)0x04,"Записать конфигурацию по умолчанию из Flash по Id"),
    READ_CONFIG_FROM_RAM_BY_ID((byte)0x05,"Читать конфигурацию из ОЗУ"),
    WRITE_CONFIG_TO_RAM_BY_ID((byte)0x06,"Записать конфигурацию в ОЗУ"),
    READ_EXTRA_INFO_FROM_FLASH((byte)0x07,"Читать дополнительную информацию из Flash"),
    WRITE_EXTRA_INFO_TO_FLASH((byte)0x08,"Записать дополнительную информацию во Flash"),
    READ_EXTRA_INFO_FROM_EEPROM((byte)0x09,"Читать дополнительную информацию из EEPROM"),
    WRITE_EXTRA_INFO_TO_EEPROM_BY_ID((byte)0x0a,"Записать дополнительную информацию из EEPROM"),
    READ_TARIFFICATOR_FROM_EEPROM((byte)0x0b,"Читать тарификатор из EEPROM"),
    WRITE_TARIFFICATOR_TO_EEPROM((byte)0x0c,"Записать тарификатор в EEPROM"),

    READ_DATA_FROM_RAM_BY_ID((byte)0x20,"Читать данные из ОЗУ по Id"),

    SET_CORRECT_DATE_TIME((byte)0x25,"Установить/корректировать дату/время"),
    ERASE_1X_PAGES_FROM_EEPROM_IN_PROFILE_REGION((byte)0x2a,"Стереть x1 страниц EEPROM в области профилей"),

    FIND_EVENTS_IN_DB((byte)0x30,"Поиск событий в БД"),
    FIND_SLICES_IN_DB((byte)0x31,"Поиск срезов в БД"),
    FIND_PROFILES_IN_DB((byte)0x32,"Поиск профиля в БД"),

    ERASE_OPPENING_ELECTRONIC_SEAL1_FLAG((byte)0x40,"Стереть признак вскрытия ЭП1"),
    ERASE_OPPENING_ELECTRONIC_SEAL2_FLAG((byte)0x41,"Стереть признак вскрытия ЭП2"),
    COMMUTATE_512HZ_TO_TELEMETRIC_OUTPUT((byte)0x42,"Включить на выход реактивной телеметрии частоту 512Гц"),

    READ_DATA_FROM_EEPROM((byte)0x50,"Читать данные из EEPROM"),
    WRITE_DATA_TO_EEPROM((byte)0x51,"Записать данные в EEPROM"),
    ERASE_1X_PAGES_FROM_EEPROM((byte)0x52,"Стереть x1 страниц EEPROM"),

    RESTART_MEASURER((byte)0x60,"Перезапустить измеритель"),
    RESET_BY_WD((byte)0x61,"Сброс по WD");

    private byte command;
    private String description;

    EmeterCommands(byte command, String description) {
        this.command = command;
        this.description = description;
     }

    public byte getCommand() {
        return command;
    }

    public void setCommand(byte command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

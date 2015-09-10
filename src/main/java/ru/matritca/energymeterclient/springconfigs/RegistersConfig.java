package ru.matritca.energymeterclient.springconfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.matritca.energymeterclient.energymeterprotocol.EmeterRegisters;
import ru.matritca.energymeterclient.registers.Register;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasiliy on 09.09.2015.
 */
@Configuration
public class RegistersConfig {

    @Bean
    public Register configRegister() {
        List<Integer> initialValues = new ArrayList<>();
        initialValues.add(0, 0xf0);
        Register register = new Register(EmeterRegisters.CONFIG_REGISTER,initialValues);
        register.defineRegisterBit("Button", 0, 0);
        register.defineRegisterBit("EP1", 0, 1);
        register.defineRegisterBit("EP2", 0, 2);
        register.defineRegisterBit("Lightening", 0, 3);
        return register;

    }

    @Bean
    public Register indicationConfigRegister() {
        List<Integer> initialValues = new ArrayList<>();
        initialValues.add(0, 0xfc);
        Register register = new Register(EmeterRegisters.INDICATION_CONFIG_REGISTER,initialValues);
        register.defineRegisterBit("EnergyIndication$LSb", 0, 0);
        register.defineRegisterBit("EnergyIndication$MSb", 0, 1);
        return register;
    }

    @Bean
    public Register programConfigRegister() {
        List<Integer> initialValues = new ArrayList<>();
        initialValues.add(0,0x80);
        Register register = new Register(EmeterRegisters.PROGRAMM_CONFIG_REGISTER,initialValues);
        register.defineRegisterBit("AutoSeason", 0, 0);
        register.defineRegisterBit("BatteryIndication", 0, 1);
        register.defineRegisterBit("WrongPasswordControl", 0, 2);
        register.defineRegisterBit("BacklightMode$LSb", 0, 3);
        register.defineRegisterBit("BacklightMode$MSb", 0, 4);
        register.defineRegisterBit("TarifficatorsEEPROM", 0, 5);
        register.defineRegisterBit("ProfilesEEPROM", 0, 6);
        register.setByteRegisterValue(0x80,0);
        return register;
    }

    @Bean
    public Register powerProfileTypeRegister() {
        List<Integer> initialValues = new ArrayList<>();
        initialValues.add(0,0x00);
        Register register = new Register(EmeterRegisters.POWER_PROFILE_TYPE,initialValues);
        register.defineRegisterByteValue("1$2883$15", 0x00);
        register.defineRegisterByteValue("2$1443$30", 0x01);
        register.defineRegisterByteValue("3$963$45", 0x02);
        register.defineRegisterByteValue("4$723$60", 0x03);
        register.defineRegisterByteValue("5$579$75", 0x04);
        register.defineRegisterByteValue("6$483$90", 0x05);
        register.defineRegisterByteValue("10$291$140", 0x06);
        register.defineRegisterByteValue("12$243$140", 0x07);
        register.defineRegisterByteValue("15$195$140", 0x08);
        register.defineRegisterByteValue("20$147$140", 0x09);
        register.defineRegisterByteValue("30$99$140", 0x0a);
        register.defineRegisterByteValue("60$51$140", 0x0b);
        return register;
    }

    @Bean
    public Register indicationModesRegister() {
        List<Integer> initialValues = new ArrayList<>(EmeterRegisters.INDICATION_MODES.getRegisterLength());
        initialValues.add(0,0x01);initialValues.add(1,0x0a);
        initialValues.add(2,0x02); initialValues.add(3,0x0a);
        initialValues.add(4,0x09);initialValues.add(5,0x0a);
        initialValues.add(6,0x0a);initialValues.add(7,0x0a);
        initialValues.add(8,0x0b);initialValues.add(9,0x0a);
        initialValues.add(10,0x00);initialValues.add(11,0x00);
        initialValues.add(12,0x00);initialValues.add(13,0x00);
        initialValues.add(14,0x00);initialValues.add(15,0x00);
        initialValues.add(16,0x00);initialValues.add(17,0x00);
        initialValues.add(18,0x00);initialValues.add(19,0x00);
        initialValues.add(20,0x00);initialValues.add(21,0x00);
        initialValues.add(22,0x00);initialValues.add(23,0x00);

        Register register = new Register(EmeterRegisters.INDICATION_MODES,initialValues);
        register.defineRegisterByteValue("endIndication",0x00);
        register.defineRegisterByteValue("Date$xx-xx-xx$1day",0x01);
        register.defineRegisterByteValue("Time$xx:xx$1min",0x02);
        register.defineRegisterByteValue("Curr$xxx.xxx$A$0.001A",0x03);
        register.defineRegisterByteValue("Volt$xxx.xx$V$0.01B",0x04);
        register.defineRegisterByteValue("AP$xxx.xxx$kW$0.001kBt",0x05);
        register.defineRegisterByteValue("RP$xxx.xxx$kvar$0.001kBAr",0x06);
        register.defineRegisterByteValue("FREQ$F_xx.xx$0.01Hz",0x07);
        register.defineRegisterByteValue("AE0T$xxxxxx(.)x(.)x_kWh$1(0.1,0.01)kWh",0x08);
        register.defineRegisterByteValue("AEET$xxxxxx(.)x(.)x_kWh$1(0.1,0.01)kWh",0x09);
        register.defineRegisterByteValue("RE0T$xxxxxx(.)x(.)x_kvarh$1(0.1,0.01)kvarh",0x0a);
        register.defineRegisterByteValue("test",0x0b);
        register.defineRegisterByteValue("SoftwareVersion+CRC16$x.xx.xx_xxxxx",0x0c);
        return register;
    }

    @Bean
    public Register indicationModesByButtonRegister(){
        List<Integer> initialValues = new ArrayList<>();
        initialValues.add(0,0x03);
        initialValues.add(1,0x04);
        initialValues.add(2,0x05);
        initialValues.add(3,0x06);
        initialValues.add(4,0x07);
        initialValues.add(5,0x08);
        initialValues.add(6,0x00);
        initialValues.add(7,0x00);
        initialValues.add(8,0x00);
        initialValues.add(9,0x00);
        initialValues.add(10,0x00);
        initialValues.add(11, 0x00);
        Register register = new Register(EmeterRegisters.INDICATION_MODES_BY_BUTTON,initialValues);
        register.defineRegisterByteValue("endIndication",0x00);
        register.defineRegisterByteValue("Date$xx-xx-xx$1day",0x01);
        register.defineRegisterByteValue("Time$xx:xx$1min",0x02);
        register.defineRegisterByteValue("Curr$xxx.xxx$A$0.001A",0x03);
        register.defineRegisterByteValue("Volt$xxx.xx$V$0.01B",0x04);
        register.defineRegisterByteValue("AP$xxx.xxx$kW$0.001kBt",0x05);
        register.defineRegisterByteValue("RP$xxx.xxx$kvar$0.001kBAr",0x06);
        register.defineRegisterByteValue("FREQ$F_xx.xx$0.01Hz",0x07);
        register.defineRegisterByteValue("AE0T$xxxxxx(.)x(.)x_kWh$1(0.1,0.01)kWh",0x08);
        register.defineRegisterByteValue("AEET$xxxxxx(.)x(.)x_kWh$1(0.1,0.01)kWh",0x09);
        register.defineRegisterByteValue("RE0T$xxxxxx(.)x(.)x_kvarh$1(0.1,0.01)kvarh",0x0a);
        register.defineRegisterByteValue("test",0x0b);
        register.defineRegisterByteValue("SoftwareVersion+CRC16$x.xx.xx_xxxxx", 0x0c);
       // register.setByteRegisterPredefinedValue("Curr$xxx.xxx$A$0.001A",0);

        return register;
     }

    @Bean
    public Register dateTimeRegister(){

       Register register = new Register(EmeterRegisters.DATE_TIME);
       register.defineRegisterByteValue("Second", 0);
       register.defineRegisterByteValue("Minute", 1);
       register.defineRegisterByteValue("Hour", 2);
       register.defineRegisterByteValue("Day", 3);
       register.defineRegisterByteValue("Month", 4);
       register.defineRegisterByteValue("Year", 5);
       register.defineRegisterByteValue("DayOfTheWeek",6);
    //   register.setByteRegisterPredefinedValue();


        return null;
    }



}
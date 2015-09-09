package ru.matritca.energymeterclient.springconfigs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.matritca.energymeterclient.energymeterprotocol.EmeterRegisters;
import ru.matritca.energymeterclient.registers.Register;

/**
 * Created by Vasiliy on 09.09.2015.
 */
@Configuration
public class RegistersConfig {

    @Bean
    public Register configRegister(){
        Register register = new Register(EmeterRegisters.CONFIG_REGISTER,1,0xf0);
        register.addRegisterBit("Button",0,0);
        register.addRegisterBit("EP1",0,1);
        register.addRegisterBit("EP2",0,2);
        register.addRegisterBit("Lightening",0,3);
        return register;

    }

    @Bean
    public Register indicationConfigRegister(){
        Register register = new Register(EmeterRegisters.INDICATION_CONFIG_REGISTER,1,0xfc);
        register.addRegisterBit("EnergyIndication_LSb",0,0);
        register.addRegisterBit("EnergyIndication_MSb",0,1);
        return register;
    }

    @Bean
    public Register programConfigRegister(){
        Register register = new Register(EmeterRegisters.PROGRAMM_CONFIG_REGISTER,1,0x80);
        register.addRegisterBit("AutoSeason",0,0);
        register.addRegisterBit("BatteryIndication",0,1);
        register.addRegisterBit("WrongPasswordControl",0,2);
        register.addRegisterBit("BacklightMode_LSb",0,3);
        register.addRegisterBit("BacklightMode_MSb",0,4);
        register.addRegisterBit("TarifficatorsEEPROM",0,5);
        register.addRegisterBit("ProfilesEEPROM",0,6);
        return register;
    }




}

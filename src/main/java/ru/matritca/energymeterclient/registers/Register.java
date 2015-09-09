package ru.matritca.energymeterclient.registers;

import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matritca.energymeterclient.energymeterprotocol.EmeterRegisters;
import ru.matritca.energymeterclient.energymeterprotocol.FlashRegistersMapForCommand0X01;

import java.util.*;

/**
 * Created by Vasiliy on 08.09.2015.
 */
public class Register {

    private Logger logger = LoggerFactory.getLogger(Register.class);

    private String registerName;
    private int numOfBytes;

    List<Integer> registerArray;
    Map<String,Integer[]> bitMap; //Integer[] - координаты бита: нулевой элемент - порядковый номер байта
                                   //     первый элемент  - порядковый номер бита в байте

    Map<Integer,Map<Integer,String>> valueInfo;

    //     первый элемент  - порядковый номер бита в байте
    public Register(EmeterRegisters register,int numOfBytes,int registerInitialValue) {
        this.registerName = register.name();
        this.numOfBytes = numOfBytes;
        this.bitMap = new HashMap<>();
        this.registerArray = new ArrayList<>();
        for(int i = 0; i < numOfBytes; i++ ) {
           registerArray.add(i,registerInitialValue);
        }
     }

     public Integer[] getRegisterByteArray(){
       Integer[] byteArray = null;
       byteArray = registerArray.toArray(new Integer[]{});
       return byteArray;
     }


     public void addRegisterBit(String bitName,int bytePosition,int bitPosition){
          bitMap.put(bitName,new Integer[]{bytePosition,bitPosition});
     }

     public Set<String> getRegitersBitMap(){
         return  bitMap.keySet();
     }


    public int readBit(String bitName){
        Integer[] integers = bitMap.get(bitName); // Получаем координаты бита по его имени
        Integer subRegisterValue = registerArray.get(integers[0]); // получаем нужный байт регистра
        int bitValue = subRegisterValue & 0x01 << integers[1]; //
        return bitValue;
      }

    public void setBit(String bitName) {
        Integer[] integers = bitMap.get(bitName); // Получаем координаты бита по его имени
        logger.info("Info bit array size: {}",integers.length);
        Integer subRegisterValue = registerArray.get(integers[0]); // получаем нужный байт регистра
        logger.info("subRegisterValue before: {}",subRegisterValue);
        subRegisterValue = subRegisterValue | 0x01 << integers[1]; // меняем значение требумого бита
        logger.info("subRegisterValue after: {}",subRegisterValue);
        ListIterator<Integer> iterator = registerArray.listIterator();

        while(iterator.hasNext()){
           logger.info("Current register byte: {}", iterator.nextIndex());
            if (iterator.nextIndex() == integers[0]) {
                iterator.next();
                iterator.set(subRegisterValue); // меняем регистр на обновленный
            }else{
                iterator.next();
            }

        }


    }
  public void clearBit(String bitName) {
        Integer[] integers = bitMap.get(bitName); // Получаем координаты бита по его имени
        logger.info("Info bit array size: {}",integers.length);
        Integer subRegisterValue = registerArray.get(integers[0]); // получаем нужный байт регистра
        logger.info("subRegisterValue before: {}",subRegisterValue);
        subRegisterValue = subRegisterValue &~ 0x01 << integers[1]; // меняем значение требумого бита
        logger.info("subRegisterValue after: {}",subRegisterValue);
        ListIterator<Integer> iterator = registerArray.listIterator();

        while(iterator.hasNext()){
            logger.info("Current register byte: {}", iterator.nextIndex());
            if (iterator.nextIndex() == integers[0]) {
                iterator.next();
                iterator.set(subRegisterValue); // меняем регистр на обновленный
            }else{
                iterator.next();
            }

        }


    }

    public void setSubRegisterValue(int value,int bytePosition){
        ListIterator<Integer> iterator = registerArray.listIterator();

        while(iterator.hasNext()){
            logger.info("Current register byte: {}", iterator.nextIndex());
            if (iterator.nextIndex() == bytePosition) {
                iterator.next();
                iterator.set(value); // меняем регистр на обновленный
            }else{
                iterator.next();
            }

        }

    }

   public void addRegisterValueInfo(){

   }


    public String getRegisterName() {
        return registerName;
    }

    public int getRegisterSize(){
       return registerArray.size();
    }
}

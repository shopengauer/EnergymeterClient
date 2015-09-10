package ru.matritca.energymeterclient.registers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.matritca.energymeterclient.energymeterprotocol.EmeterRegisters;

import java.util.*;

/**
 * Created by Vasiliy on 08.09.2015.
 */
public class Register{

    private Logger logger = LoggerFactory.getLogger(Register.class);

    private String registerName;    // имя регистра
    private int registerLength;      // количество байт в регистре
    /**
     * Массив подрегистров в регистре
     * ArrayList of all bytes of current register
     */
    private List<Integer> registerArray;

    /**
     * Таблица для предопределения имен бит и их позиций в регистре;
     *      String: name of the bit ;
     *      Integer[]:  Integer[0] - byte position,
     *                  Integer[1] - bit position in byte
     */
    private Map<String,Integer[]> bitMap;

    /**
     * Таблица соответствия имени подрегистра(String) и его позиции в регистре(Integer)
     */
    private Map<String,Integer> byteMap;

    /**
     * Таблица для предопределения имен значений, записываемых в подрегистры.
     * String - идентификатор значения;
     * Integer - значение
     */
    private Map<String,Integer> predefineValueMap;



    public Register(EmeterRegisters register) {
        this.registerName = register.name(); // set register name
        this.registerLength = register.getRegisterLength(); // register length
        this.registerArray = new ArrayList<>();  // create ArrayList of register bytes
        this.byteMap = new HashMap<>();
        this.bitMap = new HashMap<>();
        this.predefineValueMap = new HashMap<>();

        for(int i = 0; i < registerLength; i++ ) {
            registerArray.add(i,0x00);
        }

    }

    public Register(EmeterRegisters register,List<Integer> registerInitialValue) {
        this.registerName = register.name(); // set register name
        this.registerLength = register.getRegisterLength(); // register length
        this.registerArray = new ArrayList<>();  // create ArrayList of register bytes
        this.byteMap = new HashMap<>();
        this.bitMap = new HashMap<>();
        this.predefineValueMap = new HashMap<>();

        for(int i = 0; i < registerLength; i++ ) {
           registerArray.add(i,registerInitialValue.get(i));
        }
     }




     public void defineRegisterBit(String bitName, int bytePosition, int bitPositionInByte){
          bitMap.put(bitName, new Integer[]{bytePosition, bitPositionInByte});
     }

     public void defineRegisterBit(String bitName, int bitPositionInRegister){// todo

     }


     public void defineRegisterByteValue(String valueDesc, int value){
          predefineValueMap.put(valueDesc, value);
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

    public void setByteRegisterValue(int value,int bytePosition){
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
  public void setByteRegisterPredefinedValue(String valueDesc, int bytePosition){
        ListIterator<Integer> iterator = registerArray.listIterator();

        while(iterator.hasNext()){
            logger.info("Current register byte: {}", iterator.nextIndex());
            if (iterator.nextIndex() == bytePosition) {
                iterator.next();
                iterator.set(predefineValueMap.get(valueDesc)); // меняем регистр на обновленный
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

    public Set<String> getRegitersBitMap(){
        return  bitMap.keySet();
    }

    public Integer[] getRegisterByteArray(){
        Integer[] byteArray = null;
        byteArray = registerArray.toArray(new Integer[]{});
        return byteArray;
    }

}

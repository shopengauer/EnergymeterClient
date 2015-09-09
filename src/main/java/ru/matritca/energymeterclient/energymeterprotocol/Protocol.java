package ru.matritca.energymeterclient.energymeterprotocol;

import org.springframework.stereotype.Component;

/**
 * Created by Vasiliy on 07.09.2015.
 */
@Component
public class Protocol {

     private byte PREAMBULE = 0x55;

    public byte[] getBroadcast(long factoryID, int mask,int byteInCommand,EmeterCommands command) throws Exception{

         int fixPartLength = 11;// 0x55 0xaaaaaaaa 0x07 factoryId length
                                 //  1       4       1     4        1

         if(factoryID > 0xffffffff && factoryID < 0xffffffff){
              throw new Exception();
         }
         if(mask > 0xff && factoryID < 0xff){
              throw new Exception();
         }




         return null;
    }



}

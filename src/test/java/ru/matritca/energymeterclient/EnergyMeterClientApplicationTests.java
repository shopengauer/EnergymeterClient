package ru.matritca.energymeterclient;


import javafx.application.Application;
import javafx.stage.Stage;
import jssc.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.matritca.energymeterclient.domain.SerialPortObject;
import ru.matritca.energymeterclient.serialportproperties.SerialPortObjectContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EnergyMeterClientApplication.class)
//@SpringApplicationConfiguration(classes = FXMLControllersConfig.class)
public class EnergyMeterClientApplicationTests {


	private Logger logger = LoggerFactory.getLogger(EnergyMeterClientApplicationTests.class);
    private SerialPortObject testSerialPortObject;
    private StringBuilder stringBuilder = new StringBuilder(300);

	@Autowired
	private SerialPortObjectContainer serialPortObjectContainer;

	private SerialPortEventListener serialPortEventListener;
    private String testString1 = "Asdfghjkcvnxb djf df  d 28364829ydbbsbodbcjskdbhou3ygr34uvjvsdclasjcl3vul2v3fwjfvhhlwv";
    private String testString2 = "Ssd" +
			"dfvdfvdfvdfvgbfgbgf" +
			"rtbbrfbdffffb45454fbdfssadfgadgasdgadafhadfh" +
			"adfhth4h4q5h45h45h4h4dfdfbxcbzxbwwbbrbwrtbwtntyynwwrtnwrtnwrtn" +
			"rtnbcv344143t45fbdsfb" +
			"4h4nbdfbdfbaasdfbdfbdfbbndant" +
			"fghjkcvnxb djf df  d 28364829ydbbsbodbcjskdbhou3ygr34uvjvsdclasjcl3vul2v3fwjfvhhlwv";

	public static class AsNonApp extends Application {



		@Override
		public void start(Stage primaryStage) throws Exception {
			// noop
		}
	}

	@BeforeClass
	public static void initJFX() {
		Thread t = new Thread("JavaFX Init Thread") {
			public void run() {
				Application.launch(AsNonApp.class, new String[0]);
			}
		};
		t.setDaemon(true);
		t.start();
		System.out.printf("FX App thread started\n");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}


	@Test
	@Repeat(value = 1)
	public void contextLoads() throws InterruptedException {

		serialPortEventListener = new SerialPortEventListener(){
			@Override
			public void serialEvent(SerialPortEvent serialPortEvent) {
				logger.info("{}",serialPortEvent.getEventType());
				logger.info("{}",serialPortEvent.getEventValue());
				logger.info("{}",serialPortEvent.getPortName());

				if(serialPortEvent.isRXCHAR()){
			    	try {
						String data =  testSerialPortObject.getSerialPort().readString();
					    if(data != null){
					     stringBuilder.append(data);
						}

					//	testSerialPortObject.getSerialPort().getInputBufferBytesCount();
						 logger.info("Data bytes {}", testSerialPortObject.getSerialPort().getInputBufferBytesCount());
						// Assert.assertEquals(data,testString1);
						logger.info("Data recieve {}",data);
					} catch (SerialPortException e) {
						logger.error(e.getExceptionType());

					}

				}
			}
		};

		System.out.println("Test 1");
		String[] serialPortList = SerialPortList.getPortNames();
		for (String s : serialPortList) {
			logger.info("Com port {} found",s);
		}

		testSerialPortObject = new SerialPortObject(serialPortList[0]);
        String comPortName = testSerialPortObject.getPortname();

		logger.info("Com port {} isOpened: {}", comPortName, testSerialPortObject.isOpened());
        logger.info("Try to open com port {}",comPortName);
		try {
			testSerialPortObject.openPort();
			logger.info("Com port {} isOpened: {}", comPortName, testSerialPortObject.isOpened());
		} catch (SerialPortException e) {
			logger.info("Com port error: {}", e.getExceptionType());
		}

		try {
			testSerialPortObject.addEventListener(serialPortEventListener, SerialPort.MASK_RXCHAR);
		} catch (SerialPortException e) {
			 logger.error(e.getExceptionType());
		}

		byte b = 0xf;

		try {
			testSerialPortObject.getSerialPort().writeString(testString2);


		} catch (SerialPortException e) {
			logger.error(e.getExceptionType());

		}

		Thread.sleep(3000);
	//	logger.info("Data size: {}", stringBuilder.length());
		Assert.assertEquals(testString2,stringBuilder.toString());

		try {
			testSerialPortObject.closePort();
		} catch (SerialPortException e) {
			e.printStackTrace();
		}

	}

}

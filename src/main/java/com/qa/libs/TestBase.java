package com.qa.libs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static String downloadFilepath = System.getProperty("user.dir")+"\\Download";



    // Constructor
    public TestBase(){
        try {
            prop = new Properties();
            String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties";
            FileInputStream ip = new FileInputStream(filepath);
            prop.load(ip);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * This method is used to initialize browser.
     * @throws Exception
     */
    public static void initilization() throws Exception {
        String browserName = prop.getProperty( "browser" );
        if (browserName.equalsIgnoreCase( "chrome" )) {
            //downloadFilepath = System.getProperty("user.dir")+"\\Download\\Supplier_Order";
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            chromePrefs.put( "profile.default_content_settings.popups", 0 );
            chromePrefs.put( "download.default_directory", downloadFilepath );
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption( "prefs", chromePrefs );
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability( CapabilityType.ACCEPT_SSL_CERTS, true );
            cap.setCapability( ChromeOptions.CAPABILITY, options );

            System.setProperty( "webdriver.chrome.driver", System.getProperty( "user.dir" ) + "\\Driver\\chromedriver.exe" );
            driver = new ChromeDriver( options );

        } else if (browserName.equalsIgnoreCase( "firefox" )) {
            System.setProperty( "webdriver.gecko.driver", System.getProperty( "user.dir" ) + "\\src\\Driver\\geckodriver.exe" );
            driver = new ChromeDriver();

        } else {
            System.out.println( "Browser not found." );
        }

        try {
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout( 30, TimeUnit.SECONDS );
            driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS );
            driver.get( prop.getProperty( "url" ) );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package com.qa.tests;

import com.qa.libs.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    public LoginTest(){
        super();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        initilization();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginTest(){
        System.out.println( "Login Test Method." );
    }

}

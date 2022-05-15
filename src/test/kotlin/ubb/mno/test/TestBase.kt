package ubb.mno.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import ubb.mno.util.PropertiesLoader
import ubb.mno.util.PropertyValues
import java.util.concurrent.TimeUnit

abstract class TestBase {
    lateinit var driver: WebDriver
        private set

    @BeforeTest
    fun setup() {
        System.setProperty(
            PropertiesLoader.get(PropertyValues.NAME_DRIVER),
            PropertiesLoader.get(PropertyValues.PATH_DRIVER) + PropertiesLoader.get(PropertyValues.EXE_DRIVER)
        )
        driver = ChromeDriver()
        with(driver.manage()) {
            timeouts().implicitlyWait(10, TimeUnit.SECONDS)
            window().maximize()
        }
    }

    @AfterTest
    fun driverClose() {
        driver.close()
    }
}
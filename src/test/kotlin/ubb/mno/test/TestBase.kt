package ubb.mno.test

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import ubb.mno.util.PropertiesLoader
import ubb.mno.util.PropertyValues
import java.util.concurrent.TimeUnit

abstract class TestBase {
    lateinit var driver: WebDriver
        private set

    protected fun createDriver() {
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

    protected fun closeDriver() = driver.close()
}
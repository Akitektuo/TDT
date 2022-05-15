package ubb.mno.page

import org.openqa.selenium.WebDriver
import ubb.mno.util.PropertiesLoader
import ubb.mno.util.PropertyValues

abstract class BasePage(protected val driver: WebDriver) {
    init {
        navigate()
    }

    private fun navigate() {
        val username = PropertiesLoader.get(PropertyValues.USERNAME)
        val password = PropertiesLoader.get(PropertyValues.PASSWORD)

        driver.get("https://$username:$password@lighthouse-demo.evozon.com/login")

        afterLoginRendered()
    }

    open fun afterLoginRendered() {
    }

    fun wait(seconds: Int = 1) = Thread.sleep(seconds * 1000L)

    fun navigate(path: String) {
        driver.get("https://lighthouse-demo.evozon.com/$path")
        wait()
    }
}
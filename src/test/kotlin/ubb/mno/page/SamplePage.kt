package ubb.mno.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class SamplePage(private val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }
}
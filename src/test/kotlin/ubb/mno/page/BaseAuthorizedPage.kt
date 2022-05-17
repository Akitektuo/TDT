package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ubb.mno.util.PropertiesLoader
import ubb.mno.util.PropertyValues

abstract class BaseAuthorizedPage(driver: WebDriver) : BasePage(driver) {
    override fun afterLoginRendered() {
        val emailField = getEmailField()
        val passwordField = getPasswordField()
        val signInButton = getSignInButton()

        val accountEmail = PropertiesLoader.get(PropertyValues.ACCOUNT_EMAIL)
        val accountPassword = PropertiesLoader.get(PropertyValues.ACCOUNT_PASSWORD)

        emailField.sendKeys(accountEmail)
        passwordField.sendKeys(accountPassword)
        signInButton.click()

        wait()

        afterLogin()
    }

    private fun getEmailField() = driver.findElement(By.cssSelector("input[name=username]"))

    private fun getPasswordField() = driver.findElement(By.cssSelector("input[name=password]"))

    private fun getSignInButton() = driver.findElement(By.cssSelector("button"))

    open fun afterLogin() {

    }
}

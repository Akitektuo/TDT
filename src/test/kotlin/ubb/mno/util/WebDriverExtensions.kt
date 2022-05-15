package ubb.mno.util

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

fun WebElement.getTextContent(): String? = getAttribute("textContent")

fun WebElement.getInputValue(): String? = getAttribute("value")

fun WebElement.clearInput() = sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE))

fun WebDriver.scrollIntoView(element: WebElement): WebElement {
    (this as JavascriptExecutor).executeScript("arguments[0].scrollIntoView(true)", element)

    return element
}

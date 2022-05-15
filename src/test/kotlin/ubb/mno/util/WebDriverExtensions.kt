package ubb.mno.util

import org.openqa.selenium.WebElement

fun WebElement.getTextContent() = getAttribute("textContent")

fun WebElement.getInputValue() = getAttribute("value")
package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class BuildingsPage(driver: WebDriver) : BaseAuthorizedPage(driver) {

    override fun afterLogin() {
        navigate("buildings")
    }

    fun openEditBuildingDialog(buildingName: String) = lookForBuildingRow(buildingName)
        ?.openActionsAndSelect("Edit Building")

    fun getDialogTitle(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) h6 > strong")
    )

    fun getDialogBuildingNameInput(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) input")
    )

    private fun lookForBuildingRow(buildingName: String): WebElement? {
        val nextPageButton = getNextPageButton()
        var buildingRow = getBuildingRow(buildingName)

        while (buildingRow == null && nextPageButton.clickNextPage())
            buildingRow = getBuildingRow(buildingName)

        return buildingRow
    }

    private fun getBuildingRow(buildingName: String): WebElement? =
        driver.findElements(By.cssSelector("tr.MuiTableRow-hover"))
            .firstOrNull { it.getBuildingName().text == buildingName }

    private fun WebElement.clickNextPage() = isEnabled.also { if (it) click() }

    private fun WebElement.getBuildingName() = findElement(By.cssSelector("p"))

    private fun getNextPageButton() = driver.findElement(By.cssSelector("button[title=\"Next page\"]"))

    private fun WebElement.openActionsAndSelect(actionName: String) {
        openActions()
        getAllActions().selectAction(actionName)
    }

    private fun WebElement.openActions() = findElement(By.cssSelector("button[aria-label=More]")).click()

    private fun getAllActions() = driver.findElements(
        By.cssSelector("div.MuiPopover-root:not([aria-hidden]) > div.MuiPaper-root > ul > li")
    )

    private fun List<WebElement>.selectAction(actionName: String) = first { it.text == actionName }.click()
}
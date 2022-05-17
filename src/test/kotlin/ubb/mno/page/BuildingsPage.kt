package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ubb.mno.util.clearInput
import ubb.mno.util.getTextContent

class BuildingsPage(driver: WebDriver, skipLogin: Boolean = false) : BaseAuthorizedPage(driver, skipLogin) {
    override fun afterLogin() {
        navigate("buildings")
    }

    fun setBuildingName(buildingName: String) {
        getDialogBuildingNameInput()?.clearInput()
        getDialogBuildingNameInput()?.sendKeys(buildingName)
    }
    fun setSafeDistance(safeDistance: String) {
        getDialogSafeDistanceInput()?.clearInput()
        getDialogSafeDistanceInput()?.sendKeys(safeDistance)
    }
    fun openEditBuildingDialog(buildingName: String) = lookForBuildingRow(buildingName)
        ?.openActionsAndSelect("Edit Building")

    fun closeEditBuildingDialog() = getCloseDialogButton()?.click();

    fun saveBuilding() = getSaveEditedBuildingButton()?.click();

    fun getDialogBuildingNameInput(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) input")
    )

    fun getDialogSafeDistanceInput(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) #safeDistance")
    )

    fun getSaveEditedBuildingButton(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) button:not([aria-label])")
    )

    private fun getCloseDialogButton(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) button")
    )

    private fun lookForBuildingRow(buildingName: String): WebElement? {
        val nextPageButton = getNextPageButton()
        var buildingRow = getBuildingRow(buildingName)

        while (buildingRow == null && nextPageButton.clickNextPage()) {
            wait()
            buildingRow = getBuildingRow(buildingName)
        }

        return buildingRow
    }

    private fun getBuildingRow(buildingName: String): WebElement? =
        driver.findElements(By.cssSelector("tr.MuiTableRow-hover"))
            .firstOrNull { it.getBuildingName().getTextContent() == buildingName }

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

    private fun List<WebElement>.selectAction(actionName: String) = first { it.getTextContent() == actionName }.click()
}

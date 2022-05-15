package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class BuildingsPage(driver: WebDriver) : BaseAuthorizedPage(driver) {
    override fun afterLogin() {
        navigate("buildings")
    }

    fun openEditBuildingDialog(buildingName: String, buildingSafeDistance: String) =
        lookForBuildingRow(buildingName, buildingSafeDistance)?.openActionsAndSelect("Edit Building")

    fun getDialogTitle(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) h6 > strong")
    )

    fun getDialogBuildingNameInput(): WebElement? = driver.findElement(
        By.cssSelector("div.MuiDialog-root:not([aria-hidden]) input")
    )

    fun populateForNewBuilding(buildingName: String?, buildingSafeDistance: Int?) {
        val addNewBuildingButton = this.getElementByCss("button.MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary.MuiButton-fullWidth")
        addNewBuildingButton.click()

        if (buildingName != null) {
            val buildingNameInput = this.getElementByCss("input#label")
            buildingNameInput.sendKeys(buildingName)
        }

        if (buildingSafeDistance != null) {
            val safeDistanceInput = this.getElementByCss("input#safeDistance")
            safeDistanceInput.sendKeys(buildingSafeDistance.toString())
        }
    }

    fun lookForBuildingRow(buildingName: String, safeDistance: String): WebElement? {
        val nextPageButton = getNextPageButton()
        var buildingRow = getBuildingRow(buildingName, safeDistance)

        while (buildingRow == null && nextPageButton.clickNextPage())
            buildingRow = getBuildingRow(buildingName, safeDistance)

        return buildingRow
    }

    private fun getBuildingRow(buildingName: String, safeDistance: String): WebElement? =
        driver.findElements(By.cssSelector("tr.MuiTableRow-hover"))
            .firstOrNull {
                it.getBuildingName().text == buildingName && it.getSafeDistance().text == safeDistance
            }

    private fun WebElement.clickNextPage() = isEnabled.also { if (it) click() }

    private fun WebElement.getBuildingName() = findElement(By.cssSelector("p"))

    private fun WebElement.getSafeDistance() = findElements(By.cssSelector("p"))[1]

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

    private fun getElementByCss(selector: String) = driver.findElement(By.cssSelector(selector))

    fun getSaveNewBuildingButton(): WebElement = driver.findElement(By.cssSelector("button.MuiButtonBase-root.MuiButton-root.MuiButton-contained.MuiButton-containedPrimary"))
}
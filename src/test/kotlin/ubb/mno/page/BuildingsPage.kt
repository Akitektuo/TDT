package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ubb.mno.util.getTextContent

class BuildingsPage(driver: WebDriver, skipLogin: Boolean = false) : BaseAuthorizedPage(driver, skipLogin) {

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

    fun removeFloor(buildingName: String, floorName: String) {
        lookForFloorRow(buildingName, floorName)?.openActionsAndSelect("Delete")
        wait()
        confirmDelete()
    }

    fun editFloor(buildingName: String, floorName: String) =
        lookForFloorRow(buildingName, floorName)?.openActionsAndSelect("Edit")

    private fun confirmDelete() =
        driver.findElement(By.cssSelector(".MuiDialog-root button.MuiButton-contained")).click()

    private fun lookForFloorRow(buildingName: String, floorName: String): WebElement? {
        lookForBuildingRow(buildingName)?.clickExpand()
        wait()
        return getFloorRow(floorName)
    }

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

    private fun WebElement.clickExpand() = findElement(By.cssSelector("button")).click()

    private fun getFloorRow(floorName: String): WebElement? =
        driver.findElements(By.cssSelector(".MuiTableCell-body > div > div > div tbody > tr"))
            .firstOrNull { it.getFloorName().getTextContent() == floorName }

    private fun WebElement.getFloorName() = findElement(By.cssSelector("td"))
}

package ubb.mno.page


import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class BookingsPage(driver: WebDriver) : BaseAuthorizedPage(driver) {
  //  override fun afterLogin() {
    //    navigate("buildings")
    //}

    //fun openEditBuildingDialog(buildingName: String) = lookForBuildingRow(buildingName)
    //    ?.openActionsAndSelect("Edit Building")

  //  fun getDialogTitle(): WebElement? = driver.findElement(
  //      By.cssSelector("div.MuiDialog-root:not([aria-hidden]) h6 > strong")
 //   )

    //fun getDialogBuildingNameInput(): WebElement? = driver.findElement(
  //      By.cssSelector("div.MuiDialog-root:not([aria-hidden]) input")
  //  )

/*
    private fun lookForBuildingRow(buildingName: String): WebElement? {
        val nextPageButton = getNextPageButton()
        var buildingRow = getBuildingRow(buildingName)

        while (buildingRow == null && nextPageButton.clickNextPage())
            buildingRow = getBuildingRow(buildingName)

        return buildingRow
    }
*/
    /*private fun getBuildingRow(buildingName: String): WebElement? =
        driver.findElements(By.cssSelector("tr.MuiTableRow-hover"))
            .firstOrNull { it.getBuildingName().text == buildingName }

    private fun WebElement.clickNextPage() = isEnabled.also { if (it) click() }
*/
 //   private fun WebElement.getBuildingName() = findElement(By.cssSelector("p"))

 //   private fun getNextPageButton() = driver.findElement(By.cssSelector("button[title=\"Next page\"]"))
 fun openAddBookingDialog()=driver.findElement(By.className("MuiButton-startIcon")).click()

  /*  private fun WebElement.openActionsAndSelect(actionName: String) {
        openActions()
        getAllActions().selectAction(actionName)
    }*/

    private fun WebElement.openActions() = findElement(By.id("react-select-10-placeholder")).click()

   /* private fun getAllActions() = driver.findElements(
        By.cssSelector("input:is([aria-expanded])")
    )

    private fun List<WebElement>.selectAction(actionName: String) = first{ it.text == "projector" }.click()
    */


    fun chooseDesk()=driver.findElement(By.className("MuiBox-root")).click()
    fun chooseDay()=driver.findElement(By.cssSelector("div[aria-label=\"Mon May 16 2022\"]")).click()
    fun book()=driver.findElement(By.cssSelector("button.MuiButton-contained")).click()
    fun getTextAfter():WebElement?=driver.findElement(By.cssSelector("span.MuiTypography-alignLeft"))
    fun doAll(){
        openAddBookingDialog();
        chooseDay();
        wait(10)
        chooseDesk();
        wait(3)
        book();
    wait(5)

    }
}

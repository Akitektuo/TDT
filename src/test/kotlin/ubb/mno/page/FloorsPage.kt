package ubb.mno.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ubb.mno.util.clearInput
import ubb.mno.util.getInputValue
import ubb.mno.util.scrollIntoView

class FloorsPage(driver: WebDriver) : BaseAuthorizedPage(driver) {

    override fun afterLogin() {
        navigate("floors?building=489")
    }

    fun setFloorName(floorName: String) = getFloorNameInput().sendKeys(floorName)

    fun setFloorMap(floorMapPath: String) = getFloorMapInput().sendKeys(floorMapPath)

    fun setFloorDetails(floorDetailsPath: String) = getFloorDetailsInput().sendKeys(floorDetailsPath)

    fun setMapScale(mapScale: Int) = getMapScaleInput().sendKeys(mapScale.toString())

    fun setDeskLength(deskLength: Float) = getDeskLengthInput().sendKeys(deskLength.toString())

    fun setDeskWidth(deskWidth: Float) = getDeskWidthInput().sendKeys(deskWidth.toString())

    fun setMaxZoomIn(zoomIn: Float) {
        val maxZoomInInput = getMaxZoomInInput()

        maxZoomInInput.clearInput()
        maxZoomInInput.sendKeys(zoomIn.toString())
    }

    fun setMaxZoomOut(zoomOut: Float) {
        val maxZoomOutInput = getMaxZoomOutInput()

        maxZoomOutInput.clearInput()
        maxZoomOutInput.sendKeys(zoomOut.toString())
    }

    fun setDefaultZoom(defaultZoom: Float) {
        val defaultZoomInput = getDefaultZoomInput()

        defaultZoomInput.clearInput()
        defaultZoomInput.sendKeys(defaultZoom.toString())
    }

    fun clickCancel() = driver.scrollIntoView(getCancelInput()).click()

    fun clickSave() = driver.scrollIntoView(getSaveInput()).click()

    fun remove(floorName: String) = BuildingsPage(driver, true)
        .removeFloor("Robot Paradise", floorName)

    fun goToDetails(floorName: String) = BuildingsPage(driver, true)
        .editFloor("Robot Paradise", floorName)

    fun getFloorName() = getFloorNameInput().getInputValue()

    fun getFloorMap() = getFloorMapInput().getInputValue()

    fun getFloorDetails() = getFloorDetailsInput().getInputValue()

    fun getMapScale() = getMapScaleInput().getInputValue()?.toIntOrNull()

    fun getDeskLength() = getDeskLengthInput().getInputValue()?.toFloatOrNull()

    fun getDeskWidth() = getDeskWidthInput().getInputValue()?.toFloatOrNull()

    fun getMaxZoomIn() = getMaxZoomInInput().getInputValue()?.toFloatOrNull()

    fun getMaxZoomOut() = getMaxZoomOutInput().getInputValue()?.toFloatOrNull()

    fun getDefaultZoom() = getDefaultZoomInput().getInputValue()?.toFloatOrNull()

    private fun getFloorNameInput() = driver.findElement(By.cssSelector("input"))

    private fun getFloorMapInput() = driver.findElement(By.cssSelector("input[name=file]"))

    private fun getFloorDetailsInput() = driver.findElement(By.cssSelector("input[name=detailFile]"))

    private fun getMapScaleInput() = driver.findElement(By.cssSelector("input[name=scale]"))

    private fun getDeskLengthInput() = driver.findElement(By.cssSelector("input[name=deskLength]"))

    private fun getDeskWidthInput() = driver.findElement(By.cssSelector("input[name=deskWidth]"))

    private fun getMaxZoomInInput() = driver.findElement(By.cssSelector("input[name=zoomIn]"))

    private fun getMaxZoomOutInput() = driver.findElement(By.cssSelector("input[name=zoomOut]"))

    private fun getDefaultZoomInput() = driver.findElement(By.cssSelector("input[name=zoomDefault]"))

    private fun getCancelInput() = driver.findElement(By.cssSelector(".MuiGrid-item > button.MuiButton-text"))

    private fun getSaveInput() = driver.findElement(
        By.cssSelector(".MuiGrid-justify-content-xs-flex-end > .MuiGrid-item > button.MuiButton-contained")
    )
}
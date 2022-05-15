package ubb.mno.test

import org.testng.annotations.Test
import ubb.mno.page.BuildingsPage
import ubb.mno.util.getInputValue
import ubb.mno.util.getTextContent
import kotlin.test.assertEquals

class OpenEditBuildingDialogTest : TestBase() {
    @Test
    fun openRobotParadiseEditDialog() {
        val buildingName = "Robot Paradise"
        val buildingsPage = BuildingsPage(driver)

        buildingsPage.openEditBuildingDialog(buildingName)

        assertEquals("Building information", buildingsPage.getDialogTitle()?.getTextContent())
        assertEquals(buildingName, buildingsPage.getDialogBuildingNameInput()?.getInputValue())
    }
}
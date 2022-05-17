package ubb.mno.test

import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import ubb.mno.page.BuildingsPage
import ubb.mno.util.getInputValue
import kotlin.test.assertEquals


class EditBuildingTest : TestBase() {
    @BeforeMethod
    fun beforeMethod() {
        createDriver()
    }

    @AfterMethod
    fun afterMethod() {
        closeDriver()
    }

    @Test
    fun editBuilding_emptyBuildingName_canNotSave() {
        val buildingName = "DragosC-TestBuilding"
        val page = BuildingsPage(driver)

        page.openEditBuildingDialog(buildingName)
        page.setBuildingName("")

        assertEquals(false, page.getSaveEditedBuildingButton()?.isEnabled)
    }

    @Test
    fun editBuilding_emptySafeDistance_canNotSave() {
        val buildingName = "DragosC-TestBuilding"
        val page = BuildingsPage(driver)

        page.openEditBuildingDialog(buildingName)
        page.setSafeDistance("")

        assertEquals(false, page.getSaveEditedBuildingButton()?.isEnabled)
    }

    // report this ?
    @Test
    fun editBuilding_negativeSafeDistance_savedAsPositive() {
        val buildingName = "DragosC-TestBuilding"
        val page = BuildingsPage(driver)

        val negativeValue = "-12"
        val expectedOutputValue = "12"

        page.openEditBuildingDialog(buildingName)
        page.setSafeDistance(negativeValue)

        assertEquals(expectedOutputValue, page.getDialogSafeDistanceInput()?.getInputValue())
    }

    // report this ?
    @Test
    fun editBuilding_veryLargeSafeDistance_hasBoundaries() {
        val buildingName = "DragosC-TestBuilding"
        val page = BuildingsPage(driver)

        val largeSafeDistance = "1000000000000000000000000000000000000000000000000000000000000000000000000000000000000"

        page.openEditBuildingDialog(buildingName)
        page.setSafeDistance(largeSafeDistance)

        assertEquals(false, page.getSaveEditedBuildingButton()?.isEnabled)

    }
}
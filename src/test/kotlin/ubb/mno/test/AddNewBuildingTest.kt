package ubb.mno.test

import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import ubb.mno.page.BuildingsPage
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AddNewBuildingTest : TestBase() {
    private var buildingsPage: BuildingsPage? = null

    @BeforeTest
    fun setupAddNewBuildingTest() {
        buildingsPage = BuildingsPage(driver)
    }

    @Test
    fun emptyBuildingName_emptySafeDistance() {
        val buildingName = null
        val buildingSafeDistance = null

        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test
    fun emptyBuildingName_validSafeDistance() {
        val buildingName = null
        val buildingSafeDistance = 100

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test
    fun validBuildingName_emptySafeDistance() {
        val buildingName = "Matei Building"
        val buildingSafeDistance = null

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test
    fun validBuildingName_validSafeDistance() {
        val buildingName = "Matei Building"
        val buildingSafeDistance = (0..10000).random()

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        val saveNewBuildingButton = buildingsPage?.getSaveNewBuildingButton()
        assertEquals(true, saveNewBuildingButton?.isEnabled)

        saveNewBuildingButton?.click()

        assertNotNull(buildingsPage?.lookForBuildingRow(buildingName, buildingSafeDistance.toString()))
    }
}
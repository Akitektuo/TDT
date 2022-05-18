package ubb.mno.test

import org.testng.annotations.BeforeTest
import org.testng.annotations.Test
import ubb.mno.page.BuildingsPage
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class AddNewBuildingTest : TestBase() {
    private var buildingsPage: BuildingsPage? = null

    @BeforeTest
    fun setupAddNewBuildingTest() {
        buildingsPage = BuildingsPage(driver)
    }

    @Test // Case 1 from documentation
    fun blankBuildingName_blankSafeDistance() {
        val buildingName = null
        val buildingSafeDistance = null

        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test // Case 2
    fun validBuildingName_blankSafeDistance() {
        val buildingName = "Central Building"
        val buildingSafeDistance = null

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test // Case 3
    fun emptyBuildingName_validSafeDistance() {
        val buildingName = null
        val buildingSafeDistance = 50

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test // Case 4
    fun invalidBuildingName_blankSafeDistance() {
        val buildingName = "Wuxi International Finance Square, World Financial Center, East Tower Dubai"
        val buildingSafeDistance = null

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test // Case 5
    fun blankBuildingName_invalidSafeDistance() {
        val buildingName = null
        val buildingSafeDistance = 301

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        assertEquals(false, buildingsPage?.getSaveNewBuildingButton()?.isEnabled)
    }

    @Test // Case 6
    fun invalidBuildingName_validSafeDistance() {
        val buildingName = "Wuxi International Finance Square, World Financial Center, East Tower Dubai"
        val buildingSafeDistance = 300

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        val saveNewBuildingButton = buildingsPage?.getSaveNewBuildingButton()
        assertEquals(true, saveNewBuildingButton?.isEnabled)

        saveNewBuildingButton?.click()

        assertNull(buildingsPage?.lookForBuildingRow(buildingName, buildingSafeDistance.toString()))
    }

    @Test // Case 7
    fun validBuildingName_invalidSafeDistance() {
        val buildingName = "Central Building"
        val buildingSafeDistance = 49

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        val saveNewBuildingButton = buildingsPage?.getSaveNewBuildingButton()
        assertEquals(true, saveNewBuildingButton?.isEnabled)

        saveNewBuildingButton?.click()

        assertNull(buildingsPage?.lookForBuildingRow(buildingName, buildingSafeDistance.toString()))
    }

    @Test // Case 8
    fun invalidBuildingName_invalidSafeDistance() {
        val buildingName = "Wuxi International Finance Square, World Financial Center, East Tower Dubai"
        val buildingSafeDistance = 400

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        val saveNewBuildingButton = buildingsPage?.getSaveNewBuildingButton()
        assertEquals(true, saveNewBuildingButton?.isEnabled)

        saveNewBuildingButton?.click()

        assertNull(buildingsPage?.lookForBuildingRow(buildingName, buildingSafeDistance.toString()))
    }

    @Test // Case 9
    fun validBuildingName_validSafeDistance() {
        val buildingName = "Central Building"
        val buildingSafeDistance = 100

        buildingsPage?.navigate("buildings")
        buildingsPage?.populateForNewBuilding(buildingName, buildingSafeDistance)

        val saveNewBuildingButton = buildingsPage?.getSaveNewBuildingButton()
        assertEquals(true, saveNewBuildingButton?.isEnabled)

        saveNewBuildingButton?.click()

        assertNotNull(buildingsPage?.lookForBuildingRow(buildingName, buildingSafeDistance.toString()))
    }
}
package ubb.mno.test

import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import ubb.mno.page.FloorsPage
import java.nio.file.Paths
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AddFloorBoundaryTest : TestBase() {
    private val path = Paths.get("").toAbsolutePath().toString()

    @BeforeMethod
    fun beforeMethod() {
        createDriver()
    }

    @AfterMethod
    fun afterMethod() {
        closeDriver()
    }

    @Test
    fun addFloor_normalData_savesFloor() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f
        val zoomIn = 0.1f
        val zoomOut = 0.6f
        val defaultZoom = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.setMaxZoomIn(zoomIn)
        floorsPage.setMaxZoomOut(zoomOut)
        floorsPage.setDefaultZoom(defaultZoom)
        floorsPage.clickSave()

        /** Assert */
        floorsPage.goToDetails(floorName)
        floorsPage.wait()

        assertEquals(floorName, floorsPage.getFloorName())
        assertEquals(floorScale, floorsPage.getMapScale())
        assertEquals(deskLength, floorsPage.getDeskLength())
        assertEquals(deskWidth, floorsPage.getDeskWidth())
        assertEquals(zoomIn, floorsPage.getMaxZoomIn())
        assertEquals(zoomOut, floorsPage.getMaxZoomOut())
        assertEquals(defaultZoom, floorsPage.getDefaultZoom())

        /** Cleanup */
        floorsPage.navigate("buildings")
        floorsPage.wait()
        floorsPage.remove(floorName)
        floorsPage.wait()
    }

    @Test
    fun addFloor_noDetailsPdf_savesFloor() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f
        val zoomIn = 0.1f
        val zoomOut = 0.6f
        val defaultZoom = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.setMaxZoomIn(zoomIn)
        floorsPage.setMaxZoomOut(zoomOut)
        floorsPage.setDefaultZoom(defaultZoom)
        floorsPage.clickSave()

        /** Assert */
        floorsPage.goToDetails(floorName)
        floorsPage.wait()

        assertEquals(floorName, floorsPage.getFloorName())
        assertEquals(floorScale, floorsPage.getMapScale())
        assertEquals(deskLength, floorsPage.getDeskLength())
        assertEquals(deskWidth, floorsPage.getDeskWidth())
        assertEquals(zoomIn, floorsPage.getMaxZoomIn())
        assertEquals(zoomOut, floorsPage.getMaxZoomOut())
        assertEquals(defaultZoom, floorsPage.getDefaultZoom())

        /** Cleanup */
        floorsPage.navigate("buildings")
        floorsPage.wait()
        floorsPage.remove(floorName)
        floorsPage.wait()
    }

    @Test
    fun addFloor_noName_noFloorSaved() {
        /** Arrange */
        val floorName = ""
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f
        val zoomIn = 0.1f
        val zoomOut = 0.6f
        val defaultZoom = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.setMaxZoomIn(zoomIn)
        floorsPage.setMaxZoomOut(zoomOut)
        floorsPage.setDefaultZoom(defaultZoom)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_noMap_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_noScale_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_noDeskMeasurements_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorScale = 19

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setMapScale(floorScale)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_negativeScale_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = -19
        val deskLength = 2f
        val deskWidth = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_negativeDeskMeasurements_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = -2f
        val deskWidth = -1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_defaultZoom_floorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.clickSave()

        /** Assert */
        floorsPage.goToDetails(floorName)
        floorsPage.wait()

        assertEquals(floorName, floorsPage.getFloorName())
        assertEquals(floorScale, floorsPage.getMapScale())
        assertEquals(deskLength, floorsPage.getDeskLength())
        assertEquals(deskWidth, floorsPage.getDeskWidth())
        assertNotNull(floorsPage.getMaxZoomIn())
        assertNotNull(floorsPage.getMaxZoomOut())
        assertNotNull(floorsPage.getDefaultZoom())

        /** Cleanup */
        floorsPage.navigate("buildings")
        floorsPage.wait()
        floorsPage.remove(floorName)
        floorsPage.wait()
    }

    @Test
    fun addFloor_negativeZoom_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f
        val zoomIn = -0.1f
        val zoomOut = -0.6f
        val defaultZoom = -1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.setMaxZoomIn(zoomIn)
        floorsPage.setMaxZoomOut(zoomOut)
        floorsPage.setDefaultZoom(defaultZoom)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_zeroScale_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 0
        val deskLength = 2f
        val deskWidth = 1f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_zeroDeskMeasurements_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 0f
        val deskWidth = 0f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }

    @Test
    fun addFloor_zeroZoom_noFloorSaved() {
        /** Arrange */
        val floorName = "Test"
        val floorMap = "$path\\src\\test\\resources\\map.png"
        val floorDetails = "$path\\src\\test\\resources\\description.pdf"
        val floorScale = 19
        val deskLength = 2f
        val deskWidth = 1f
        val zoomIn = 0f
        val zoomOut = 0f
        val defaultZoom = 0f

        val floorsPage = FloorsPage(driver)

        /** Act */
        floorsPage.setFloorName(floorName)
        floorsPage.setFloorMap(floorMap)
        floorsPage.setFloorDetails(floorDetails)
        floorsPage.setMapScale(floorScale)
        floorsPage.setDeskLength(deskLength)
        floorsPage.setDeskWidth(deskWidth)
        floorsPage.setMaxZoomIn(zoomIn)
        floorsPage.setMaxZoomOut(zoomOut)
        floorsPage.setDefaultZoom(defaultZoom)
        floorsPage.clickSave()

        /** Assert */
        assertContains(driver.currentUrl, "floor")
    }
}
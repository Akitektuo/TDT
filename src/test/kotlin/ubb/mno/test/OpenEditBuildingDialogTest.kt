package ubb.mno.test


import org.testng.annotations.Test
import ubb.mno.page.BookingsPage
import ubb.mno.util.getInputValue
import ubb.mno.util.getTextContent
import kotlin.test.assertEquals

class OpenEditBuildingDialogTest : TestBase() {
    @Test
    fun openRobotParadiseEditDialog() {
        val bName = "18-i7L, Parter, DEMO-1"
        val bookingsPage = BookingsPage(driver)

        bookingsPage.doAll()

        assertEquals(bName, bookingsPage.getTextAfter()?.getTextContent() )
    }
}

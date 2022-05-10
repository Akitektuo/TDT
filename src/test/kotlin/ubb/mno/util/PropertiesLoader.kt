package ubb.mno.util

import java.io.FileInputStream
import java.util.*

object PropertiesLoader {
    private var properties: Properties? = null

    private fun load() {
        properties = Properties().apply {
            load(FileInputStream("config.properties"))
        }
    }

    fun get(property: PropertyValues): String {
        properties?.let { load() }

        return properties?.getProperty(property.key) ?: ""
    }
}
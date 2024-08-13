package com.gary.GalacticTrading.converter

import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.util.*

/**
 * This class is responsible for converting intergalactic units to roman string.
 */
@Service
class IntergalacticUnitsToRomanStringConverter() {
    val interGalacticUnits: MutableMap<String, String> = HashMap()
    private val log = KotlinLogging.logger {}

    fun setInterGalacticUnits(interGalacticUnits: MutableMap<String, String>) {
        this.interGalacticUnits.putAll(interGalacticUnits)
    }
    /**
     * This method is used to convert intergalactic units to roman string.
     * @param interGalacticUnits intergalactic units string to be converted to roman string
     * @return roman string
     */
    fun convertIntergalacticUnitsToRomanString(interGalacticUnits: String): String {
        val interGalacticUnitArray =
            interGalacticUnits.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val romanString = StringBuilder()
        for (interGalacticUnit in interGalacticUnitArray) {
            romanString.append(this.interGalacticUnits[interGalacticUnit.lowercase(Locale.getDefault())])
        }
        return romanString.toString()
    }
}

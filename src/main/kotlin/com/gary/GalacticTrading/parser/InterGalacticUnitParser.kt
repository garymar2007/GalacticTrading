package com.gary.GalacticTrading.parser

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter
import com.gary.GalacticTrading.exception.ExceptionMsgConstants
import com.gary.GalacticTrading.exception.InvalidIntergalacticUnitException
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.*

/**
 * This class is responsible for parsing intergalactic units.
 */
@Component
class InterGalacticUnitParser(private val intergalacticUnitsToRomanStringConverter:
                              IntergalacticUnitsToRomanStringConverter) {
    /**
     * This map is used to store the intergalactic unit and roman letter.
     * NB: MutableMap is used here for modification purposes.
     */
    val interGalacticUnits: MutableMap<String, String> = HashMap()
    private val log = KotlinLogging.logger {}

    /**
     * This method is used to parse intergalactic units.
     * @param interGalacticUnits intergalactic units definition string to be parsed
     */
    fun parseIntergalacticUnits(interGalacticUnits: String) {
        val interGalacticUnitArray =
            interGalacticUnits.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (interGalacticUnitArray.size != 3) {
            throw InvalidIntergalacticUnitException(ExceptionMsgConstants.INVALID_INTERGALACTIC_UNIT)
        }
        log.debug(
            "Parsed intergalactic unit: {} -> {}", interGalacticUnitArray[0],
            interGalacticUnitArray[interGalacticUnitArray.size - 1]
        )
        this.interGalacticUnits[interGalacticUnitArray[0].lowercase(Locale.getDefault())] =
            interGalacticUnitArray[interGalacticUnitArray.size - 1].uppercase(Locale.getDefault())
        intergalacticUnitsToRomanStringConverter.setInterGalacticUnits(this.interGalacticUnits)
    }

    fun reset() {
        interGalacticUnits.clear()
    }
}

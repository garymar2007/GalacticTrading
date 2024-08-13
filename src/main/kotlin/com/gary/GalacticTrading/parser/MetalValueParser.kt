package com.gary.GalacticTrading.parser

import com.gary.GalacticTrading.exception.ExceptionMsgConstants
import com.gary.GalacticTrading.exception.InvalidMetalValueDefinitionException
import com.gary.GalacticTrading.utils.InputRegEx
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.*

/**
 *
 */
@Component
class MetalValueParser() {
    var interGalacticUnitString: String = ""
    var metalName: String = ""
    var value: Int = 0
    private val log = KotlinLogging.logger {}

    fun parseMetalValue(metalValueDefn: String) {
        if (metalValueDefn.isEmpty() ||
            !metalValueDefn.matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex())) {
            throw InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS)
        }
        val metalValueArray = metalValueDefn
            .split(" ".toRegex())
            .filter { s: String -> s != "" }.toTypedArray()
        val indexOfIs = metalValueArray.indexOf("is")
        this.metalName = convertToUpperCaseForFirstLetter(metalValueArray[indexOfIs - 1]) ?: ""
        log.debug("Parsed metal value: metal name -> {}", metalName)
        if (metalValueArray[indexOfIs + 1].chars().allMatch { codePoint: Int ->
                Character.isDigit(
                    codePoint
                )
            }) {
            this.value = metalValueArray[indexOfIs + 1].toInt()
            log.debug("Parsed total value: value -> {}", this.value)
        } else {
            throw InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS)
        }

        for (i in 0 until indexOfIs - 1) {
            if (interGalacticUnitString.isEmpty()) {
                interGalacticUnitString = metalValueArray[i] + " "
            } else {
                interGalacticUnitString += metalValueArray[i] + " "
            }
        }
        interGalacticUnitString = interGalacticUnitString.trim { it <= ' ' }
        log.debug("Parsed interGalacticUnitString: string -> {}", interGalacticUnitString)
    }

    private fun convertToUpperCaseForFirstLetter(str: String?): String? {
        if (str == null || str.isEmpty()) {
            return str
        }

        return str.substring(0, 1).uppercase(Locale.getDefault()) + str.substring(1)
    }

    fun reset() {
        interGalacticUnitString = ""
        metalName = ""
        value = 0
    }
}

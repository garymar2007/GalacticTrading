package com.gary.GalacticTrading.calculator

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter
import com.gary.GalacticTrading.converter.RomanStringToIntegerConverter
import com.gary.GalacticTrading.exception.ExceptionMsgConstants
import com.gary.GalacticTrading.exception.InvalidMetalValueDefinitionException
import com.gary.GalacticTrading.validator.RomanSymbolRules
import mu.KotlinLogging
import org.springframework.stereotype.Service

/**
 * This class is used to calculate the value of metal and multiple.
 */
@Service
class MetalAndMultipleCalculator(private val romanSymbolRules: RomanSymbolRules,
                                 private val romanStringToIntegerConverter: RomanStringToIntegerConverter,
                                 private val intergalacticUnitsToRomanStringConverter: IntergalacticUnitsToRomanStringConverter) {
    private val log = KotlinLogging.logger {}

    /**
     * This map is used to store the metal name and multiple.
     */
    val metalNameMultiplerMap: MutableMap<String, Double> = HashMap()

    /**
     * This method is used to calculate the value of metal and multiple.
     * @param metalName
     * @param interGalacticUnits
     * @return
     */
    fun initializeMetalAndMultipler(interGalacticUnits: String, metalName: String, value: Int) {
        val romanString = intergalacticUnitsToRomanStringConverter
            .convertIntergalacticUnitsToRomanString(interGalacticUnits)
        if (!romanSymbolRules.validateRomanSymbols(romanString)) {
            log.error("Invalid Roman String: {}", romanString)
            throw InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS)
        }

        val totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString)
        if (totalValue == 0) {
            log.error("Invalid Roman String: {}", romanString)
            throw InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS)
        }

        val multipler = value.toDouble() / totalValue
//        metalNameMultiplerMap = metalNameMultiplerMap.mapNotNull { (key, value) ->
//            when(key) {
//                metalName -> key to multipler
//                else -> key to value
//            }
//        }.toMap()
        metalNameMultiplerMap[metalName] = multipler
    }

    fun calculateMetalValue(interGalacticUnits: String, metalName: String): Int {
        val romanString = intergalacticUnitsToRomanStringConverter
            .convertIntergalacticUnitsToRomanString(interGalacticUnits)
        if (!romanSymbolRules.validateRomanSymbols(romanString)) {
            log.error("Invalid Roman String: {}", romanString)
            throw InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_UNITS_IN_QUERY)
        }

        val totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString)
        if (metalName.isEmpty() || !metalNameMultiplerMap.containsKey(metalName)
            || metalNameMultiplerMap[metalName] == null) {
            return totalValue
        }
        return metalNameMultiplerMap[metalName]?.let {
            (totalValue * it).toInt()
        } ?: totalValue
    }
}

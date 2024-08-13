package com.gary.GalacticTrading.converter

import org.springframework.stereotype.Service

/**
 * This class is used to convert Roman String to Integer
 */
@Service
class RomanStringToIntegerConverter {
    /**
     * This method is used to convert Roman String to Integer
     * @param romanString
     * @return
     */
    fun convertRomanStringToInteger(romanString: String): Int {
        var result = 0
        var prev = 0
        for (i in romanString.length - 1 downTo 0) {
            val current =
                ROMAN_TO_INTEGER_MAP[romanString[i].toString()]!!
            if (current < prev) {
                result -= current
            } else {
                result += current
            }
            prev = current
        }
        return result
    }

    companion object {
        private val ROMAN_TO_INTEGER_MAP: Map<String, Int> = mapOf(
            "I" to 1,
            "V" to 5,
            "X" to 10,
            "L" to 50,
            "C" to 100,
            "D" to 500,
            "M" to 1000
        )
    }
}

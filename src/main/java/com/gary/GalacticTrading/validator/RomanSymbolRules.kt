package com.gary.GalacticTrading.validator

import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class RomanSymbolRules {
    private val log = KotlinLogging.logger {}
    fun validateRomanSymbols(romanSymbol: String): Boolean {
        var isValid: Boolean
        //Rule 1: Format of Roman Symbol
        this.log.debug("Validating Roman Symbol Rules...")
        val p = Pattern.compile(ROMAN_SYMBOL_REGEX)
        val m = p.matcher(romanSymbol)

        isValid = m.matches()

        if (!isValid) {
            this.log.debug("Failed on Rule 1 - Invalid Roman Symbol: {}", romanSymbol)
            return false
        }

        //Rule 2: Occurences - "I", "X", "C" and "M" should not have more than 3 consecutive occurences,
        // and "D", "L" and "V" should not have more than 1 occurence.
        isValid = !romanSymbol.matches(".*M{4}.*".toRegex()) && !romanSymbol.matches(".*C{4}.*".toRegex())
                && !romanSymbol.matches(".*X{4}.*".toRegex()) && !romanSymbol.matches(".*I{4}.*".toRegex())
                && !romanSymbol.matches(".*[DLV]{2}.*".toRegex())

        if (!isValid) {
            this.log.debug("Failed on Rule 2 - wrong number of occurences: {}", romanSymbol)
            return false
        }

        //Rule 3: Substraction - "I", "X" and "C" can be subtracted only from the next two higher values.
        isValid = !romanSymbol.matches(".*I[LCDM].*".toRegex()) && !romanSymbol.matches(".*X[DM].*".toRegex())
                && !romanSymbol.matches(".*VX.*".toRegex()) && !romanSymbol.matches(".*LC.*".toRegex())
                && !romanSymbol.matches(".*DM.*".toRegex())

        if (!isValid) {
            this.log.debug("Failed on Rule 3 - wrong order for substraction: {}", romanSymbol)
            return false
        }

        return true
    }

    companion object {
        private const val ROMAN_SYMBOL_REGEX = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"
    }
}

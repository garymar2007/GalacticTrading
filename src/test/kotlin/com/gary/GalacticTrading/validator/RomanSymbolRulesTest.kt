package com.gary.GalacticTrading.validator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class RomanSymbolRulesTest {
    private val romanSymbolRules = RomanSymbolRules()

    @Test
    fun `test that validation on valid roman symbol`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("DCLXIV"))
    }

    @Test
    fun `test that validation on another valid roman symbol`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("MCMXLIV"))
    }

    @Test
    fun `test that validation on valid roman symbol again`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("MCMLXXII"))
    }

    @Test
    fun `test that validation on another valid roman symbol again`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("MMMCCCL"))
    }

    @Test
    fun `test that validation on another valid roman symbol again and again`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("CXLIV"))
    }

    @Test
    fun `test that validation on last valid roman symbol`() {
        Assertions.assertTrue(romanSymbolRules.validateRomanSymbols("XXXIX"))
    }

    @Test
    fun `test that validation on invalid roman symbols`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("MCMXLIVB"))
    }

    @Test
    fun `test that validation failed on rule 2 - exceeding three occurrences for some symbols`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("CCCLXXXXVIIII"))
    }

    @Test
    fun `test that validation failed on rule 2 - exceeding one occurrences for some symbols`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("CCCLLXXXVIIII"))
    }

    @Test
    fun `test that validation failed with wrong occurrences for some symbols`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("DD"))
    }

    @Test
    fun `test that validation failed on rule 3 - wrong substraction Of IC`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("ICX"))
    }

    @Test
    fun `test that validation failed on rule 3 - wrong substraction Of XD`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("XDIII"))
    }

    @Test
    fun `test that validation failed on rule 3 - wrong substraction Of VX`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("VXIII"))
    }

    @Test
    fun `test that validation failed on rule 3 - wrong substraction Of LC`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("DLCXIII"))
    }

    @Test
    fun `test that validation failed on rule 3 - wrong substraction Of DM`() {
        Assertions.assertFalse(romanSymbolRules.validateRomanSymbols("DMXIII"))
    }
}
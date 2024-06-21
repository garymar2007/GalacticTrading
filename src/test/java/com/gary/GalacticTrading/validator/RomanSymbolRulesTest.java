package com.gary.GalacticTrading.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanSymbolRulesTest {
    private final RomanSymbolRules romanSymbolRules = new RomanSymbolRules();

    @Test
    void validateRomanSymbolsReturnValid() {
        assertTrue(romanSymbolRules.validateRomanSymbols("DCLXIV"));
    }

    @Test
    void validateRomanSymbolsReturnValid1() {
        assertTrue(romanSymbolRules.validateRomanSymbols("MCMXLIV"));
    }

    @Test
    void validateRomanSymbolsReturnValid2() {
        assertTrue(romanSymbolRules.validateRomanSymbols("MCMLXXII"));
    }

    @Test
    void validateRomanSymbolsReturnValid3() {
        assertTrue(romanSymbolRules.validateRomanSymbols("MMMCCCL"));
    }

    @Test
    void validateRomanSymbolsReturnValid4() {
        assertTrue(romanSymbolRules.validateRomanSymbols("CXLIV"));
    }

    @Test
    void validateRomanSymbolsReturnValid5() {
        assertTrue(romanSymbolRules.validateRomanSymbols("XXXIX"));
    }

    @Test
    void validateRomanSymbolsFailedRule1() {
        assertFalse(romanSymbolRules.validateRomanSymbols("MCMXLIVB"));
    }
    @Test
    void validateRomanSymbolsFailedRule2WithExceedingThreeOccurences() {
        assertFalse(romanSymbolRules.validateRomanSymbols("CCCLXXXXVIIII"));
    }

    @Test
    void validateRomanSymbolsFailedRule2WithExceedingOneOccurrence() {
        assertFalse(romanSymbolRules.validateRomanSymbols("CCCLLXXXVIIII"));
    }

    @Test
    void validateRomanSymbolsFailedRule2WithWrongOcurrence() {
        assertFalse(romanSymbolRules.validateRomanSymbols("DD"));
    }

    @Test
    void validateRomanSymbolsFailedRule3WithWrongSubstractionOfIC() {
        assertFalse(romanSymbolRules.validateRomanSymbols("ICX"));
    }

    @Test
    void validateRomanSymbolsFailedRule3WithWrongSubstractionOfXD() {
        assertFalse(romanSymbolRules.validateRomanSymbols("XDIII"));
    }

    @Test
    void validateRomanSymbolsFailedRule3WithWrongSubstractionOfVX() {
        assertFalse(romanSymbolRules.validateRomanSymbols("VXIII"));
    }

    @Test
    void validateRomanSymbolsFailedRule3WithWrongSubstractionOfLC() {
        assertFalse(romanSymbolRules.validateRomanSymbols("DLCXIII"));
    }

    @Test
    void validateRomanSymbolsFailedRule3WithWrongSubstractionOfDM() {
        assertFalse(romanSymbolRules.validateRomanSymbols("DMXIII"));
    }
}
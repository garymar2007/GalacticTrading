package com.gary.GalacticTrading.calculator;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.converter.RomanStringToIntegerConverter;
import com.gary.GalacticTrading.exception.ExceptionMsgConstants;
import com.gary.GalacticTrading.exception.InvalidMetalValueDefinitionException;
import com.gary.GalacticTrading.validator.RomanSymbolRules;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to calculate the value of metal and multiple.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class MetalAndMultipleCalculator {
    private final RomanSymbolRules romanSymbolRules;
    private final RomanStringToIntegerConverter romanStringToIntegerConverter;
    private final IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;
    /**
     * This map is used to store the metal name and multiple.
     */
    private Map<String, Double> metalNameMultiplerMap = new HashMap<>();

    /**
     * This method is used to calculate the value of metal and multiple.
     * @param metalName
     * @param interGalacticUnits
     * @return
     */
    public void initializeMetalAndMultipler(final String interGalacticUnits, final String metalName, final Integer value) {
        if (metalNameMultiplerMap.containsKey(metalName)) {
            log.warn("Metal {} already initialized with value {}! Skipping...",
                    metalName, metalNameMultiplerMap.get(metalName));
            return;
        }

        final String romanString = intergalacticUnitsToRomanStringConverter
                .convertIntergalacticUnitsToRomanString(interGalacticUnits);
        if (!romanSymbolRules.validateRomanSymbols(romanString)) {
            log.error("Invalid Roman String: {}", romanString);
            throw new InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS);
        }

        final int totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString);
        if (totalValue == 0) {
            log.error("Invalid Roman String: {}", romanString);
            throw new InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_METAL_VALUE_DEFINITIONS);
        }

        final double multipler = (double)value / totalValue;
        metalNameMultiplerMap.put(metalName, multipler);
    }

    public int calculateMetalValue(final String interGalacticUnits, final String metalName) {
        final String romanString = intergalacticUnitsToRomanStringConverter
                .convertIntergalacticUnitsToRomanString(interGalacticUnits);
        if (!romanSymbolRules.validateRomanSymbols(romanString)) {
            log.error("Invalid Roman String: {}", romanString);
            throw new InvalidMetalValueDefinitionException(ExceptionMsgConstants.INVALID_UNITS_IN_QUERY);
        }

        final int totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString);
        if (metalName == null) {
            return totalValue;
        }

        return (int)(totalValue * metalNameMultiplerMap.get(metalName));
    }
}

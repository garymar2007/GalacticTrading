package com.gary.GalacticTrading.calculator;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.converter.RomanStringToIntegerConverter;
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
    private Map<String, Double> metalNameMultiplerMap = new HashMap<>();
    private final RomanStringToIntegerConverter romanStringToIntegerConverter;
    private final IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;

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
        final int totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString);
        final double multipler = (double)value / totalValue;
        metalNameMultiplerMap.put(metalName, multipler);
    }

    public double calculateMetalValue(final String interGalacticUnits, final String metalName) {
        final String romanString = intergalacticUnitsToRomanStringConverter
                .convertIntergalacticUnitsToRomanString(interGalacticUnits);
        final int totalValue = romanStringToIntegerConverter.convertRomanStringToInteger(romanString);
        if (metalName == null) {
            return totalValue;
        }

        return totalValue * metalNameMultiplerMap.get(metalName);
    }
}

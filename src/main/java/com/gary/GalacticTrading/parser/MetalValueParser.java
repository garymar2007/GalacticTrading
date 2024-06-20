package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.exception.InvalidMetalValueDefinitionException;
import com.gary.GalacticTrading.utils.InputRegEx;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 *
 */
@Component
@Slf4j
@Getter
public class MetalValueParser {
    private String interGalacticUnitString;
    private String metalName;
    private Integer value;

    public void parseMetalValue(final String metalValueDefn) {
        if (metalValueDefn == null || metalValueDefn.isEmpty() ||
                !metalValueDefn.matches(InputRegEx.METAL_VALUE_DEFINITION)) {
            throw new InvalidMetalValueDefinitionException("Error: Invalid metal value definition!");
        }
        String[] metalValueArray = Arrays.stream(metalValueDefn.split(" ")).filter(s -> !s.equals(""))
                .toArray(String[]::new);
        final int indexOfIs = Arrays.asList(metalValueArray).indexOf("is");
        metalName = metalValueArray[indexOfIs - 1];
        log.debug("Parsed metal value: metal name -> {}", metalName);
        if (metalValueArray[indexOfIs + 1].chars().allMatch(Character::isDigit)) {
            value = Integer.parseInt(metalValueArray[indexOfIs + 1]);
            log.debug("Parsed total value: value -> {}", value);
        } else {
            throw new InvalidMetalValueDefinitionException("Error: Invalid metal value definition!");
        }

        for (int i = 0; i <  indexOfIs -1; i++) {
            if (interGalacticUnitString == null) {
                interGalacticUnitString = metalValueArray[i] + " ";
            } else {
                interGalacticUnitString += metalValueArray[i] + " ";
            }
        }
        interGalacticUnitString = interGalacticUnitString.trim();
        log.debug("Parsed interGalacticUnitString: string -> {}", interGalacticUnitString);
    }

    public void reset() {
        interGalacticUnitString = null;
        metalName = null;
        value = null;
    }
}

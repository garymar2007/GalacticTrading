package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.exception.ExceptionMsgConstants;
import com.gary.GalacticTrading.exception.InvalidIntergalacticUnitException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for parsing intergalactic units.
 * */
@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class InterGalacticUnitParser {
    private final IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;
    /**
     * This map is used to store the intergalactic unit and roman letter.
     */
    private final Map<String, String> interGalacticUnits = new HashMap<>();

    /**
     * This method is used to parse intergalactic units.
     * @param interGalacticUnits intergalactic units definition string to be parsed
     */
    public void parseIntergalacticUnits(final String interGalacticUnits) {
        String[] interGalacticUnitArray = interGalacticUnits.split(" ");
        if (interGalacticUnitArray.length != 3) {
            throw new InvalidIntergalacticUnitException(ExceptionMsgConstants.INVALID_INTERGALACTIC_UNIT);
        }
        log.debug("Parsed intergalactic unit: {} -> {}", interGalacticUnitArray[0],
                interGalacticUnitArray[interGalacticUnitArray.length - 1]);
        this.interGalacticUnits.put(interGalacticUnitArray[0].toLowerCase(),
                interGalacticUnitArray[interGalacticUnitArray.length - 1].toUpperCase());
        intergalacticUnitsToRomanStringConverter.setInterGalacticUnits(this.interGalacticUnits);
    }

    public void reset() {
        interGalacticUnits.clear();
    }
}

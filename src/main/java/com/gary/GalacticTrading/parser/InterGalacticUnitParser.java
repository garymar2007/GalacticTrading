package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
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
    private Map<String, String> interGalacticUnits = new HashMap<>();

    /**
     * This method is used to parse intergalactic units.
     * @param interGalacticUnits
     * @return
     */
    public void parseIntergalacticUnits(final String interGalacticUnits) {
        String[] interGalacticUnitArray = interGalacticUnits.split(" ");
        if (interGalacticUnitArray.length != 3) {
            throw new InvalidIntergalacticUnitException("Error: Invalid intergalactic unit definition!");
        }
        log.debug("Parsed intergalactic unit: {} -> {}", interGalacticUnitArray[0],
                interGalacticUnitArray[interGalacticUnitArray.length - 1]);
        this.interGalacticUnits.put(interGalacticUnitArray[0], interGalacticUnitArray[interGalacticUnitArray.length - 1]);
        intergalacticUnitsToRomanStringConverter.setInterGalacticUnits(this.interGalacticUnits);
    }
}

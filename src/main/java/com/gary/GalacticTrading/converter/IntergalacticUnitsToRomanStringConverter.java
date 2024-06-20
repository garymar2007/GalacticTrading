package com.gary.GalacticTrading.converter;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * This class is responsible for converting intergalactic units to roman string.
 */
@Service
public class IntergalacticUnitsToRomanStringConverter {
    private Map<String, String> interGalacticUnits;

    public IntergalacticUnitsToRomanStringConverter(Map<String, String> interGalacticUnits) {
        this.interGalacticUnits = interGalacticUnits;
    }

    /**
     * This method is used to convert intergalactic units to roman string.
     * @param interGalacticUnits
     * @return
     */
    public String convertIntergalacticUnitsToRomanString(String interGalacticUnits) {
        String[] interGalacticUnitArray = interGalacticUnits.split(" ");
        StringBuilder romanString = new StringBuilder();
        for (String interGalacticUnit : interGalacticUnitArray) {
            romanString.append(this.interGalacticUnits.get(interGalacticUnit));
        }
        return romanString.toString();
    }
}

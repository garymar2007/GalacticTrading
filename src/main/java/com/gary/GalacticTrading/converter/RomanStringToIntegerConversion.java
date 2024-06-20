package com.gary.GalacticTrading.converter;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to convert Roman String to Integer
 */
@Service
public class RomanStringToIntegerConversion {
    private static final Map<String, Integer> ROMAN_TO_INTEGER_MAP = new HashMap<>(){
        {
            put("I", 1);
            put("V", 5);
            put("X", 10);
            put("L", 50);
            put("C", 100);
            put("D", 500);
            put("M", 1000);
        }
    };

    /**
     * This method is used to convert Roman String to Integer
     * @param romanString
     * @return
     */
    public int convertRomanStringToInteger(String romanString) {
        int result = 0;
        int prev = 0;
        for (int i = romanString.length() - 1; i >= 0; i--) {
            int current = ROMAN_TO_INTEGER_MAP.get(String.valueOf(romanString.charAt(i)));
            if (current < prev) {
                result -= current;
            } else {
                result += current;
            }
            prev = current;
        }
        return result;
    }
}

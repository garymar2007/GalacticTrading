package com.gary.GalacticTrading.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RomanSymbolRules {

    public boolean validateRomanSymbols(String romanSymbol) {
        boolean isValid;
        //Rule 1: Format of Roman Symbol
        log.debug("Validating Roman Symbol Rules...");
        isValid = romanSymbol.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");

        if (!isValid) {
            log.debug("Failed on Rule 1 - Invalid Roman Symbol: {}", romanSymbol);
            return false;
        }

        //Rule 2: Occurences - "I", "X", "C" and "M" should not have more than 3 consecutive occurences,
        // and "D", "L" and "V" should not have more than 1 occurence.
        isValid = !romanSymbol.matches(".*M{4}.*") && !romanSymbol.matches(".*C{4}.*")
                && !romanSymbol.matches(".*X{4}.*") && !romanSymbol.matches(".*I{4}.*")
                && !romanSymbol.matches(".*[DLV]{2}.*");

        if (!isValid) {
            log.debug("Failed on Rule 2 - wrong number of occurences: {}", romanSymbol);
            return false;
        }

        //Rule 3: Substraction - "I", "X" and "C" can be subtracted only from the next two higher values.
        isValid = !romanSymbol.matches(".*I[LCDM].*") && !romanSymbol.matches(".*X[DM].*")
                && !romanSymbol.matches(".*VX.*") && !romanSymbol.matches(".*LC.*")
                && !romanSymbol.matches(".*DM.*");

        if (!isValid) {
            log.debug("Failed on Rule 3 - wrong order for substraction: {}", romanSymbol);
            return false;
        }

        return true;
    }
}

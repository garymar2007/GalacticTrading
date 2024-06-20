package com.gary.GalacticTrading.io;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter;
import com.gary.GalacticTrading.parser.InterGalacticUnitParser;
import com.gary.GalacticTrading.parser.MetalValueParser;
import com.gary.GalacticTrading.parser.QueryParser;
import com.gary.GalacticTrading.utils.InputRegEx;
import com.gary.GalacticTrading.utils.QueryConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class InputProcessor {
    private final InterGalacticUnitParser interGalacticUnitParser;
    private final MetalValueParser metalValueParser;
    private final MetalAndMultipleCalculator metalAndMultipleCalculator;
    private final IntergalacticUnitsToRomanStringConverter intergalacticUnitsToRomanStringConverter;
    private final QueryParser queryParser;
    private final OutputProcessor outputProcessor;

    public void processInputFromFile() {
        log.info("Processing input from file...");
        try {
            File file = new ClassPathResource("input.txt").getFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.debug("Processing line: {}", line);
                if(line.matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION)) {
                    log.debug("Processing intergalactic unit definition...");
                    interGalacticUnitParser.parseIntergalacticUnits(line);
                } else if(line.matches(InputRegEx.METAL_VALUE_DEFINITION)) {
                    log.debug("Processing metal value definition...");
                    metalValueParser.parseMetalValue(line);
                    metalAndMultipleCalculator.initializeMetalAndMultipler(metalValueParser.getInterGalacticUnitString(),
                            metalValueParser.getMetalName(), metalValueParser.getValue());
                    metalValueParser.reset();
                } else if(line.matches(InputRegEx.QUERY)) {
                    log.debug("Processing query...");
                    String[] unitsAndMetalQuery = queryParser.parseQuery(line, metalAndMultipleCalculator.getMetalNameMultiplerMap().keySet(),
                            interGalacticUnitParser.getInterGalacticUnits().keySet());
                    outputProcessor.saveToOutputFile(unitsAndMetalQuery);
                } else {
                    outputProcessor.saveToOutputFile(new String[]{QueryConstants.INVALID_QUERY});
                }
            }
            outputProcessor.writeToFile();
        } catch (IOException e) {
            log.error("Error: Unable to read file input.txt");
        }
    }
}

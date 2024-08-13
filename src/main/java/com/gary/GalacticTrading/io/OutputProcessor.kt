package com.gary.GalacticTrading.io;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Getter
public class OutputProcessor {
    private final MetalAndMultipleCalculator metalAndMultipleCalculator;
    private List<String> contents = new ArrayList<>();

    public void saveForOutput(final String[] unitsAndMetalQuery) {
        log.debug("Saving to buffer and ready for output file...");

            if (unitsAndMetalQuery.length == 1) {
                contents.add(unitsAndMetalQuery[0]);
                return;
            }

            String temp = String.join(" ", unitsAndMetalQuery);
            temp += " is ";
            final int length = unitsAndMetalQuery.length;
            if (metalAndMultipleCalculator.getMetalNameMultiplerMap().get(unitsAndMetalQuery[length - 1]) != null) {
                final String metalName = unitsAndMetalQuery[length - 1];
                final String interGalacticUnits = String.join(" ", unitsAndMetalQuery).replace(metalName, "");
                temp += String.valueOf(
                        metalAndMultipleCalculator.calculateMetalValue(interGalacticUnits, metalName));
                temp += " Credits";
            } else {
                temp += String.valueOf(metalAndMultipleCalculator.calculateMetalValue(
                        String.join(" ", unitsAndMetalQuery), null));
            }
            contents.add(temp);
    }

    public void writeToFile(String outputFileName) throws IOException {
        File resourcesDir = new File("src/main/resources/");
        File file = new File(resourcesDir.getAbsolutePath() + File.separator + outputFileName);
        if (file.createNewFile()) {
            log.info("File created: " + file.getName());
        } else {
            log.info("File already exists.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String str : contents) {
                writer.write(str + System.lineSeparator());
            }

            log.info("Output written to file: {}", file.getAbsolutePath());
            contents.clear();
        } catch (IOException e) {
            log.error("Error: Unable to write to file output.txt");
            throw e;
        }
    }

    public void reset() {
        contents.clear();
    }

}

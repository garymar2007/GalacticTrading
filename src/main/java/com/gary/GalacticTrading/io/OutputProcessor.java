package com.gary.GalacticTrading.io;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutputProcessor {
    private final MetalAndMultipleCalculator metalAndMultipleCalculator;
    private final ResourceLoader resourceLoader;
    private List<String> contents = new ArrayList<>();

    public void saveToOutputFile(final String[] unitsAndMetalQuery) {
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

    public void writeToFile() {
        Resource resource =  resourceLoader.getResource("classpath:output.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resource.getFile()))) {
            for (String str : contents) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            log.error("Error: Unable to write to file output.txt");
        }
    }

}

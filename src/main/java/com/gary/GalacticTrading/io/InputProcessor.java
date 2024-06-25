package com.gary.GalacticTrading.io;

import com.gary.GalacticTrading.utils.InputRegEx;
import com.gary.GalacticTrading.utils.QueryConstants;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Getter
public class InputProcessor {
    private List<String> interGalacticUnitDefinitions = new ArrayList<>();
    private List<String> metalValueDefinitions = new ArrayList<>();
    private List<String> queryDefinitions = new ArrayList<>();
    private String invalidQuery = null;

    public boolean processInputFromFile(String fileName) throws IOException {
        log.info("Processing input from file...");

        try (InputStream is = new  FileInputStream(new ClassPathResource(fileName).getFile());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.debug("Processing line: {}", line);
                if(line.matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION)) {
                    interGalacticUnitDefinitions.add(line);
                } else if(line.matches(InputRegEx.METAL_VALUE_DEFINITION)) {
                    metalValueDefinitions.add(line);
                } else if(line.matches(InputRegEx.QUERY)) {
                    queryDefinitions.add(line);
                } else {
                    invalidQuery = QueryConstants.INVALID_QUERY;
                }
            }
            return true;
        } catch (IOException e) {
            log.error("Error: Unable to read file input.txt");
            throw e;
        }
    }

    public void reset() {
        interGalacticUnitDefinitions.clear();
        metalValueDefinitions.clear();
        queryDefinitions.clear();
        invalidQuery = null;
    }
}

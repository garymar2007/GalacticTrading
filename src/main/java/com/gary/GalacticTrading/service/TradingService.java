package com.gary.GalacticTrading.service;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import com.gary.GalacticTrading.exception.*;
import com.gary.GalacticTrading.io.InputProcessor;
import com.gary.GalacticTrading.io.OutputProcessor;
import com.gary.GalacticTrading.parser.InterGalacticUnitParser;
import com.gary.GalacticTrading.parser.MetalValueParser;
import com.gary.GalacticTrading.parser.QueryParser;
import com.gary.GalacticTrading.utils.QueryConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for trading.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TradingService {
    private final InputProcessor inputProcessor;
    private final InterGalacticUnitParser interGalacticUnitParser;
    private final MetalValueParser metalValueParser;
    private final QueryParser queryParser;
    private final MetalAndMultipleCalculator metalAndMultipleCalculator;
    private final OutputProcessor outputProcessor;

    public void trade(String inputFileName, String outputFileName) throws IOException {
        log.debug("Galactic Trading Process...");
        try {
            boolean isInputProcessedSuccessfully = inputProcessor.processInputFromFile(inputFileName);

            if (isInputProcessedSuccessfully) {
                List<String> interGalacticUnitDefinitions = inputProcessor.getInterGalacticUnitDefinitions();
                processInterGalacticUnitDefinitions(interGalacticUnitDefinitions);

                List<String> metalValueDefinitions = inputProcessor.getMetalValueDefinitions();
                processMetalValueDefinitions(metalValueDefinitions);

                List<String> queryDefinitions = inputProcessor.getQueryDefinitions();
                processQueryDefinitions(queryDefinitions);

                if (inputProcessor.getInvalidQuery() != null) {
                    outputProcessor.saveForOutput(new String[]{inputProcessor.getInvalidQuery()});
                }

                outputProcessor.writeToFile(outputFileName);
                cleanup();
            } else {
                throw new FailedProcessInputFile(ExceptionMsgConstants.UNABLE_PROCESS_INPUT_FILE);
            }
        } catch (FailedProcessInputFile | NoInterGalacticUnitDefinitionsFoundException |
                 InvalidIntergalacticUnitException | NoQueryFoundException
                 | NoMetalValueDefinitionsFoundException | InvalidMetalValueDefinitionException e) {
            log.error(e.getMessage());
            handleExceptions(e, outputFileName);
            throw e;
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    private void handleExceptions(Exception e, String outputFileName) throws IOException {
        if(e instanceof FailedProcessInputFile) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.UNABLE_TO_PROCESS_INPUT_FILE});
            outputProcessor.writeToFile(outputFileName);
        }

        if (e instanceof NoInterGalacticUnitDefinitionsFoundException) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.NO_INTERGALACTIC_UNIT_DEFN_FOUND});
            outputProcessor.writeToFile(outputFileName);
        }

        if (e instanceof NoMetalValueDefinitionsFoundException) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.NO_METAL_VALUE_DEFN_FOUND});
            outputProcessor.writeToFile(outputFileName);
        }

        if (!outputProcessor.getContents().isEmpty() && e instanceof InvalidMetalValueDefinitionException ) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.QUERY_WITH_INVALID_UNIT_OF_METAL});
            outputProcessor.writeToFile(outputFileName);
        }

        if (e instanceof InvalidIntergalacticUnitException) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.QUERY_WITH_INVALID_UNIT});
            outputProcessor.writeToFile(outputFileName);
        }

        if (e instanceof NoQueryFoundException) {
            outputProcessor.saveForOutput(new String[]{QueryConstants.NO_QUERY_FOUND});
            outputProcessor.writeToFile(outputFileName);
        }
    }

    private void cleanup() {
        interGalacticUnitParser.reset();
        metalValueParser.reset();
        inputProcessor.reset();
        outputProcessor.reset();
    }

    private void processInterGalacticUnitDefinitions(List<String> interGalacticUnitDefinitions) {
        if (interGalacticUnitDefinitions == null || interGalacticUnitDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.INTERGALACTIC_DEFN_NOT_FOUND);
            throw new NoInterGalacticUnitDefinitionsFoundException(ExceptionMsgConstants.INTERGALACTIC_DEFN_NOT_FOUND);
        }

        for (String interGalacticUnitDefinition : interGalacticUnitDefinitions) {
            interGalacticUnitParser.parseIntergalacticUnits(interGalacticUnitDefinition);
        }
    }

    private void processMetalValueDefinitions(List<String> metalValueDefinitions) {
        if (metalValueDefinitions == null || metalValueDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.METAL_VALUE_DEFN_NOT_FOUND);
                throw new NoMetalValueDefinitionsFoundException(ExceptionMsgConstants.METAL_VALUE_DEFN_NOT_FOUND);
        }

        for (String metalValueDefinition : metalValueDefinitions) {
            metalValueParser.parseMetalValue(metalValueDefinition);
            metalAndMultipleCalculator.initializeMetalAndMultipler(metalValueParser.getInterGalacticUnitString(),
                            metalValueParser.getMetalName(), metalValueParser.getValue());
            metalValueParser.reset();
        }
    }

    private void processQueryDefinitions(List<String> queryDefinitions) {
        if (queryDefinitions == null || queryDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.NO_QUERY_FOUND);
            throw new NoQueryFoundException(ExceptionMsgConstants.NO_QUERY_FOUND);
        }

        for (String queryDefinition : queryDefinitions) {
            String[] unitsAndMetalQuery = queryParser.parseQuery(queryDefinition);
            outputProcessor.saveForOutput(unitsAndMetalQuery);
        }
    }
}

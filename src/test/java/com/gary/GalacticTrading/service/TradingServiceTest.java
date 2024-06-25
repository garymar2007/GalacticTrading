package com.gary.GalacticTrading.service;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import com.gary.GalacticTrading.exception.FailedProcessInputFile;
import com.gary.GalacticTrading.exception.NoInterGalacticUnitDefinitionsFoundException;
import com.gary.GalacticTrading.exception.NoMetalValueDefinitionsFoundException;
import com.gary.GalacticTrading.exception.NoQueryFoundException;
import com.gary.GalacticTrading.io.InputProcessor;
import com.gary.GalacticTrading.io.OutputProcessor;
import com.gary.GalacticTrading.parser.InterGalacticUnitParser;
import com.gary.GalacticTrading.parser.MetalValueParser;
import com.gary.GalacticTrading.parser.QueryParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class TradingServiceTest {
    @InjectMocks
    private TradingService tradingService;
    @Mock
    private InputProcessor inputProcessor;
    @Mock
    private InterGalacticUnitParser interGalacticUnitParser;
    @Mock
    private MetalValueParser metalValueParser;
    @Mock
    private QueryParser queryParser;
    @Mock
    private OutputProcessor outputProcessor;
    @Mock
    private MetalAndMultipleCalculator metalAndMultipleCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTradeThrowFailedProcessInptFileException() throws IOException {
        when(inputProcessor.processInputFromFile(any())).thenReturn(false);

        assertThrows(FailedProcessInputFile.class, () -> tradingService.trade("inputFileName", "outputFileName"));
    }

    @Test
    void testTradeThrowNoInterGalacticUnitDefinitionsFoundException() throws IOException {
        when(inputProcessor.processInputFromFile(any())).thenReturn(true);
        when(inputProcessor.getInterGalacticUnitDefinitions()).thenReturn(null);

        assertThrows(NoInterGalacticUnitDefinitionsFoundException.class, () -> tradingService.trade("inputFileName", "outputFileName"));
    }

    @Test
    void testTradeThrowNoMetalValueDefinitionsFoundException() throws IOException {
        when(inputProcessor.processInputFromFile(any())).thenReturn(true);
        when(inputProcessor.getInterGalacticUnitDefinitions()).thenReturn(List.of("glob is I", "prok is V", "pish is X", "tegj is L"));
        doNothing().when(interGalacticUnitParser).parseIntergalacticUnits(any());

        when(inputProcessor.getMetalValueDefinitions()).thenReturn(null);
        assertThrows(NoMetalValueDefinitionsFoundException.class, () -> tradingService.trade("inputFileName", "outputFileName"));
    }

    @Test
    void testTradeThrowNoQueryFoundException() throws IOException {
        when(inputProcessor.processInputFromFile(any())).thenReturn(true);
        when(inputProcessor.getInterGalacticUnitDefinitions()).thenReturn(List.of("glob is I", "prok is V", "pish is X", "tegj is L"));
        doNothing().when(interGalacticUnitParser).parseIntergalacticUnits(any());
        when(inputProcessor.getMetalValueDefinitions()).thenReturn(List.of("glob prok Silver is 34 Credits"));
        doNothing().when(metalValueParser).parseMetalValue(any());
        when(metalValueParser.getInterGalacticUnitString()).thenReturn("glob prok");
        when(metalValueParser.getMetalName()).thenReturn("Silver");
        when(metalValueParser.getValue()).thenReturn(34);
        doNothing().when(metalAndMultipleCalculator).initializeMetalAndMultipler(any(), any(), any());

        when(inputProcessor.getQueryDefinitions()).thenReturn(null);
        assertThrows(NoQueryFoundException.class, () -> tradingService.trade("inputFileName", "outputFileName"));
    }

    @Test
    void testTrade() throws IOException {
        List<String> queryDefinitions = List.of("how many Credits is glob prok Silver ?");
        List<String> metalValueDefinitions = List.of("glob prok Silver is 34 Credits");
        List<String> interGalacticUnitDefinitions = List.of("glob is I", "prok is V", "pish is X", "tegj is L");

        when(inputProcessor.processInputFromFile(any())).thenReturn(true);
        when(inputProcessor.getInterGalacticUnitDefinitions()).thenReturn(interGalacticUnitDefinitions);
        when(inputProcessor.processInputFromFile(any())).thenReturn(true);
        when(inputProcessor.getInterGalacticUnitDefinitions()).thenReturn(interGalacticUnitDefinitions);
        doNothing().when(interGalacticUnitParser).parseIntergalacticUnits(any());
        when(inputProcessor.getMetalValueDefinitions()).thenReturn(metalValueDefinitions);
        doNothing().when(metalValueParser).parseMetalValue(any());
        when(metalValueParser.getInterGalacticUnitString()).thenReturn("glob prok");
        when(metalValueParser.getMetalName()).thenReturn("Silver");
        when(metalValueParser.getValue()).thenReturn(34);
        doNothing().when(metalAndMultipleCalculator).initializeMetalAndMultipler(any(), any(), any());
        when(inputProcessor.getQueryDefinitions()).thenReturn(queryDefinitions);
        when(metalAndMultipleCalculator.getMetalNameMultiplerMap()).thenReturn(new HashMap<String, Double>() {{ put("Silver", 17.0);}});
        when(interGalacticUnitParser.getInterGalacticUnits()).thenReturn(new HashMap<String, String>() {{ put("glob", "I"); put("prok", "V");}});
        doNothing().when(outputProcessor).saveForOutput(any());
        when(inputProcessor.getInvalidQuery()).thenReturn(null);
        doNothing().when(outputProcessor).writeToFile(any());
        when(outputProcessor.getContents()).thenReturn(List.of("glob prok Silver is 34 Credits"));

        tradingService.trade("inputFileName", "outputFileName");
    }
}
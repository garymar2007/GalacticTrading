package com.gary.GalacticTrading;

import com.gary.GalacticTrading.service.TradingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class GalacticTradingApplicationTests {
	@Autowired
	private TradingService tradingService;

	@Test
	void testValidInputFile1() throws IOException {
		String inputFileName = "input.txt";
		String outputFileName = "output.txt";
		tradingService.trade(inputFileName, outputFileName);
	}

	@Test
	void testValidInputFile2() throws IOException {
		String inputFileName = "input2.txt";
		String outputFileName = "output2.txt";
		tradingService.trade(inputFileName, outputFileName);
	}

	@Test
	void testInvalidInputFile() throws IOException {
		String inputFileName = "invalid-input.txt";
		String outputFileName = "invalid-output.txt";
		tradingService.trade(inputFileName, outputFileName);
	}
}

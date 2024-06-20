package com.gary.GalacticTrading;

import com.gary.GalacticTrading.io.InputProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GalacticTradingApplicationTests {
	@Autowired
	private InputProcessor inputProcessor;

	@Test
	void contextLoads() {
	}

	@Test
	void testSampleInputFile() {
		inputProcessor.processInputFromFile();
	}

}

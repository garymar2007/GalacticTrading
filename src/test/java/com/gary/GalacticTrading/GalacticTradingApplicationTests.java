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
	void testSampleInputFile() throws IOException {
		tradingService.trade();
	}
}

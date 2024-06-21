package com.gary.GalacticTrading;

import com.gary.GalacticTrading.service.TradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static java.lang.System.exit;

@SpringBootApplication
@Slf4j
public class GalacticTradingApplication implements ApplicationRunner {
	@Autowired
	private TradingService tradingService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GalacticTradingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (args.getNonOptionArgs().size() == 2) {
			String inputFileName = args.getNonOptionArgs().get(0);
			String outputFileName = args.getNonOptionArgs().get(1);
			tradingService.trade(inputFileName, outputFileName);

			exit(0);
		} else {
			log.error("Invalid number of arguments. Please provide input and output file names.");
		}
	}
}

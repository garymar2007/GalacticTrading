package com.gary.GalacticTrading;

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator;
import com.gary.GalacticTrading.parser.InterGalacticUnitParser;
import com.gary.GalacticTrading.parser.MetalValueParser;
import com.gary.GalacticTrading.parser.QueryParser;
import com.gary.GalacticTrading.utils.InputRegEx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

@SpringBootApplication
@Slf4j
public class GalacticTradingApplication {
	public static void main(String[] args) {
		SpringApplication.run(GalacticTradingApplication.class, args);
	}
}

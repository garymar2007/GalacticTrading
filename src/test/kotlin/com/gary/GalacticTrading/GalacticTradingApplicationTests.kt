package com.gary.GalacticTrading

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator
import com.gary.GalacticTrading.io.InputProcessor
import com.gary.GalacticTrading.io.OutputProcessor
import com.gary.GalacticTrading.parser.InterGalacticUnitParser
import com.gary.GalacticTrading.parser.MetalValueParser
import com.gary.GalacticTrading.parser.QueryParser
import com.gary.GalacticTrading.service.TradingService
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class GalacticTradingApplicationTests: FunSpec() {
    @Autowired
    private lateinit var inputProcessor: InputProcessor
    @Autowired
    private lateinit var interGalacticUnitParser: InterGalacticUnitParser
    @Autowired
    private lateinit var metalValueParser: MetalValueParser
    @Autowired
    private lateinit var queryParser: QueryParser
    @Autowired
    private lateinit var metalAndMultipleCalculator: MetalAndMultipleCalculator
    @Autowired
    private lateinit var outputProcessor: OutputProcessor

    private lateinit var tradingService: TradingService

    init {
        extensions(SpringExtension)

        this.beforeTest {
            tradingService = TradingService(inputProcessor,
                interGalacticUnitParser, metalValueParser,
                queryParser, metalAndMultipleCalculator, outputProcessor)
        }

        this.test("valid input file 1") {
            val inputFileName = "input.txt"
            val outputFileName = "output.txt"
            tradingService.trade(inputFileName, outputFileName)
        }
    }
}

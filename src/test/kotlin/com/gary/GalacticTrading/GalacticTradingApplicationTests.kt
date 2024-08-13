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
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class GalacticTradingApplicationTests: FunSpec() {
    @MockBean
    private lateinit var inputProcessor: InputProcessor
    @MockBean
    private lateinit var interGalacticUnitParser: InterGalacticUnitParser
    @MockBean
    private lateinit var metalValueParser: MetalValueParser
    @MockBean
    private lateinit var queryParser: QueryParser
    @MockBean
    private lateinit var metalAndMultipleCalculator: MetalAndMultipleCalculator
    @MockBean
    private lateinit var outputProcessor: OutputProcessor

    @Autowired
    private lateinit var tradingService: TradingService

    init {
        extensions(SpringExtension)

        test("valid input file 1") {
            val inputFileName = "input.txt"
            val outputFileName = "output.txt"
            tradingService.trade(inputFileName, outputFileName)
        }
    }
}

package com.gary.GalacticTrading.service

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator
import com.gary.GalacticTrading.exception.FailedProcessInputFile
import com.gary.GalacticTrading.exception.NoInterGalacticUnitDefinitionsFoundException
import com.gary.GalacticTrading.exception.NoMetalValueDefinitionsFoundException
import com.gary.GalacticTrading.exception.NoQueryFoundException
import com.gary.GalacticTrading.io.InputProcessor
import com.gary.GalacticTrading.io.OutputProcessor
import com.gary.GalacticTrading.parser.InterGalacticUnitParser
import com.gary.GalacticTrading.parser.MetalValueParser
import com.gary.GalacticTrading.parser.QueryParser
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.mockk

internal class TradingServiceTest : FunSpec({
    val inputProcessor: InputProcessor = mockk<InputProcessor>()
    val interGalacticUnitParser: InterGalacticUnitParser = mockk<InterGalacticUnitParser>()
    val metalValueParser: MetalValueParser = mockk<MetalValueParser>()
    val queryParser: QueryParser = mockk<QueryParser>()
    val outputProcessor: OutputProcessor = mockk<OutputProcessor>()
    val metalAndMultipleCalculator: MetalAndMultipleCalculator = mockk<MetalAndMultipleCalculator>()

    val tradingService = TradingService(inputProcessor,
       interGalacticUnitParser, metalValueParser, queryParser,
        metalAndMultipleCalculator, outputProcessor
    )

    test("Test Trade Service to throw Failed Process Input File Exception") {
        every { inputProcessor.processInputFromFile("inputFileName") } returns false
        every { outputProcessor.saveForOutput(any()) } returns Unit
        every { outputProcessor.writeToFile("outputFileName") } returns Unit
        val exception = shouldThrow<FailedProcessInputFile> {
            tradingService.trade("inputFileName", "outputFileName")
        }
        exception.message should startWith("ERROR: Galactic Trading Process failed")
    }

    test("Test Trade Service to throw NoInterGalacticUnitDefinitionsFoundException") {
        every { inputProcessor.processInputFromFile("inputFileName") } returns true
        every { inputProcessor.interGalacticUnitDefinitions } returns mutableListOf()
        every { outputProcessor.saveForOutput(any()) } returns Unit
        every { outputProcessor.writeToFile("outputFileName") } returns Unit
        val exception = shouldThrow<NoInterGalacticUnitDefinitionsFoundException> {
            tradingService.trade("inputFileName", "outputFileName")
        }
        exception.message shouldBe("ERROR: No intergalactic unit definitions found!")
    }

    test("Test Trade Service to throw NoMetalValueDefinitionsFoundException") {
        every { inputProcessor.processInputFromFile("inputFileName") } returns true
        every { inputProcessor.interGalacticUnitDefinitions } returns mutableListOf("glob is I", "prok is V", "pish is X", "tegj is L")
        every { inputProcessor.metalValueDefinitions} returns mutableListOf()
        every { interGalacticUnitParser.parseIntergalacticUnits(any()) } returns Unit
        every { outputProcessor.saveForOutput(any()) } returns Unit
        every { outputProcessor.writeToFile("outputFileName") } returns Unit
        val exception = shouldThrow<NoMetalValueDefinitionsFoundException> {
            tradingService.trade("inputFileName", "outputFileName")
        }
        exception.message shouldBe("ERROR: No metal value definitions found!")
    }

    test("Test Trade Service to throw NoQueryFoundException") {
        every { inputProcessor.processInputFromFile("inputFileName") } returns true
        every { inputProcessor.interGalacticUnitDefinitions } returns mutableListOf("glob is I", "prok is V", "pish is X", "tegj is L")
        every { inputProcessor.metalValueDefinitions} returns mutableListOf("glob prok Silver is 34 Credits")
        every { metalValueParser.parseMetalValue(any()) } returns Unit
        every { metalValueParser.interGalacticUnitString } returns "glob prok"
        every { metalValueParser.metalName } returns "Silver"
        every { metalValueParser.value } returns 34
        every { metalAndMultipleCalculator.initializeMetalAndMultipler(any(), any(), any()) } returns Unit
        every { inputProcessor.queryDefinitions } returns mutableListOf()
        every { interGalacticUnitParser.parseIntergalacticUnits(any()) } returns Unit
        every { outputProcessor.saveForOutput(any()) } returns Unit
        every { outputProcessor.writeToFile("outputFileName") } returns Unit
        every { metalValueParser.reset() } returns Unit
        val exception = shouldThrow<NoQueryFoundException> {
            tradingService.trade("inputFileName", "outputFileName")
        }
        exception.message shouldBe("ERROR: No query found!")
    }

    test("Test Trade Service should pass") {
        val queryDefinitions = mutableListOf("how many Credits is glob prok Silver ?")
        val metalValueDefinitions = mutableListOf("glob prok Silver is 34 Credits")
        val interGalacticUnitDefinitions = mutableListOf("glob is I", "prok is V", "pish is X", "tegj is L")

        every { inputProcessor.processInputFromFile("inputFileName") } returns true
        every { inputProcessor.interGalacticUnitDefinitions } returns interGalacticUnitDefinitions
        every { inputProcessor.metalValueDefinitions} returns metalValueDefinitions
        every { metalValueParser.parseMetalValue(any()) } returns Unit
        every { metalValueParser.interGalacticUnitString } returns "glob prok"
        every { metalValueParser.metalName } returns "Silver"
        every { metalValueParser.value } returns 34
        every { metalAndMultipleCalculator.initializeMetalAndMultipler(any(), any(), any()) } returns Unit
        every { metalAndMultipleCalculator.metalNameMultiplerMap } returns mutableMapOf<String, Double>().apply {
            "Silver" to 17.0
        }
        every { interGalacticUnitParser.interGalacticUnits } returns mutableMapOf<String, String>().apply {
            "glob" to "I"
            "prok" to "V"
        }
        every { inputProcessor.queryDefinitions } returns queryDefinitions
        every { interGalacticUnitParser.parseIntergalacticUnits(any()) } returns Unit
        every { outputProcessor.saveForOutput(any()) } returns Unit
        every { outputProcessor.writeToFile("outputFileName") } returns Unit
        every { inputProcessor.invalidQuery } returns ""
        every { outputProcessor.contents } returns listOf("glob prok Silver is 34 Credits")
        every { queryParser.parseQuery(any()) } returns arrayOf<String>()
        every { interGalacticUnitParser.reset() } returns Unit
        every { inputProcessor.reset() } returns Unit
        every { outputProcessor.reset() } returns Unit
        every { metalValueParser.reset() } returns Unit
        val exception = shouldNotThrowAny {
            tradingService.trade("inputFileName", "outputFileName")
        }
        exception shouldBe Unit
    }
})
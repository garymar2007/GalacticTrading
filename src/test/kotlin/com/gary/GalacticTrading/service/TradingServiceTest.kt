//package com.gary.GalacticTrading.service
//
//import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator
//import com.gary.GalacticTrading.exception.FailedProcessInputFile
//import com.gary.GalacticTrading.exception.NoInterGalacticUnitDefinitionsFoundException
//import com.gary.GalacticTrading.exception.NoMetalValueDefinitionsFoundException
//import com.gary.GalacticTrading.exception.NoQueryFoundException
//import com.gary.GalacticTrading.io.InputProcessor
//import com.gary.GalacticTrading.io.OutputProcessor
//import com.gary.GalacticTrading.parser.InterGalacticUnitParser
//import com.gary.GalacticTrading.parser.MetalValueParser
//import com.gary.GalacticTrading.parser.QueryParser
//import io.kotest.assertions.throwables.shouldThrow
//import io.kotest.matchers.should
//import io.kotest.matchers.string.startWith
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.*
//import org.mockito.BDDMockito.given
//import org.mockito.BDDMockito.willDoNothing
//import org.springframework.boot.test.mock.mockito.MockBean
//import java.io.IOException
//
//internal class TradingServiceTest {
//    @InjectMocks
//    private val tradingService: TradingService? = null
//
//    @MockBean
//    private val inputProcessor: InputProcessor? = null
//
//    @MockBean
//    private val interGalacticUnitParser: InterGalacticUnitParser? = null
//
//    @MockBean
//    private val metalValueParser: MetalValueParser? = null
//
//    @MockBean
//    private val queryParser: QueryParser? = null
//
//    @MockBean
//    private val outputProcessor: OutputProcessor? = null
//
//    @MockBean
//    private val metalAndMultipleCalculator: MetalAndMultipleCalculator? = null
//
//    @Test
//    fun testTradeThrowFailedProcessInptFileException() {
//        given(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).willReturn(false)
//
//        val exception = shouldThrow<FailedProcessInputFile> {
//            tradingService?.trade("inputFileName", "outputFileName")
//        }
//        exception.message should startWith("ERROR:")
//    }
//
//    @Test
//    @Throws(IOException::class)
//    fun testTradeThrowNoInterGalacticUnitDefinitionsFoundException() {
//        given(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).willReturn(true)
//        given(inputProcessor?.interGalacticUnitDefinitions).willReturn(null)
//
//        val exception = shouldThrow<NoInterGalacticUnitDefinitionsFoundException> {
//            tradingService?.trade("inputFileName", "outputFileName")
//        }
//        exception.message should startWith("ERROR:")
//    }
//
//    @Test
//    fun testTradeThrowNoMetalValueDefinitionsFoundException() {
//        given(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).willReturn(true)
//        given(inputProcessor?.interGalacticUnitDefinitions).willReturn(mutableListOf("glob is I", "prok is V", "pish is X", "tegj is L"))
//        willDoNothing().given(interGalacticUnitParser)?.parseIntergalacticUnits(ArgumentMatchers.any())
//        given(inputProcessor?.metalValueDefinitions).willReturn(null)
//
//        val exception = shouldThrow<NoMetalValueDefinitionsFoundException> {
//            tradingService?.trade("inputFileName", "outputFileName")
//        }
//        exception.message should startWith("ERROR:")
//    }
//
//    @Test
//    fun testTradeThrowNoQueryFoundException() {
//        given(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).willReturn(true)
//        given(inputProcessor?.interGalacticUnitDefinitions).willReturn(
//            mutableListOf("glob is I", "prok is V", "pish is X", "tegj is L"))
//        willDoNothing().given(interGalacticUnitParser)?.parseIntergalacticUnits(ArgumentMatchers.any())
//        given(inputProcessor?.metalValueDefinitions).willReturn(mutableListOf("glob prok Silver is 34 Credits"))
//        willDoNothing().given(metalValueParser)?.parseMetalValue(ArgumentMatchers.any())
//        given(metalValueParser?.interGalacticUnitString).willReturn("glob prok")
//        Mockito.`when`(metalValueParser?.metalName).thenReturn("Silver")
//        Mockito.`when`(metalValueParser?.value).thenReturn(34)
//        Mockito.doNothing().`when`(metalAndMultipleCalculator)?.initializeMetalAndMultipler(
//            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
//
//        Mockito.`when`<List<String>>(inputProcessor?.queryDefinitions).thenReturn(null)
//        Assertions.assertThrows(
//            NoQueryFoundException::class.java
//        ) { tradingService!!.trade("inputFileName", "outputFileName") }
//    }
//
//    @Test
//    fun testTrade() {
//        val queryDefinitions = listOf("how many Credits is glob prok Silver ?")
//        val metalValueDefinitions = listOf("glob prok Silver is 34 Credits")
//        val interGalacticUnitDefinitions = listOf("glob is I", "prok is V", "pish is X", "tegj is L")
//
//        Mockito.`when`(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).thenReturn(true)
//        Mockito.`when`<List<String>>(inputProcessor?.interGalacticUnitDefinitions)
//            .thenReturn(interGalacticUnitDefinitions)
//        Mockito.`when`(inputProcessor?.processInputFromFile(ArgumentMatchers.any())).thenReturn(true)
//        Mockito.`when`<List<String>>(inputProcessor?.interGalacticUnitDefinitions)
//            .thenReturn(interGalacticUnitDefinitions)
//        Mockito.doNothing().`when`(interGalacticUnitParser)?.parseIntergalacticUnits(ArgumentMatchers.any())
//        Mockito.`when`<List<String>>(inputProcessor?.metalValueDefinitions).thenReturn(metalValueDefinitions)
//        Mockito.doNothing().`when`(metalValueParser)?.parseMetalValue(ArgumentMatchers.any())
//        Mockito.`when`(metalValueParser?.interGalacticUnitString).thenReturn("glob prok")
//        Mockito.`when`(metalValueParser?.metalName).thenReturn("Silver")
//        Mockito.`when`(metalValueParser?.value).thenReturn(34)
//        Mockito.doNothing().`when`(metalAndMultipleCalculator)?.initializeMetalAndMultipler(
//            ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
//        Mockito.`when`<List<String>>(inputProcessor?.queryDefinitions).thenReturn(queryDefinitions)
//        Mockito.`when`<Map<String, Double>>(metalAndMultipleCalculator!!.metalNameMultiplerMap)
//            .thenReturn(mutableMapOf<String, Double>().apply {
//                "Silver" to 17.0
//            })
//        Mockito.`when`(interGalacticUnitParser?.interGalacticUnits)
//            .thenReturn(mutableMapOf<String, String>().apply {
//                    "glob" to "I"
//                    "prok" to "V"
//            })
//        Mockito.doNothing().`when`(outputProcessor)?.saveForOutput(ArgumentMatchers.any())
//        Mockito.`when`(inputProcessor?.invalidQuery).thenReturn(null)
//        Mockito.doNothing().`when`(outputProcessor)?.writeToFile(ArgumentMatchers.any())
//        Mockito.`when`(outputProcessor?.contents).thenReturn(listOf("glob prok Silver is 34 Credits"))
//
//        tradingService?.trade("inputFileName", "outputFileName")
//    }
//}
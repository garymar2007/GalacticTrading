package com.gary.GalacticTrading.service

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator
import com.gary.GalacticTrading.exception.*
import com.gary.GalacticTrading.io.InputProcessor
import com.gary.GalacticTrading.io.OutputProcessor
import com.gary.GalacticTrading.parser.InterGalacticUnitParser
import com.gary.GalacticTrading.parser.MetalValueParser
import com.gary.GalacticTrading.parser.QueryParser
import com.gary.GalacticTrading.utils.QueryConstants
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.io.IOException

/**
 * This class is responsible for trading.
 */
@Service
class TradingService(private val inputProcessor: InputProcessor,
                     private val interGalacticUnitParser: InterGalacticUnitParser,
                     private val metalValueParser: MetalValueParser,
                     private val queryParser: QueryParser,
                     private val metalAndMultipleCalculator: MetalAndMultipleCalculator,
                     private val outputProcessor: OutputProcessor) {
    private val log = KotlinLogging.logger {}

    fun trade(inputFileName: String, outputFileName: String) {
        log.debug("Galactic Trading Process...")
        try {
            val isInputProcessedSuccessfully = inputProcessor.processInputFromFile(inputFileName)

            if (isInputProcessedSuccessfully) {
                val interGalacticUnitDefinitions = inputProcessor.interGalacticUnitDefinitions
                processInterGalacticUnitDefinitions(interGalacticUnitDefinitions)

                val metalValueDefinitions = inputProcessor.metalValueDefinitions
                processMetalValueDefinitions(metalValueDefinitions)

                val queryDefinitions = inputProcessor.queryDefinitions
                processQueryDefinitions(queryDefinitions)
                outputProcessor.saveForOutput(arrayOf(inputProcessor.invalidQuery))
                outputProcessor.writeToFile(outputFileName)
                cleanup()
            } else {
                throw FailedProcessInputFile(ExceptionMsgConstants.UNABLE_PROCESS_INPUT_FILE)
            }
        } catch (e: FailedProcessInputFile) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (e: NoInterGalacticUnitDefinitionsFoundException) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (e: InvalidIntergalacticUnitException) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (e: NoQueryFoundException) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (e: NoMetalValueDefinitionsFoundException) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (e: InvalidMetalValueDefinitionException) {
            log.error(e.message)
            handleExceptions(e, outputFileName)
            throw e
        } catch (ex: IOException) {
            log.error(ex.message)
            throw ex
        }
    }

    private fun handleExceptions(e: Exception, outputFileName: String) {
        if (e is FailedProcessInputFile) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.UNABLE_TO_PROCESS_INPUT_FILE))
            outputProcessor.writeToFile(outputFileName)
        }

        if (e is NoInterGalacticUnitDefinitionsFoundException) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.NO_INTERGALACTIC_UNIT_DEFN_FOUND))
            outputProcessor.writeToFile(outputFileName)
        }

        if (e is NoMetalValueDefinitionsFoundException) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.NO_METAL_VALUE_DEFN_FOUND))
            outputProcessor.writeToFile(outputFileName)
        }

        if (!outputProcessor.contents.isEmpty() && e is InvalidMetalValueDefinitionException) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.QUERY_WITH_INVALID_UNIT_OF_METAL))
            outputProcessor.writeToFile(outputFileName)
        }

        if (e is InvalidIntergalacticUnitException) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.QUERY_WITH_INVALID_UNIT))
            outputProcessor.writeToFile(outputFileName)
        }

        if (e is NoQueryFoundException) {
            outputProcessor.saveForOutput(arrayOf(QueryConstants.NO_QUERY_FOUND))
            outputProcessor.writeToFile(outputFileName)
        }
    }

    private fun cleanup() {
        interGalacticUnitParser.reset()
        metalValueParser.reset()
        inputProcessor.reset()
        outputProcessor.reset()
    }

    private fun processInterGalacticUnitDefinitions(interGalacticUnitDefinitions: List<String>?) {
        if (interGalacticUnitDefinitions == null || interGalacticUnitDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.INTERGALACTIC_DEFN_NOT_FOUND)
            throw NoInterGalacticUnitDefinitionsFoundException(ExceptionMsgConstants.INTERGALACTIC_DEFN_NOT_FOUND)
        }

        for (interGalacticUnitDefinition in interGalacticUnitDefinitions) {
            interGalacticUnitParser.parseIntergalacticUnits(interGalacticUnitDefinition)
        }
    }

    private fun processMetalValueDefinitions(metalValueDefinitions: List<String>?) {
        if (metalValueDefinitions == null || metalValueDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.METAL_VALUE_DEFN_NOT_FOUND)
            throw NoMetalValueDefinitionsFoundException(ExceptionMsgConstants.METAL_VALUE_DEFN_NOT_FOUND)
        }

        for (metalValueDefinition in metalValueDefinitions) {
            metalValueParser.parseMetalValue(metalValueDefinition)
            metalAndMultipleCalculator.initializeMetalAndMultipler(
                metalValueParser.interGalacticUnitString,
                metalValueParser.metalName, metalValueParser.value
            )
            metalValueParser.reset()
        }
    }

    private fun processQueryDefinitions(queryDefinitions: List<String>?) {
        if (queryDefinitions == null || queryDefinitions.isEmpty()) {
            log.error(ExceptionMsgConstants.NO_QUERY_FOUND)
            throw NoQueryFoundException(ExceptionMsgConstants.NO_QUERY_FOUND)
        }

        for (queryDefinition in queryDefinitions) {
            val unitsAndMetalQuery = queryParser.parseQuery(queryDefinition)
            outputProcessor.saveForOutput(unitsAndMetalQuery)
        }
    }
}

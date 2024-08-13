package com.gary.GalacticTrading

import com.gary.GalacticTrading.service.TradingService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.IOException

@SpringBootApplication
class GalacticTradingApplication : ApplicationRunner {
    @Autowired
    private val tradingService: TradingService? = null
    private val log = KotlinLogging.logger("GalacticTradingApplication")

    @Throws(Exception::class)
    override fun run(args: ApplicationArguments) {
        if (args.nonOptionArgs.size == 2) {
            val inputFileName = args.nonOptionArgs[0]
            val outputFileName = args.nonOptionArgs[1]
            tradingService!!.trade(inputFileName, outputFileName)

            System.exit(0)
        } else {
            log.error("Invalid number of arguments. Please provide input and output file names.")
        }
    }

    companion object {
        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication <GalacticTradingApplication>(*args)
        }
    }
}

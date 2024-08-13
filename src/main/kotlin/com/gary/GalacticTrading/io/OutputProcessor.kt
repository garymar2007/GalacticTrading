package com.gary.GalacticTrading.io

import com.gary.GalacticTrading.calculator.MetalAndMultipleCalculator
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

@Service
data class OutputProcessor(private val metalAndMultipleCalculator: MetalAndMultipleCalculator) {
    var contents: List<String> = ArrayList()
    private val log = KotlinLogging.logger {}

    fun saveForOutput(unitsAndMetalQuery: Array<String>) {
        log.debug("Saving to buffer and ready for output file...")

        if (unitsAndMetalQuery.size == 1) {
            contents += unitsAndMetalQuery[0]
            return
        }

        var temp = unitsAndMetalQuery.joinToString(" ")
        temp += " is "
        val length = unitsAndMetalQuery.size
        if (metalAndMultipleCalculator.metalNameMultiplerMap[unitsAndMetalQuery[length - 1]] != null) {
            val metalName = unitsAndMetalQuery[length - 1]
            val interGalacticUnits = unitsAndMetalQuery.joinToString(" ").replace(metalName, "")
            temp += metalAndMultipleCalculator.calculateMetalValue(interGalacticUnits, metalName).toString()
            temp += " Credits"
        } else {
            temp += metalAndMultipleCalculator.calculateMetalValue(
                unitsAndMetalQuery.joinToString(" "), ""
            ).toString()
        }
        contents += temp
    }

    fun writeToFile(outputFileName: String) {
        val resourcesDir = File("src/main/resources/")
        val file = File(resourcesDir.absolutePath + File.separator + outputFileName)
        if (file.createNewFile()) {
            log.info("File created: " + file.name)
        } else {
            log.info("File already exists.")
        }

        try {
            BufferedWriter(FileWriter(file)).use { writer ->
                for (str in contents) {
                    writer.write(str + System.lineSeparator())
                }
                log.info("Output written to file: {}", file.absolutePath)
                contents = ArrayList()
            }
        } catch (e: IOException) {
            log.error("Error: Unable to write to file output.txt")
            throw e
        }
    }

    fun reset() {
        contents = ArrayList()
    }
}

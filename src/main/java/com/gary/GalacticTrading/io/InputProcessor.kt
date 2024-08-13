package com.gary.GalacticTrading.io

import com.gary.GalacticTrading.utils.InputRegEx
import com.gary.GalacticTrading.utils.QueryConstants
import mu.KotlinLogging
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.*

@Service
data class InputProcessor(
    val interGalacticUnitDefinitions: MutableList<String>,
    val metalValueDefinitions: MutableList<String>,
    val queryDefinitions: MutableList<String>
) {
    lateinit var invalidQuery: String
    private val log = KotlinLogging.logger {}

    fun processInputFromFile(fileName: String): Boolean {
        log.info("Processing input from file...")

        try {
            val br = ClassPathResource(fileName).file.bufferedReader()
            br.use { bufferedReader ->
                var line: String
                while ((bufferedReader.readLine().also { line = it }) != null) {
                    log.debug("Processing line: {}", line)
                    if (line.matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex())) {
                        interGalacticUnitDefinitions.add(line)
                    } else if (line.matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex())) {
                        metalValueDefinitions.add(line)
                    } else if (line.matches(InputRegEx.QUERY.toRegex())) {
                        queryDefinitions.add(line)
                    } else {
                        invalidQuery = QueryConstants.INVALID_QUERY
                    }
                }
                return true
            }
        } catch (e: IOException) {
            log.error("Error: Unable to read file input.txt")
            throw e
        }
    }

    fun reset() {
        interGalacticUnitDefinitions.clear()
        metalValueDefinitions.clear()
        queryDefinitions.clear()
        invalidQuery = ""
    }
}

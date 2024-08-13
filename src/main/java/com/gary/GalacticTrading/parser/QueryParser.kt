package com.gary.GalacticTrading.parser

import com.gary.GalacticTrading.utils.InputRegEx
import com.gary.GalacticTrading.utils.QueryConstants
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.*

/**
 * QueryParser class is responsible for parsing queries.
 */
@Component
class QueryParser {
    private val log = KotlinLogging.logger {}

    fun parseQuery(query: String?): Array<String> {
        if (query == null || query.isEmpty() || !query.matches(InputRegEx.QUERY.toRegex())) {
            log.error(QueryConstants.INVALID_QUERY)
            return arrayOf(QueryConstants.INVALID_QUERY)
        }
        val metalValueQuery: Array<String> = query.split(" ".toRegex()).filter { it != "" }.toTypedArray()
        val indexOfIs = metalValueQuery.indexOf("is")
        val indexOfQuestionMark = metalValueQuery.indexOf("?")
        val unitMetalQuery = Arrays.copyOfRange(metalValueQuery, indexOfIs + 1, indexOfQuestionMark)

        log.debug("Parsed query: query -> {}", java.lang.String.join(" ", *unitMetalQuery))
        return unitMetalQuery
    }
}

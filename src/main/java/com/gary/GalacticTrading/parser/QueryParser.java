package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.utils.InputRegEx;
import com.gary.GalacticTrading.utils.QueryConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 * QueryParser class is responsible for parsing queries.
 */
@Component
@Slf4j
public class QueryParser {
    public String[] parseQuery(final String query) {
        if (query == null || query.isEmpty() || !query.matches(InputRegEx.QUERY)) {
            log.error(QueryConstants.INVALID_QUERY);
            return new String[] {QueryConstants.INVALID_QUERY};
        }
        String[] metalValueQuery = Arrays.stream(query.split(" ")).filter(s -> !s.equals("")).toArray(String[]::new);
        final int indexOfIs = Arrays.asList(metalValueQuery).indexOf("is");
        final int indexOfQuestionMark = Arrays.asList(metalValueQuery).indexOf("?");
        String[] unitMetalQuery = Arrays.copyOfRange(metalValueQuery, indexOfIs + 1, indexOfQuestionMark);

        log.debug("Parsed query: query -> {}", String.join(" ", unitMetalQuery));
        return unitMetalQuery;
    }
}

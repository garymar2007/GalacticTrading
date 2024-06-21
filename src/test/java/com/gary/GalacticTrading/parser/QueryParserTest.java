package com.gary.GalacticTrading.parser;

import com.gary.GalacticTrading.utils.QueryConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class QueryParserTest {

    private QueryParser queryParser;
    private Set<String> interGalacticUnits;
    private Set<String> metalValues;

    @BeforeEach
    void setUp() {
        queryParser = new QueryParser();
        interGalacticUnits = new HashSet<>();
        interGalacticUnits.add("glob");
        interGalacticUnits.add("prok");
        interGalacticUnits.add("pish");
        interGalacticUnits.add("tegj");

        metalValues = new HashSet<>();
        metalValues.add("Silver");
        metalValues.add("Gold");
        metalValues.add("Iron");
    }
    @Test
    void parseQueryReturnInvalidQueryDueToUnknowUnitsAndMetal() {
        final String[] query = queryParser.parseQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood?",
                metalValues, interGalacticUnits);
        assertEquals(QueryConstants.INVALID_QUERY, query[0]);
    }

    @Test
    void parseQueryReturnInvalidQueryDueToNoUnitsOrMetal() {
        final String query[] = queryParser.parseQuery("how much is ?",
                metalValues, interGalacticUnits);
        assertEquals(QueryConstants.INVALID_QUERY, query[0]);
    }

    @Test
    void parseQueryReturnInvalidQueryDueToNullQuery() {
        final String query[] = queryParser.parseQuery("",
                metalValues, interGalacticUnits);
        assertEquals(QueryConstants.INVALID_QUERY, query[0]);
    }

    @Test
    void parseQueryReturnInvalidQueryDueToNoQuestionMark() {
        final String[] query = queryParser.parseQuery("how much is pish tegj glob glob",
                metalValues, interGalacticUnits);
        assertEquals(QueryConstants.INVALID_QUERY, query[0]);
    }

    @Test
    void parseQueryReturnInvalidQueryDueToNoUnits() {
        final String[] query = queryParser.parseQuery("how many Credits is Silver ?",
                metalValues, interGalacticUnits);
        assertEquals(QueryConstants.INVALID_QUERY, query[0]);
    }

    @Test
    void parseQueryReturnValidQueryWithoutMetal() {
        final String[] query = queryParser.parseQuery("how much is pish tegj glob glob ?",
                metalValues, interGalacticUnits);
        assertEquals("pish tegj glob glob", String.join(" ", query));
    }

    @Test
    void parseQueryReturnValidQueryWithMetal() {
        final String query[] = queryParser.parseQuery("how much is glob prok Gold ?",
                metalValues, interGalacticUnits);
        assertEquals("glob prok Gold",String.join(" ", query));
    }

    @Test
    void parseQueryReturnValidQueryWithMetalAndSpaces() {
        final String[] query = queryParser.parseQuery("how  much   is glob   prok   Gold ?",
                metalValues, interGalacticUnits);
        assertEquals("glob prok Gold", String.join(" ", query));
    }
}
package com.gary.GalacticTrading.parser

import com.gary.GalacticTrading.utils.QueryConstants
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class QueryParserTest {
    private var queryParser: QueryParser? = null
    private var interGalacticUnits: MutableSet<String>? = null
    private var metalValues: MutableSet<String>? = null

    @BeforeEach
    fun setUp() {
        queryParser = QueryParser()
        interGalacticUnits = hashSetOf("glob", "prok", "pish", "tegj")
        metalValues = hashSetOf("Silver", "Gold", "Iron")
    }

    @Test
    fun parseQueryReturnInvalidQueryDueToUnknowUnitsAndMetal() {
        val query = queryParser?.parseQuery("how much wood could a woodchuck chuck if a woodchuck could chuck wood?")
        Assertions.assertEquals(QueryConstants.INVALID_QUERY, query?.get(0) ?: "")
    }

    @Test
    fun parseQueryReturnInvalidQueryDueToNoUnitsOrMetal() {
        val query = queryParser?.parseQuery("how much is ?")
        Assertions.assertEquals(QueryConstants.INVALID_QUERY, query?.get(0) ?: "")
    }

    @Test
    fun parseQueryReturnInvalidQueryDueToNullQuery() {
        val query = queryParser?.parseQuery("")
        Assertions.assertEquals(QueryConstants.INVALID_QUERY, query?.get(0) ?: "")
    }

    @Test
    fun parseQueryReturnInvalidQueryDueToNoQuestionMark() {
        val query = queryParser?.parseQuery("how much is pish tegj glob glob")
        Assertions.assertEquals(QueryConstants.INVALID_QUERY, query?.get(0) ?: "")
    }

    @Test
    fun parseQueryReturnInvalidQueryDueToNoUnits() {
        val query = queryParser?.parseQuery("how many Credits is Silver ?")
        Assertions.assertEquals(QueryConstants.INVALID_QUERY, query?.get(0) ?: "")
    }

    @Test
    fun parseQueryReturnValidQueryWithoutMetal() {
        val query = queryParser?.parseQuery("how much is pish tegj glob glob ?")
        Assertions.assertEquals("pish tegj glob glob", query?.joinToString(" "))
    }

    @Test
    fun parseQueryReturnValidQueryWithMetal() {
        val query = queryParser?.parseQuery("how much is glob prok Gold ?")
        Assertions.assertEquals("glob prok Gold", query?.joinToString(" "))
    }

    @Test
    fun parseQueryReturnValidQueryWithMetalAndSpaces() {
        val query = queryParser?.parseQuery("how  much   is glob   prok   Gold ?")
        Assertions.assertEquals("glob prok Gold", query?.joinToString(" "))
    }
}
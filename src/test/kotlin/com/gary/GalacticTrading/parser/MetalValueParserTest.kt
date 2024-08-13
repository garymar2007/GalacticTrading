package com.gary.GalacticTrading.parser

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MetalValueParserTest {
    private val metalValueParser = MetalValueParser()

    @Test
    fun parseMetalValue() {
        metalValueParser.parseMetalValue("glob glob Silver is 34 Credits")
        Assertions.assertEquals("Silver", metalValueParser.metalName)
        Assertions.assertEquals(34, metalValueParser.value)
        Assertions.assertEquals("glob glob", metalValueParser.interGalacticUnitString)
        metalValueParser.reset()

        metalValueParser.parseMetalValue("glob prok Gold is 57800 Credits")
        Assertions.assertEquals("Gold", metalValueParser.metalName)
        Assertions.assertEquals(57800, metalValueParser.value)
        Assertions.assertEquals("glob prok", metalValueParser.interGalacticUnitString)
        metalValueParser.reset()

        metalValueParser.parseMetalValue("pish pish Iron is 3910 Credits")
        Assertions.assertEquals("Iron", metalValueParser.metalName)
        Assertions.assertEquals(3910, metalValueParser.value)
        Assertions.assertEquals("pish pish", metalValueParser.interGalacticUnitString)
        metalValueParser.reset()
    }
}
//package com.gary.GalacticTrading.parser
//
//import com.gary.GalacticTrading.converter.IntergalacticUnitsToRomanStringConverter
//import com.gary.GalacticTrading.exception.InvalidIntergalacticUnitException
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//
//internal class InterGalacticUnitParserTest {
//    private var intergalacticUnitParser: InterGalacticUnitParser? = null
//    private var intergalacticUnitsToRomanStringConverter: IntergalacticUnitsToRomanStringConverter? = null
//
//    @BeforeEach
//    fun setUp() {
//        intergalacticUnitsToRomanStringConverter = IntergalacticUnitsToRomanStringConverter()
//        intergalacticUnitParser = InterGalacticUnitParser(intergalacticUnitsToRomanStringConverter!!)
//    }
//
//    @Test
//    fun parseIntergalacticUnits() {
//        intergalacticUnitParser!!.parseIntergalacticUnits("glob is I")
//        intergalacticUnitParser!!.parseIntergalacticUnits("prok is V")
//        intergalacticUnitParser!!.parseIntergalacticUnits("pish is X")
//        intergalacticUnitParser!!.parseIntergalacticUnits("tegj is L")
//
//        assertEquals(4, intergalacticUnitParser.getInterGalacticUnits().size())
//    }
//
//    @get:Test
//    val interGalacticUnits: Unit
//        get() {
//            assertEquals(0, intergalacticUnitParser.getInterGalacticUnits().size())
//            intergalacticUnitParser!!.parseIntergalacticUnits("glob is I")
//            val interGalacticUnits: Map<String, String> =
//                intergalacticUnitParser.getInterGalacticUnits()
//            assertEquals(1, intergalacticUnitParser.getInterGalacticUnits().size())
//            Assertions.assertEquals("I", interGalacticUnits["glob"])
//        }
//
//    @get:Test
//    val invalidInterGalacticUnits: Unit
//        get() {
//            Assertions.assertThrows(
//                InvalidIntergalacticUnitException::class.java
//            ) {
//                intergalacticUnitParser!!.parseIntergalacticUnits("glob is I is I")
//            }
//        }
//}
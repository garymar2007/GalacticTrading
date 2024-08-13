package com.gary.GalacticTrading.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class InputRegExTest {
    @Test
    fun `test that inter galatic unit definition should be fine`() {
        Assertions.assertTrue("glob is I".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("prok is V".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("pish is X".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("tegj is L".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("glob is I ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("glob  is  I  ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertTrue("   glob is I   ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertFalse("glob glob is I    ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
        Assertions.assertFalse("glob is I  I   ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION.toRegex()))
    }

    @Test
    fun `test that metal value definition should be fine`() {
        Assertions.assertTrue("glob glob Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("prok Gold is 57800 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("pish pish Iron is 3910 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("tegj Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("glob glob Silver is 34 Credits ".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("glob  glob  Silver  is 34 Credits  ".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertTrue("   glob glob Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertFalse("glob glob Silver is 34 Credits Credits".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
        Assertions.assertFalse("glob glob Silver is 34".matches(InputRegEx.METAL_VALUE_DEFINITION.toRegex()))
    }

    @Test
    fun `test that query should be valid`() {
        Assertions.assertTrue("how much is pish tegj glob glob?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("How many Credits is glob prok Silver?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("how much is pish tegj glob glob ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("How much is pish tegj glob glob  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how  much  is pish  tegj glob  glob?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how   many  Credits is glob prok Silver?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how much is pish tegj glob glob ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how much is pish tegj glob glob  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue(" how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how much is pish tegj glob glob?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how many Credits is glob prok Silver?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how much is pish tegj glob glob ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how much is pish tegj glob glob  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertTrue("   how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY.toRegex()))
        Assertions.assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY.toRegex()))
    }
}
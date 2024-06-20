package com.gary.GalacticTrading.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputRegExTest {

    @Test
    void testIntergalaticUnitDefinition() {
        assertTrue("glob is I".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("prok is V".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("pish is X".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("tegj is L".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("glob is I ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("glob  is  I  ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertTrue("   glob is I   ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertFalse("glob glob is I    ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
        assertFalse("glob is I  I   ".matches(InputRegEx.INTERGALACTIC_UNIT_DEFINITION));
    }

    @Test
    void testMetalValueDefinition() {
        assertTrue("glob glob Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("prok Gold is 57800 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("pish pish Iron is 3910 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("tegj Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("glob glob Silver is 34 Credits ".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("glob  glob  Silver  is 34 Credits  ".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertTrue("   glob glob Silver is 34 Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertFalse("glob glob Silver is 34 Credits Credits".matches(InputRegEx.METAL_VALUE_DEFINITION));
        assertFalse("glob glob Silver is 34".matches(InputRegEx.METAL_VALUE_DEFINITION));
    }

    @Test
    void testQuery() {
        assertTrue("how much is pish tegj glob glob?".matches(InputRegEx.QUERY));
        assertTrue("How many Credits is glob prok Silver?".matches(InputRegEx.QUERY));
        assertTrue("how much is pish tegj glob glob ?".matches(InputRegEx.QUERY));
        assertTrue("how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY));
        assertTrue("How much is pish tegj glob glob  ?".matches(InputRegEx.QUERY));
        assertTrue("how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY));
        assertTrue(" how  much  is pish  tegj glob  glob?".matches(InputRegEx.QUERY));
        assertTrue(" how   many  Credits is glob prok Silver?".matches(InputRegEx.QUERY));
        assertTrue(" how much is pish tegj glob glob ?".matches(InputRegEx.QUERY));
        assertTrue(" how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY));
        assertTrue(" how much is pish tegj glob glob  ?".matches(InputRegEx.QUERY));
        assertTrue(" how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY));
        assertTrue("   how much is pish tegj glob glob?".matches(InputRegEx.QUERY));
        assertTrue("   how many Credits is glob prok Silver?".matches(InputRegEx.QUERY));
        assertTrue("   how much is pish tegj glob glob ?".matches(InputRegEx.QUERY));
        assertTrue("   how many Credits is glob prok Silver ?".matches(InputRegEx.QUERY));
        assertTrue("   how much is pish tegj glob glob  ?".matches(InputRegEx.QUERY));
        assertTrue("   how many Credits is glob prok Silver  ?".matches(InputRegEx.QUERY));
        assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY));
        assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY));
        assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY));
        assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY));
        assertFalse("how much is pish tegj glob glob".matches(InputRegEx.QUERY));
        assertFalse("how many Credits is glob prok Silver".matches(InputRegEx.QUERY));
    }

}
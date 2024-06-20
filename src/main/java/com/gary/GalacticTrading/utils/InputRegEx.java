package com.gary.GalacticTrading.utils;

public class InputRegEx {
    public static final String INTERGALACTIC_UNIT_DEFINITION = "^(\\s*\\w+\\s*) is (\\s*\\w+\\s*)$";
    public static final String METAL_VALUE_DEFINITION = "^(\\s*\\w+\\s?)+ is (\\s*\\d+\\s*) (C|c)redits\\s*$";
    public static final String QUERY = "^(\\s*(H|h)ow\\s* much\\s* is (\\s*\\w+\\s*)+\\?\\s*)|(\\s*(H|h)ow\\s* many\\s* (C|c)redits\\s* is (\\s*\\w+\\s*)+ (\\s*\\w+\\s*)+\\?\\s*)$";
}

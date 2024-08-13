package com.gary.GalacticTrading.utils

object QueryConstants {
    const val INVALID_QUERY: String = "I have no idea what you are talking about"
    const val QUERY_WITH_INVALID_UNIT_OF_METAL: String = "Unknown units of metal in query"
    const val UNABLE_TO_PROCESS_INPUT_FILE: String = "Unable to process input file"
    const val NO_INTERGALACTIC_UNIT_DEFN_FOUND: String = "No intergalactic unit definitions found in query"
    const val QUERY_WITH_INVALID_UNIT: String = "Unknown unit in query"
    const val NO_QUERY_FOUND: String = "No query found"
    const val NO_METAL_VALUE_DEFN_FOUND: String = "No metal value definitions found in query"
}

object InputRegEx {
    const val INTERGALACTIC_UNIT_DEFINITION: String = "^(\\s*\\w+\\s*) is (\\s*\\w+\\s*)$"
    const val METAL_VALUE_DEFINITION: String = "^(\\s*\\w+\\s?)+ is (\\s*\\d+\\s*) (C|c)redits\\s*$"
    const val QUERY: String =
        "^(\\s*(H|h)ow\\s* much\\s* is (\\s*\\w+\\s*)+\\?\\s*)|(\\s*(H|h)ow\\s* many\\s* (C|c)redits\\s* is (\\s*\\w+\\s*)+ (\\s*\\w+\\s*)+\\?\\s*)$"
}
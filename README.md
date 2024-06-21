# Merchant's Guide to the Galaxy Project

## Problem statement
You decided to give up on earth after the latest financial collapse left 99.99% of the earth's
population with 0.01% of the wealth. Luckily, with the scant sum of money that is left in
your account, you are able to afford to rent a spaceship, leave earth, and fly all over the
galaxy to sell common metals and dirt (which apparently is worth a lot).

Galactic trading requires you to convert numbers and units, and you decided to write a
program to help you. The numbers used for intergalactic transactions follows a similar
convention to the roman numerals and you have painstakingly collected the appropriate
translation between them. Roman numerals are based on seven symbols.

### Roman numerals
| Symbol | Value |

    I=1
    V=5
    X = 10
    L = 250
    C = 100
    D = 500
    M = 1000
Numbers are formed by combining symbols together and adding the values. For example,
MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are placed in order of value,
starting with the largest values. When smaller values precede larger values, the smaller
values are subtracted from the larger values, and the result is added to the total. For
example MCMXLIV = 1000 + (1000 -100) + (50 -10) + (5 -1) = 1944.

The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
(They may appear four times if the third and fourth are separated by a smaller value, such
as XXXIX.)

"D", "L", and "V" can never be repeated.
"I" can be subtracted from "V" and "X" only. "X" can be subtracted from "L" and "C" only.

"C" can be subtracted from "D" and "M" only. "V", "L", and "D" can never be subtracted.

Only one small-value symbol may be subtracted from any large-value symbol.

A number written in Arabic numerals can be broken into digits. For example, 1903 is
composed of 1, 9, 0, and 3. To write the Roman numeral, each of the non-zero digits should
be treated separately.

In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 1903 = MCMIII.
Source: Wikipedia http://en.wikipedia.org/wiki/Roman_numerals


Input to your program consists of lines of text detailing your notes on the conversion
between intergalactic units and roman numerals. You are expected to handle invalid
queries appropriately.

### Test input
    glob is I
    prok is V
    pish is X
    tegj is L
    glob glob Silver is 34 Credits
    glob prok Gold is 57800 Credits
    pish pish Iron is 3910 Credits
    how much is pish tegj glob glob ?
    how many Credits is glob prok Silver ?
    how many Credits is glob prok Gold ?
    how many Credits is glob prok Iron ?
    how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

### Test output
    pish tegj glob glob is 42
    glob prok Silver is 68 Credits
    glob prok Gold is 57800 Credits
    glob prok Iron is 782 Credits
    I have no idea what you are talking about

## Solution Design
The problem can be broken down into 3 parts:
1. Parse the input and store the conversion rates in a dictionary.
2. Parse the input and store the conversion rates for the metals in a dictionary.
3. Parse the input and convert the intergalactic units to roman numerals and then to decimal numbers.

### Assumptions
1. The inputs are following the order: 
    - Conversion rates for intergalactic units
    - Conversion rates for metals
    - Queries
2. The queries are in the format:
    - how much is pish tegj glob glob ?
    - how many Credits is glob prok Silver ?
    - how many Credits is glob prok Gold ?
    - how many Credits is glob prok Iron ?
3. The queries are case sensitive.
4. The queries are ending with a question mark.
5. The conversion rates are case sensitive.
6. The conversion rates are in the format:
    - glob is I
    - prok is V
    - pish is X
    - tegj is L
    - glob glob Silver is 34 Credits
    - glob prok Gold is 57800 Credits
    - pish pish Iron is 3910 Credits
7. The output for the Credits will be an integer value, not decimal.

### Design
1. Parser: 
   - a map of <String, String> to store the conversion rates;
   - a map of <String, Integer> to store the metal multiplers;
   - a list of queries;
2. Converter:
   - Romman string to integer converter;
   - Intergalactic units to Roman string converter;
3. Calculator:
   - Calculate the value of the intergalactic units;
   - Calculate the value of the metal;
   - Calculate the value of the query;
4. Validator:
   - Validate the input;
   - Validate the queries;
   - Validate the format of converted roman string;
5. Exception Handler:
   - Handle the exceptions from input parser;
   - Handle the exceptions from query parser;
   - Handle the exceptions from calculator;
   - Handle the exceptions from I/O;

### Test
1. Unit Test: for most of the classes and methods;
2. Integration Test: for the whole program that read input file and generate output file;

## How to test the program
1. Clone the repository;

2. Maven build:
    ```
    mvn clean install
    ```
3. In order to run the program easily, you should use integration test to run against your input file which resides in resource folder.

The integration test will read the input file as input test and generate a output file for the outcome.
The IT class is GalaxyMerchantIT.java, which is located in src/test/java/com/galaxy/merchant/it folder.  
It has three simple tests which read three different files:
```
- input.txt - the valid input file for the test;
  - The output file will be generated as output.txt;
- input2.txt - another valid input file for the test with different queries;
  - The output file will be generated as output2.txt;
- invalid-input.txt - an invalid input file for the test with invalid intergalactic units;
  - The output file will be generated as output3.txt, as well as an exception will be thrown.;
```


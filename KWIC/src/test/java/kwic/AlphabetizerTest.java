package kwic;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This is the Unit test for the Alphabetizer.
 * It is used to test the alphabetic sort component of this project.
 * Class has one field data, which contains the lines to be shifted.
 * Class has three methods, shift(), output() and getData().
 * This test generally uses static data. reading input files  is done in the KWIC Class(main class)
 */

class AlphabetizerTest {

    // field data, which contains the lines to be shifted.
    private static List<String> data = new ArrayList<>();


    /**
     * method to test the alphabetizer.
     * this test compares the list of string in the mock data to the list of string, returned from the circular shifter.
     *
     */
    @ParameterizedTest
    @MethodSource("getData")
    void alphabetSort() {
        Alphabetizer alphabetizer = new Alphabetizer();
        alphabetizer.setLines(data);
        alphabetizer.alphabetSort();
        assertEquals(output(),alphabetizer.getSortedLines());
    }

    //This method, only returns the lines to be sorted
    static List<String> getData(){
        data.add("Hello how are you");
        data.add("Hello how are you");
        data.add("My name is Joe");
        data.add("Hello how is the boy you");
        data.add("Hard work eventually pays off");
        data.add("JUnit is a unit testing framework for the Java programming language");
        data.add("JUnit is a unit testing framework for only Java programming language");
        data.add("JUnit Jupiter is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5");
        data.add("JUnit Vintage provides a test engine for running JUnit 3 and JUnit 4 based tests on the platform");
        data.add("A girl is no one");
        return data;
    }


    //This is a mock data of the expected alphabetic sort
    static List<String> output(){
        List<String> output = new ArrayList<>();
        output.add("A girl is no one");
        output.add("Hard work eventually pays off");
        output.add("Hello how are you");
        output.add("Hello how are you");
        output.add("Hello how is the boy you");
        output.add("JUnit is a unit testing framework for only Java programming language");
        output.add("JUnit is a unit testing framework for the Java programming language");
        output.add("JUnit Jupiter is the combination of the new programming model and extension model for writing tests and extensions in JUnit 5");
        output.add("JUnit Vintage provides a test engine for running JUnit 3 and JUnit 4 based tests on the platform");
        output.add("My name is Joe");

        return output;
    }

}
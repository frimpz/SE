package kwic;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Unit test for the circular shifter.
 * This class is used to test the circular shift component of this project.
 * Class has one field data, which contains the lines to be shifted.
 * Class has three methods, shift(), output() and getData().
 * This test generally uses static data. reading input files  is done in the KWIC Class(main class)
 */

class CircularShifterTest {

    // field data, which contains the lines to be shifted.
    private static List<String[]> data = new ArrayList<>();

    /**
     * method to test the component.
     * this test compares the list of string in the mock data to the list of string, returned from the circular shifter.
     *
     */
    @ParameterizedTest
    @MethodSource("getData")
    void Shift() {
        CircularShifter circularShifter = new CircularShifter();
        circularShifter.shift(data);
        assertEquals(output(),circularShifter.getShifted());
    }


    //This method, only returns the lines to be shifted(Input)
    static List<String[]> getData(){
        data.add(new String[]{"Hello", "World","hi","There","Hello","again"});
        data.add(new String[]{"I", "am","going","to","circular","shift","you"});
         return data;
    }

    //This is a mock data of the expected shift(output)
    static List<String> output(){
        List<String> output = new ArrayList<>();
        output.add("Hello World hi There Hello again");
        output.add("World hi There Hello again Hello");
        output.add("hi There Hello again Hello World");
        output.add("There Hello again Hello World hi");
        output.add("Hello again Hello World hi There");
        output.add("again Hello World hi There Hello");
        output.add("I am going to circular shift you");
        output.add("am going to circular shift you I");
        output.add("going to circular shift you I am");
        output.add("to circular shift you I am going");
        output.add("circular shift you I am going to");
        output.add("shift you I am going to circular");
        output.add("you I am going to circular shift");
        return output;
    }


}
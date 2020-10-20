package kwic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class KWICTest {



    CircularShifter shifter;
    Alphabetizer alphabetizer;
    Output output;

    @BeforeEach
    void setUp() {
         shifter = new CircularShifter();
         alphabetizer = new Alphabetizer();
         output = new Output();
    }



    /**
     * method to test the all components.
     * Compares the output generated to an expected output.
     * Input is entered statically
     */
    @Test
    void KwicTest() {
        LineStorage lines = new LineStorage();
        lines.addLinesFluent(new String[]{"GOPAS", "Architektury", "Soft." ,"Systémů"})
                .addLinesFluent(new String[]{"Key", "Word", "in", "Context"});
        if(lines.isSet())
        {
            shifter.setup(lines);
            alphabetizer.execute(shifter);
            output.print(alphabetizer);
            assertEquals(output(),alphabetizer.getSortedLines());
        }
        else{
            System.out.println("No lines read, Exiting");

        }
    }


    //Expected output of this test
    static List<String> output(){
        List<String> output = new ArrayList<>();
        output.add("Architektury Soft. Systémů GOPAS");
        output.add("Context Key Word in");
        output.add("GOPAS Architektury Soft. Systémů");
        output.add("in Context Key Word");
        output.add("Key Word in Context");
        output.add("Soft. Systémů GOPAS Architektury");
        output.add("Systémů GOPAS Architektury Soft.");
        output.add("Word in Context Key");
        return output;
    }





}
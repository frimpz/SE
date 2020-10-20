package kwic;

import java.util.Scanner;

/**
*@ Author Frimpong Boadu
* KWIC Resubmission
* Software Engineering Assignment
*
*/

/**
 * This class follows the pattern in the slide,
 * it creates a line storage, and reads the input into it,
 * passes the line storage to the circular shifter.
 * Then  it Sends the circular shifter object to the alphabetizer.
 * Outpu also takes alphabetizer and displays the results.
 */

public class KWIC {

    public static void main(String[] args){
        KWIC kwic = new KWIC();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Input file name");
        System.out.flush();
        String filename = scanner.nextLine();
        filename = "./"+filename;
        kwic.execute(filename);
    }


    public void execute(String file){
        LineStorage lines = new LineStorage();
        Input input = new Input();
        CircularShifter shifter = new CircularShifter();
        Alphabetizer alphabetizer = new Alphabetizer();
        Output output = new Output();


        input.parse(file,lines);
        if(lines.isSet())
        {
        shifter.setup(lines);
        alphabetizer.execute(shifter);
        output.print(alphabetizer);
        }
        else{
            System.out.println("No lines read, Exiting");

        }


    }
}

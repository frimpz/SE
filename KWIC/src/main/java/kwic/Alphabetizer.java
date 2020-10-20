package kwic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Alphabetizer class takes the line storage, shifted array and also creates a new array of correct position
 * The alphabetizer is then sent to the output
 */


public class Alphabetizer {

    //The lines received from the circular shifter
    private List<String> lines;

    //This contains the sorted lines.
    private List<String> sortedLines;


    /**
     * I am using two constructors so that I can test the alphabetizer, without the circular shifer
     * In the pattern from the book, the circular shifter object is passed to the alphabetizer.
     * I included a default constructor and a setter method, so that I can test this component separately.
     */
    public Alphabetizer() {

    }

    public void execute(CircularShifter circularShifter) {
        this.lines = circularShifter.getShifted();
        alphabetSort();
    }

    /**
     * This method sorts the lines passed from the circular shifter object.
     * I creates a new instance List because using the original list gives a concurrent modification exception.
     * The sorted lines are then put into the sortedLines field.
     */
    public void  alphabetSort(){
        List<String> lines = new ArrayList<>(this.lines);
        Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
        sortedLines = lines;
    }


    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    /**
     * getter method for sorted lines.
     * @return list of sorted string
     */
    public List<String> getSortedLines() {
        return sortedLines;
    }

    //Setter method for sorted lines.
    public void setSortedLines(List<String> sortedLines) {
        this.sortedLines = sortedLines;
    }
}

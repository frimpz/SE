package kwic;

import java.util.ArrayList;
import java.util.List;

/**
 * To follow the oop pattern for KWICC implementation, the line storage serves as a class to hold the input lines read.
 * The class has 3 fields
 */

public class LineStorage {

    // this is the array of split lines.
    private List<String[]> lines = new ArrayList<>();

    //this field is set to true when lines are read.
    private boolean isSet = false;


    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }

    //constructor for the class
    public LineStorage() {
    }


    /**
     * This is the getter method of the number of lines
     * @return array of String(the split line)
     */
    public List<String[]> getLines() {
        return lines;
    }

    /**
     * Setter method for set lines
     * @param lines
     */
    public void setLines(List<String[]> lines) {
        this.lines = lines;
    }

    /**
     * this method is used to add new lines read, to the line storage
     * @param line (input String array of split lines)
     */

    public void addLines(String[] line){
        this.lines.add(line);
    }

    /**
     * this method is used to add new lines read, to the line storage
     * same as method above, but this one uses fluent design pattern
     * @param line (input String array of split lines)
     *
     */
    public LineStorage addLinesFluent(String[] line) {
        this.lines.add(line);
        if(line.length > 0 ) {
            this.isSet = true;
        }
        return this;
    }


}

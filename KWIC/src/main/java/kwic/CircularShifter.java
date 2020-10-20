package kwic;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
* In this class, original lines are stored in array which is in the linestorage object passed
* The circular shifts is created from the arrays that store each line.
* setup method, is used to initialise the circular shifter
*/

public class CircularShifter {


    private List<String[]> lines;
    private List<String> shifted = new ArrayList<>();

    public void setup(LineStorage lines){
        this.lines = lines.getLines();
        shift(this.lines);
    }


    /**
     * This method iterates over the list of string arrays, calls the String builder method
     * It passes the current array, as well as the starting position for the builder to build the string.
     * The string builder then returns a string
     * @param lines
     */
    public void shift(List<String[]> lines) {
        String tempString = "";
        for (String[] line : lines){
            shifted.add(stringBuilder(line,0));
            for(int i=1;i<line.length;i++){
                tempString = stringBuilder(line,i);
                shifted.add(tempString);
            }
        }

    }

    /**
     * getter for the line storage object.
     * @return List of String arrays, which are the split lines.
     */
    public List<String[]> getLines() {
        return lines;
    }

    /**
     * setter for the line storage object.
     * @param lines List of String arrays, which are the split lines.
     */
    public void setLines(List<String[]> lines) {
        this.lines = lines;
    }

    /**
     * getter for the circular shifted object.
     * @return
     */
    public List<String> getShifted() {
        return shifted;
    }

    /**
     * setter for the circular shifted object.
     * @param shifted
     */
    public void setShifted(List<String> shifted) {
        this.shifted = shifted;
    }


    /**
     * This method is used to convert each array into a shifted string.
     * @param strArray the array which the string should be constructed from.
     * @param start the position which the string concatenation should begin from
     * @return the string constructed.
     */
    public static String stringBuilder(String[] strArray,int start) {
        StringJoiner stringJoiner = new StringJoiner (" ");

        for (int i = start; i < strArray.length; i++) {
            stringJoiner.add(strArray[i]);
        }

        for (int i = 0; i < start; i++) {
            stringJoiner.add(strArray[i]);
        }
        return stringJoiner.toString();
    }
}

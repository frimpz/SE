package kwic;


import java.util.logging.Level;
import java.util.logging.Logger;

    /**
    *   This is the Output Class which receives alphabetizer and prints output.
    *   It iterates over the sorted list in the alphabetizer passed and displays the output.
    */

public class Output {

    private static final Logger logger = Logger.getLogger(Input.class.getName());

    public void print(Alphabetizer alphabetizer) {

        try{
            if(!alphabetizer.getSortedLines().isEmpty()) {
                for (String lines : alphabetizer.getSortedLines()) {
                    System.out.println(lines);
                }
            }else{
                System.out.println("No lines to print");
            }

        }catch(NullPointerException e){
            logger.log(Level.WARNING,"Cannot find the specified file",e);
        }


    }


}

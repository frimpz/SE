package kwic;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

/**
 * This is the input class, this class reads the file line by line.
 * The class has one method (parse) which reads the inputs one line at a time and splits into words.
 * The split words are put into an array and sent to the LineStorage.
 */
public class Input {

    private static final Logger logger = Logger.getLogger(Input.class.getName());

/**
 * Method to parse the input file.
 * This method reads the input lines and splits into words so it can rotate.
 * Also removed punctuations, So this is sorted only by the letters.
 */
        public void parse(String file, LineStorage line){
            BufferedReader bufferedReader ;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String ln;
                String[] arr;
                while ((ln = bufferedReader.readLine()) != null) {
                    arr = ln.replaceAll("[^a-zA-Z ]", "").split("\\s+");
                    line.addLines(arr);
                    line.setSet(true);
                }
                bufferedReader.close();

            } catch (FileNotFoundException e) {
                logger.log(Level.WARNING,"Cannot find the specified file",e);
            } catch (IOException e) {
                logger.log(Level.SEVERE,e.toString(),e);
            }


        }



}

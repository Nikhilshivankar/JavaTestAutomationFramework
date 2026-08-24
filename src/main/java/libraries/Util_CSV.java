package libraries;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class Util_CSV {

    public static ArrayList<String[]> readCSV(String filePath) throws IOException {
        ArrayList<String[]> arr = new ArrayList<>();
        
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                arr.add(nextLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            System.err.println("Validation exception occurred in CSV parsing: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("Read CSV Contents: " + arr);
        return arr;
    }
}

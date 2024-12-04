import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportData {
    public static double[][] importData(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Read the first line and get the number of workers
            line = br.readLine();
            int workers = Integer.parseInt(line.split(" ")[1]);

            // Create a 2D array to store the data [worker][ratings]
            double[][] data = new double[workers][workers];

            // List to store all numbers temporarily
            double[] numbers = new double[workers * workers];
            int numberIndex = 0;

            // Read all numbers from the file
            while ((line = br.readLine()) != null) {
                String[] currentLine = line.split(" ");
                for (String number : currentLine) {
                    if (!number.trim().isEmpty()) {
                        numbers[numberIndex++] = Double.parseDouble(number);
                    }
                }
            }

            // Transfer numbers to 2D array
            for (int worker = 0; worker < workers; worker++) {
                for (int rating = 0; rating < workers; rating++) {
                    data[worker][rating] = numbers[worker * worker + rating];
                }
            }

            return data;
        }
    }
}

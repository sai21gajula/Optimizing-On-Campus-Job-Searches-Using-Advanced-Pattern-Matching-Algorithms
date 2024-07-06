import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NaiveAlgo {

    public static void naiveSearch(List<String> lines, String P) {
        boolean found = false;
        int compCount = 0;

        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String T = lines.get(lineIndex);
            int nTxtLen = T.length();
            int mPatLen = P.length();

            for (int i = 0; i <= (nTxtLen - mPatLen); i++) {
                int j;
                for (j = 0; j < mPatLen; j++) {
                    compCount++;
                    if (T.charAt(i + j) != P.charAt(j)) {
                        break;
                    }
                }
                if (j == mPatLen) { // Match found
                    found = true;
                    System.out.println("Job found at line " + (lineIndex + 1) + ": " + T);
                }
            }
        }

        System.out.println("Number of comparisons: " + compCount);
        if (!found) {
            System.out.println("Job not found!");
        }
    }

    public static List<String> readAndStoreLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path containing the job description:");
        String filePath = sc.next();
        
        try {
            List<String> lines = readAndStoreLines(filePath);
            System.out.println("Enter the job search keyword:");
            String P = sc.next();

            naiveSearch(lines, P);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

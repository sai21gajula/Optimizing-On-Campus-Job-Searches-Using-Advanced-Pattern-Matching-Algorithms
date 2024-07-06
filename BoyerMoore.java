import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoyerMoore {
    public static int SIZE = 500;
    public static int[] table = new int[SIZE];

    public void tableShift(String pattern) {
        int m = pattern.length();

        for (int i = 0; i < SIZE; i++) {
            table[i] = m;
        }

        for (int j = 0; j < m - 1; j++) {
            table[pattern.charAt(j)] = m - 1 - j;
        }
    }
    
    public void horspoolMatch(String pattern, List<String> lines) {
      int m = pattern.length();
      boolean found = false;
      int compCount = 0;
  
      for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
          String text = lines.get(lineIndex);
          int n = text.length();
  
          for (int i = m - 1; i < n; ) {
              int k = 0;
  
              while (k < m && pattern.charAt(m - 1 - k) == text.charAt(i - k)) {
                  k++;
                  compCount++;
              }
  
              if (k == m) {
                  found = true;
                  System.out.println("Job found at line " + (lineIndex + 1) + ": " + text);
                  // Removed break to allow searching for subsequent matches
              }
  
              // Use a default shift value for characters outside the table's range
              int shift = (i < n && (text.charAt(i) & 0xFFFF) < SIZE) ? table[text.charAt(i)] : m;
              i += shift;
              compCount++;
          }
      }
  
      if (!found) {
          System.out.println("Job Not Found");
      }
      System.out.println("Total number of comparisons: " + compCount);
  }
  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path containing the job description:");
        String filePath = sc.next();
        
        try {
            List<String> lines = readAndStoreLines(filePath);
            System.out.println("Enter the job search keyword:");
            String pattern = sc.next();
            BoyerMoore bm = new BoyerMoore();

            bm.tableShift(pattern);
            bm.horspoolMatch(pattern, lines);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public static List<String> readAndStoreLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}


// sas
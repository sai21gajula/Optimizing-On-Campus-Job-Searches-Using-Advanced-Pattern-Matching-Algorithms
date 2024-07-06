import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KMP {
    void KMPSearch(String pat, List<String> txtLines) {
        int M = pat.length();
        boolean found = false;
        int count = 0;

        // create lps[] that will hold the longest prefix suffix values for pattern
        int[] lps = new int[M];
        
        // Preprocess the pattern (calculate lps[] array)
        computeLPSArray(pat, M, lps);

        for (int lineIndex = 0; lineIndex < txtLines.size(); lineIndex++) {
            String txt = txtLines.get(lineIndex);
            int N = txt.length();
            int j = 0; // index for pat[]
            int i = 0; // index for txt[]
            while ((N - i) >= (M - j)) {
                count += 1;
                if (pat.charAt(j) == txt.charAt(i)) {
                    j++;
                    i++;
                }
                if (j == M) {
                    found = true;
                    System.out.println("Job found at line " + (lineIndex + 1) + ": " + txt);
                    j = lps[j - 1];
                    // To find subsequent matches, do not break here.
                }

                // mismatch after j matches
                else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                    if (j != 0) j = lps[j - 1];
                    else i = i + 1;
                }
            }
        }

        if (!found) System.out.println("Job Not Found");
        System.out.println("Number of comparisons: " + count);
    }

    void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0; // length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0; // lps[0] is always 0
        
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the file path containing the job description:");
        String filePath = sc.next();
        
        try {
            List<String> lines = readAndStoreLines(filePath);
            System.out.println("Enter the job search keyword:");
            String pattern = sc.next();

            new KMP().KMPSearch(pattern, lines);
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

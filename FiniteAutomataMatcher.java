import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class FiniteAutomataMatcher {

    public static final int ALPHABET_SIZE = 256; // Assuming ASCII characters

    private static int[][] buildFA(String pat) {
        int M = pat.length();
        int[][] FA = new int[M + 1][ALPHABET_SIZE];
        for (int[] row : FA) {
            Arrays.fill(row, 0);
        }
        FA[0][pat.charAt(0)] = 1;

        for (int X = 0, j = 1; j <= M; j++) {
            for (int c = 0; c < ALPHABET_SIZE; c++) {
                FA[j][c] = FA[X][c];
            }
            if (j != M) {
                FA[j][pat.charAt(j)] = j + 1;
                X = FA[X][pat.charAt(j)];
            }
        }
        return FA;
    }

    private static void search(String text, String pat, int[][] FA) {
        int M = pat.length();
        int N = text.length();

        int state = 0;
        int comparisons = 0;

        for (int i = 0; i < N; i++) {
            state = FA[state][text.charAt(i)];
            comparisons++;

            if (state == M) {
                System.out.println("Pattern found at index " + (i - M + 1));
            }
        }

        System.out.println("Comparisons for pattern \"" + pat + "\": " + comparisons);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Specify your text file path here
        String textFilePath = "/Users/bharathgajula/Downloads/testcases.txt.rtf";
        String text = readFile(textFilePath);

        // Getting job search pattern from user input
        System.out.println("Enter the job search:");
        String pat = scanner.nextLine();

        int[][] FA = buildFA(pat);
        search(text, pat, FA);
    }

    private static String readFile(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}

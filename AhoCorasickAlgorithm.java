import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AhoCorasickAlgorithm {
    static final int ALPHABET_SIZE = 256; // Assuming ASCII

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        TrieNode failNode;
        List<String> outputs = new ArrayList<>();
        int depth; // For counting comparisons
        
        public TrieNode(int depth) {
            this.depth = depth;
            failNode = null;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                this.children[i] = null;
            }
        }
    }

    private TrieNode root = new TrieNode(0);
    private int totalComparisons = 0;

    public void insertString(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            if (current.children[ch] == null) {
                current.children[ch] = new TrieNode(current.depth + 1);
            }
            current = current.children[ch];
        }
        current.outputs.add(str);
    }

    public void buildFailureLinks() {
        Queue<TrieNode> queue = new LinkedList<>();
        root.failNode = root;
        queue.add(root);

        while (!queue.isEmpty()) {
            TrieNode current = queue.poll();
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                if (current.children[i] != null) {
                    TrieNode child = current.children[i];
                    TrieNode fail = current.failNode;

                    while (fail.children[i] == null && fail != root) {
                        fail = fail.failNode;
                        totalComparisons++; // Counting comparisons
                    }
                    child.failNode = (fail.children[i] == null) ? root : fail.children[i];
                    child.outputs.addAll(child.failNode.outputs);
                    queue.add(child);
                }
            }
        }
    }

    public void search(String text) {
        long startTime = System.nanoTime(); // Start timer
        
        TrieNode current = root;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
    
            while (current.children[ch] == null && current != root) {
                current = current.failNode;
                totalComparisons++; // Counting comparisons
            }
            current = (current.children[ch] == null) ? root : current.children[ch];
    
            for (String output : current.outputs) {
                System.out.println("Found keyword \"" + output + "\" at index " + (i - output.length() + 1));
            }
        }
        
        long endTime = System.nanoTime(); // End timer
        long duration = (endTime - startTime); // Duration in nanoseconds
        System.out.println("Search took " + (duration / 1e9) + " seconds.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AhoCorasickAlgorithm aca = new AhoCorasickAlgorithm();
    
        System.out.println("Enter the file path containing the job description:");
        String filePath = scanner.nextLine();
        String text = readFile(filePath);
    
        System.out.println("Enter keywords (separated by comma):");
        String[] keywords = scanner.nextLine().split(",");
        for (String keyword : keywords) {
            aca.insertString(keyword.trim());
        }
        System.out.println("ERuuning");
        aca.buildFailureLinks();
        
        long overallStartTime = System.nanoTime(); // Start timing for overall process including building failure links
        aca.search(text);
        long overallEndTime = System.nanoTime(); // End timing
        System.out.println("Overall process took " + ((overallEndTime - overallStartTime) / 1e9) + " seconds.");
        
        System.out.println("Total comparisons made: " + aca.totalComparisons);
    }
    // readFile method remains unchanged
    //Users/bharathgajula/Downloads/testcases1.rtf

    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line + " ");
            }
        } catch (IOException e) {
            System.out.println("An error occurred reading the file.");
            e.printStackTrace();
        }
        return content.toString();
    }
}

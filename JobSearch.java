import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JobSearch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the file path containing the job descriptions:");
        String filePath = scanner.nextLine();
        
        System.out.println("Enter the job search keyword:");
        String keyword = scanner.nextLine().toLowerCase();
        
        String text = readJobListings(filePath);
        long startTime = System.currentTimeMillis();
        List<Integer> foundPositions = searchKeywordInText(text, keyword);
        long endTime = System.currentTimeMillis();
        
        List<String[]> jobDetails = findJobDetails(text, foundPositions);
        
        if (!jobDetails.isEmpty()) {
            System.out.println("Keyword found in the following jobs:");
            for (String[] job : jobDetails) {
                System.out.println("Job Title: " + job[0]);
                System.out.println("Keywords: " + job[1]);
                System.out.println("Job Description: " + job[2] + "\n");
            }
        } else {
            System.out.println("No jobs found containing the keyword.");
        }
        
        System.out.println("Starting position: " + (foundPositions.isEmpty() ? "Not Found" : foundPositions.get(0)));
        System.out.println("Search time: " + (endTime - startTime) + "ms");
        
        scanner.close();
    }
    
    private static String readJobListings(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    
    private static List<Integer> searchKeywordInText(String text, String keyword) {
        List<Integer> positions = new ArrayList<>();
        String lowerText = text.toLowerCase();
        int index = lowerText.indexOf(keyword);
        
        while (index != -1) {
            positions.add(index);
            index = lowerText.indexOf(keyword, index + keyword.length());
        }
        
        return positions;
    }
    
    private static List<String[]> findJobDetails(String text, List<Integer> positions) {
        List<String[]> jobDetails = new ArrayList<>();
        
        for (int position : positions) {
            int startIdx = text.lastIndexOf("Job Title: ", position);
            int endIdx = text.indexOf("\n\n", position);
            if (endIdx == -1) endIdx = text.length();
            String jobSection = text.substring(startIdx, endIdx).trim();
            
            String[] lines = jobSection.split("\n");
            if (lines.length >= 3) {
                String jobTitle = lines[0].replace("Job Title: ", "").trim();
                String keywords = lines[1].replace("Keywords: ", "").trim();
                String jobDesc = lines[2].replace("Job Description: ", "").trim();
                
                jobDetails.add(new String[]{jobTitle, keywords, jobDesc});
            }
        }
        
        return jobDetails;
    }
}

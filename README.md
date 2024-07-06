# README

## Project Title: Optimizing On-Campus Job Searches Using Advanced Pattern Matching Algorithms

### Project Overview
This project aims to enhance the efficiency of on-campus job search portals by implementing and comparing various pattern matching algorithms. The algorithms analyzed in this study include the Naive Method, Rabin-Karp, Knuth-Morris-Pratt (KMP), and Boyer-Moore-Horspool. Our goal is to identify the most effective algorithm in terms of minimizing comparisons and optimizing search results to support students in finding on-campus employment opportunities.


### Introduction
On-campus jobs are crucial for students, providing financial support and valuable work experience. However, finding these jobs can be challenging due to limited availability and a lack of awareness about opportunities. Our project addresses these challenges by utilizing pattern matching algorithms to streamline the job search process.

### Objectives
- Compare the performance and efficiency of various pattern matching algorithms.
- Determine the algorithm best suited for on-campus job search portals by evaluating the number of comparisons made between job descriptions and search keywords.
- Enhance the overall user experience of the job search platform.

### Algorithms Analyzed
1. **Naive String Matching Algorithm**:
   - A straightforward approach that compares each character of the pattern with each character of the text.
   - Time Complexity: O((n-m+1)m) in the worst case.

2. **Rabin-Karp Algorithm**:
   - Utilizes hashing to find pattern occurrences within a text efficiently.
   - Time Complexity: O(n + m) on average, O(nm) in the worst case due to hash collisions.

3. **Knuth-Morris-Pratt (KMP) Algorithm**:
   - Preprocesses the pattern to create a partial match table, skipping unnecessary comparisons.
   - Time Complexity: O(n + m).

4. **Boyer-Moore-Horspool Algorithm**:
   - A simplified version of the Boyer-Moore algorithm using a shift table to determine the shift amount during mismatches.
   - Time Complexity: Linear in the best and average cases.

### Implementation
The project includes Java implementations of all four algorithms. Each algorithm was tested with varying lengths of job descriptions and search keywords to evaluate their performance. The Boyer-Moore-Horspool algorithm consistently demonstrated the least number of comparisons, making it the most efficient for our use case.

### Test Cases
1. **Test Case 1**:
   - Job Description Length: 661
   - Search Keywords Length: 2
   - Results:
     - Naive: 293 comparisons
     - Rabin-Karp: 267 comparisons
     - KMP: 274 comparisons
     - Boyer-Moore-Horspool: 49 comparisons

2. **Test Case 2**:
   - Job Description Length: 51
   - Search Keywords Length: 1
   - Results:
     - Naive: 297 comparisons
     - Rabin-Karp: 293 comparisons
     - KMP: 295 comparisons
     - Boyer-Moore-Horspool: 102 comparisons

3. **Test Case 3**:
   - Job Description Length: 46
   - Search Keywords Length: 2
   - Results:
     - Naive: 322 comparisons
     - Rabin-Karp: 321 comparisons
     - KMP: 321 comparisons
     - Boyer-Moore-Horspool: 67 comparisons

### Conclusion
The Boyer-Moore-Horspool algorithm is identified as the most efficient for optimizing on-campus job searches. It minimizes the number of comparisons while delivering accurate results, enhancing the overall user experience.

### Future Work
Future enhancements could include testing with larger datasets, exploring additional pattern matching algorithms, and developing a front-end application for better visualization of search results.

### How to Run the Project
1. **Setup**:
   - Ensure you have Java Development Kit (JDK) installed.
   - Clone the project repository to your local machine.

2. **Running the Algorithms**:
   - Navigate to the project directory.
   - Compile the Java files using `javac`.
   - Run the main class for each algorithm using `java`.

### Contact Information
For any questions or further information, please contact the project team members.

---

This README provides a comprehensive overview of the project, including its objectives, methodology, results, and instructions for running the code.

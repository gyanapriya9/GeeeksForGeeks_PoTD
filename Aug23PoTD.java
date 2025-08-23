/*
    PoTD Date: 23rd Aug 2025

    Quick Flashcard:
    -----------------
    Problem:
    - Given 'arr[]' where arr[i] = number of pages in i-th book.
    - Allocate books to 'k' students such that:
        1. Each student gets at least one book.
        2. Allocation is contiguous.
        3. No book is shared between students.
    - Goal: Minimize the maximum pages assigned to a student.

    Key Notes:
    - If k > number of books, allocation is impossible → return -1.
    - Otherwise, we must partition books optimally.

    Approach (Binary Search on Answer):
    - Minimum possible limit = max(pages in a single book).
    - Maximum possible limit = sum of all pages.
    - Apply binary search on this range:
        * For each mid value, check if allocation is possible without exceeding it.
        * If yes → try smaller maximum (hi = mid - 1).
        * If no → increase limit (lo = mid + 1).
    - Answer is the smallest valid 'mid'.

    Example:
    arr = [12, 34, 67, 90], k = 2
    - Possible allocations:
        [12,34,67] & [90] → Max = 113 (optimal)
        [12,34] & [67,90] → Max = 157
        [12] & [34,67,90] → Max = 191
    - Result = 113
*/

class Solution {
    // Helper function: checks if books can be allocated with given pageLimit
    public boolean check(int[] arr, int k, int pageLimit) {
        int cnt = 1;           // Number of students allocated so far
        int pageSum = 0;       // Pages assigned to current student

        for (int i = 0; i < arr.length; i++) {
            // If adding this book exceeds limit, allocate to next student
            if (pageSum + arr[i] > pageLimit) {
                cnt++;
                pageSum = arr[i];
            } else {
                pageSum += arr[i]; // Assign to current student
            }
        }
        // Allocation is valid if we used ≤ k students
        return (cnt <= k);
    }

    // Main function to find minimum pages
    public int findPages(int[] arr, int k) {
        // If students are more than books → impossible
        if (k > arr.length) return -1;

        // Define binary search range:
        int lo = Arrays.stream(arr).max().getAsInt();  // Lower bound: max book
        int hi = Arrays.stream(arr).sum();             // Upper bound: total pages
        int res = -1;

        // Binary search to minimize maximum pages
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // Middle point

            if (check(arr, k, mid)) {
                res = mid;    // Possible answer, try for smaller
                hi = mid - 1;
            } else {
                lo = mid + 1; // Too small, increase pages
            }
        }
        return res;
    }
}

/*
    Time Complexity:
    - Binary Search range = (sum of arr - max(arr)).
    - Each check() takes O(n).
    - Overall: O(n * log(sum of pages)).

    Space Complexity:
    - O(1) extra space.
*/

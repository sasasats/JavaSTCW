package algo;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        printAnswer(new int[]{2, 7, 11, 5}, 9);
        printAnswer(new int[]{3, 2, 4}, 6);
        printAnswer(new int[]{3, 3}, 6);

        printAnswerOptimized(new int[]{2, 7, 11, 5}, 9);
        printAnswerOptimized(new int[]{3, 2, 4}, 6);
        printAnswerOptimized(new int[]{3, 3}, 6);
    }

    private static void printAnswer(int[] nums, int target) {
        var result = twoSum(nums, target);
        System.out.println("[%s,%s]".formatted(result[0], result[1]));
    }

    private static void printAnswerOptimized(int[] nums, int target) {
        var result = twoSumOptimized(nums, target);
        System.out.println("[%s,%s]".formatted(result[0], result[1]));
    }

    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    private static int[] twoSumOptimized(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 1. Running Sum of 1d Array
     * 2. How Many Numbers Are Smaller Than the Current Number
     *
     * 3. Two Sum
     * 4. Valid Anagram
     * 5. Isomorphic Strings
     *
     * 6. Find Pivot Index
     *
     * 7. Binary Search
     * 8. First Bad Version
     *
     * 9. Baseball Game
     * 10. Valid Parentheses
     *
     * 11. Fibonacci Number
     *
     * 12. Rank Transform of an Array
     *
     * 13. Longest Palindrome
     */
}

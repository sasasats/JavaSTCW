package algo;

public class RunningSumOfArray {
    public static void main(String[] args) {
        printAnswer(new int[]{1,2,3,4});
        printAnswer(new int[]{1,1,1,1,1});
        printAnswer(new int[]{3,1,2,10,1});
    }

    private static void printAnswer(int[] nums) {
        int[] result = runningSum(nums);
        for (int num : result) {
            System.out.println(num);
        }
        System.out.println("--------------------");
    }

    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] + sum;
            sum += nums[i];
        }
        return result;
    }
}

package ALOGORITHM.JIANZHI;

/**
 * @author canoeYang
 * @Date 2020-08-30 18:35
 */
public class ExchangeEvenOdd21 {
    public int[] exchange(int[] nums) {
        if(nums.length==0){
            return nums;
        }
        int low = 0;
        int high = nums.length-1;
        while(low<high){
            while(high>low && nums[high]%2==0){
                high--;
            }
            if(low<high){
                swap(nums,low,high);
            }
            while(high>low && nums[low]%2==1){
                low++;
            }
            if(low<high){
                swap(nums,low,high);
            }
        }
        return nums;
    }
    private static void swap(int[] nums,int low,int high){
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }
}

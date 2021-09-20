public class L02{
    //****************************** 17 SEPTEMBER ***********************************
    //LEETCODE 795 -> https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int count = 0;
        int prevCount = 0;
        
        int start = 0;
        int end = 0;
        
        while(end < nums.length){
            if(left <= nums[end] && nums[end] <= right){
                prevCount = end - start + 1;
                count += end - start + 1;
            }else if(nums[end] < left){ 
                count += prevCount;
            }else{ // agar number bada hai toh vo breakpoint hai simply vo array khtam and naya array start karo
                prevCount = 0;
                start = end+1;
            }
            end++;
            
        }
        
        return count;
    }
    public static void swap(int[] arr,int i , int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]= temp;
    }

    //gfg wave array
    //Wiggle sort -> https://www.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/wiggle-sort-1/ojquestion
    public static void wiggleSort(int[] arr) {
      // write your code here
      for(int i =0;i<arr.length -1  ;i++){
          if(i % 2 == 0){
              if(arr[i] > arr[i+1]){
                  swap(arr,i,i+1);
              }
          }else{
              if(arr[i] < arr[i+1]){
                  swap(arr,i,i+1);
              }
          }
      }
    }

    //LEETCODE 324 => WIGGLE SORT II -> https://leetcode.com/problems/wiggle-sort-ii/
    // SORT KRKE i start mai j last  mai and i ko bhara then j ko bhara nhi kr skte becoz equal wala case fat skta hai.eg 1 1 2 2 2 4 5 6 ismai krnge toh  1 6 1 5 2 4 2 2 
    //ab ismai 2 2 saath saath ho gye par hume slternate bnane the
    
    //so we will find middle and ulta bharnge eg 2 6 2 5 1 4 1 2 ulta bharnge i k middle se 0 ki taraf
    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i]= nums[i];
        }
        
        Arrays.sort(arr);
        
        int i = 1;
        int j = n - 1;
        while(i < n){
            nums[i] = arr[j];
            j--;
            i += 2;
        }
        i = 0;
        while( i < n){
            nums[i] = arr[j];
            i += 2;
            j--;
        }    
    }

    //LINTCODE 903 -> https://www.lintcode.com/problem/range-addition

    //queris pr iterate krke mark kr liya jaha se jaha bola h stsrting idx  pr 2 and jaha bola h uske ek zyada pr -2 
    //phir array pr traverse krte hue jo number pda h array position pr add krdo last val mai culminative sum
    public int[] getModifiedArray(int length, int[][] queries) {
        // Write your code here
        int[] arr = new int[length];
        // travel on query and mark impact
        for(int i  = 0; i < queries.length ; i++){
             int start = queries[i][0];
             int end = queries[i][1];
             int val = queries[i][2];

             arr[start] += val;
             if(end != length-1){
                  arr[end + 1 ] -= (val);
             }
        }
         // travel on array and make impact visible using prefix sum
        for(int i = 1 ; i< length ; i++){
            arr[i] += arr[i-1];
        }
       return arr;
    }

    //LEETCODE 238 -> https://leetcode.com/problems/product-of-array-except-self/
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length ;
         // make right product array
        int[] rightpro = new int[n+1];
         rightpro[n] = 1;
         for(int i = n-1 ; i>= 0; i--){
             rightpro[i] =  rightpro[i+1] * nums[i];
         }
     
         int[] ans = new int[n];
         // maintain left product in running time and solve 
         int leftPro = 1;
         for(int i = 0; i < n; i++){
             ans[i] = leftPro * rightpro[i+1];
             leftPro = leftPro * nums[i] ;
         }
         return ans;
     }



    //LEETCODE 118 -> https://leetcode.com/problems/pascals-triangle/

    public List<List<Integer>> generate(int n) {
        if( n == 0){
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i <n ; i++){
            List<Integer> row = new ArrayList<>();
            int icj = 1;
            for(int j = 0; j <= i ; j++){
                row.add(icj);
                int icjp1 = icj *(i-j)/(j+1);
                icj = icjp1;
            }
            result.add(row);
        }
        return result;
    }

     //****************************** 18 SEPTEMBER ***********************************
    //LEETCODE 849 -> https://leetcode.com/problems/maximize-distance-to-closest-person/
    // phle ek left array ek right array bnaya
    // left array ko bharo jo left side se pass hai and right array mai jo right se pass hai
    //in dono mai se minimum nikala 
    //and jo array aygi usmai max nikalo

    public int maxDistToClosest(int[] seats) {
       // step 1. travel on arr and make left person distance and empty seats, and -1 at occupied seat
        int idx =  Integer.MAX_VALUE;
        for(int i = 0; i < seats.length ; i++){
            if(seats[i] == 1){
               idx = i;
               seats[i] = -1;
            }else{
                seats[i] = Math.abs(idx - i);
            }
        }
        /* step 2. travel from right to left and make arr as closest person 
            distance and manage max of closest person distnace*/
        int max = 0;
        idx = Integer.MAX_VALUE;;
        for(int i= seats.length - 1; i>= 0; i--){
            if(seats[i] == -1){
                idx = i;
            }else{
                seats[i] = Math.min (seats[i],Math.abs(idx - i));
                max = Math.max(max,seats[i]);
            }
        }
        return max;
    }

    //LINTCODE 912 -> BEST MEEING POINT -> https://www.lintcode.com/problem/912/

    //LEETCODE 41 -> https://leetcode.com/problems/first-missing-positive/\
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // step 1. mark number out of range and check presence of one
        boolean one = false;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) 
                one = true;
            
            if(nums[i] < 1 || n < nums[i]) {
                nums[i] = 1;
            } 
        }
        if(one == false) return 1;
        // step 2. travel and mark present index in array
        for(int i = 0; i < n; i++) {
            int indx = Math.abs(nums[i]) - 1; 
            nums[indx] = -Math.abs(nums[indx]);
        }
        // step 3. travel and check unmarked index, if found return indx + 1, otherwise n + 1
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    // leetcode 670 ->  https://leetcode.com/problems/maximum-swap/
    //max swap
    //best meeting
    //chunks 2
}
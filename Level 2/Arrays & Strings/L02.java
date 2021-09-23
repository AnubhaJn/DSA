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
    public int maximumSwap(int num) {
        char[] nums = Integer.toString(num).toCharArray();
        int[] lastIdx = new int[10]; // make array of digits jo contain krega last index jaha vo digit mila tha
        for(int i = 0; i < nums.length ; i++){
            lastIdx[nums[i]-'0'] = i;
        }
        
        for(int i = 0 ; i < nums.length; i++){
            int digit = nums[i] -'0';
            boolean flag = true;
            for(int j = 9; j > digit; j--){
                if( lastIdx[j] > i){ // check kra 9 se lekar ith digit se bado mai agar koi bhi baad mai h toh use swap krlo
                    char temp = nums[i];
                    nums[i] = nums[lastIdx[j]];
                    nums[lastIdx[j]] = temp;
                    flag = false;
                    break;
                }
            }
            if(flag == false){
                break;
            }
        }
         int nbr = 0;
         for(int i = 0; i < nums.length;i++) // toform number
             nbr = nbr*10+(nums[i]-'0');
        
        return nbr;
    }
    
    //****************************** 19 SEPTEMBER ***********************************
    // 2 Sum - Target Sum Unique Pairs: https://classroom.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/2-sum-target-sum-unique-pairs/ojquestion

    public static List<List<Integer>> twoSum(int[] arr, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        int i = 0;
        int j = arr.length -1;
        while(i < j){
            if(i != 0 && arr[i] == arr[i-1]){
                i++;
                continue;
            }
            if(arr[i] + arr[j] == target){
                List<Integer> ans = new ArrayList<>();
                ans.add(arr[i]);
                ans.add(arr[j]);
                result.add(ans);
                i++;
                j--;
            }else if(arr[i] + arr[j] > target){
                j--;
            }else{
                i++;
            }
        }
        return result;
      }
    

    // 3 Sum - Target Sum Unique Triplet: https://classroom.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/3-sum-target-sum-unique-triplet/ojquestion
    //LEETCODE 15 : 3 Sum : https://leetcode.com/problems/3sum/

    public List<List<Integer>> twoSum(int[] nums,int target, int st){
        List<List<Integer>> res = new ArrayList<>();
        int i = st;
        int j = nums.length-1;
        while(i < j){
            if(i != st && nums[i] == nums[i-1]){
                i++;
                continue;
            }
            int sum = nums[i] + nums[j];
            if(sum == target){
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                res.add(list);
                i++;
                j--;
            }else if( sum > target){
                j--;
            }else{
                i++;
            }
        }
        return res;
    }
    
    public List<List<Integer>> threeSum_(int[] nums,int target){
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i =0; i <= n-3;i++){
           int val1 = nums[i];
            
            if(i != 0  && nums[i] == nums[i-1]){
                continue;
            }
            
            List<List<Integer>> subRes = twoSum(nums,target-val1,i+1);
            for(List<Integer> list: subRes){
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum_(nums,0);
    }

    //4 Sum - Target Sum With Unique Quadruple :https://classroom.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/4-sum-target-sum-with-unique-quadruple/ojquestion
    //LEETCODE 18 : 4 sum : https://leetcode.com/problems/4sum/
    public List<List<Integer>> twoSum(int[] nums,int target, int st){
        List<List<Integer>> res = new ArrayList<>();
        int i = st;
        int j = nums.length-1;
        while(i < j){
            if(i != st && nums[i] == nums[i-1]){
                i++;
                continue;
            }
            int sum = nums[i] + nums[j];
            if(sum == target){
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                list.add(nums[j]);
                res.add(list);
                i++;
                j--;
            }else if( sum > target){
                j--;
            }else{
                i++;
            }
        }
        return res;
    }
    
    public List<List<Integer>> threeSum_(int[] nums,int target,int st){
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = st; i <= n-3;i++){
           int val1 = nums[i];
            
            if(i != st  && nums[i] == nums[i-1]){
                continue;
            }
            
            List<List<Integer>> subRes = twoSum(nums,target-val1,i+1);
            for(List<Integer> list: subRes){
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = 0; i <= n-4;i++){
           int val1 = nums[i];
            
            if(i != 0  && nums[i] == nums[i-1]){
                continue;
            }
            
            List<List<Integer>> subRes = threeSum_(nums,target-val1,i+1);
            for(List<Integer> list: subRes){
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }
   
    //KSum : https://classroom.pepcoding.com/resources/data-structures-and-algorithms-in-java-levelup/arrays-and-strings/k-sum-target-sum-unique-set/ojquestion
    public static List<List<Integer>> KSum_(int[] nums,int target,int st,int k){
        if(k == 2){
            return twoSum(nums,target,st);
         }
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = st; i <= n-k ;i++){
           int val1 = nums[i];
            
            if(i != st  && nums[i] == nums[i-1]){
                continue;
            }
            
            List<List<Integer>> subRes = KSum_(nums,target-val1,i+1,k-1);
            for(List<Integer> list: subRes){
                list.add(val1);
                res.add(list);
            }
        }
        return res;
    }
  public static List<List<Integer>> kSum(int[] nums, int target, int k) {
    Arrays.sort(nums);
    return KSum_(nums,target,0,k);
    }

    //LEETCODE 537 : https://leetcode.com/problems/complex-number-multiplication/submissions/
    public String complexNumberMultiply(String num1, String num2) {
        int a1 = Integer.parseInt(num1.substring(0,num1.indexOf("+")));
        int b1 = Integer.parseInt(num1.substring(num1.indexOf("+")+1, num1.length()-1 ));
        
        int a2 = Integer.parseInt(num2.substring(0,num2.indexOf("+")));
        int b2 = Integer.parseInt(num2.substring(num2.indexOf("+")+1, num2.length()-1));
        
        int real = a1*a2 - b1*b2;
        int imaginary = a1*b2 + a2 *b1;
        return real +"+"+imaginary+"i";
    }

    // GFG : Minimum platform : https://practice.geeksforgeeks.org/problems/minimum-platforms-1587115620/1#
    public static int findPlatform(int arr[], int dep[], int n)
    {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i= 0 ,j= 0;
        int max = 0;
        int platform = 0;
        
        while(i < n){
            if(arr[i] <= dep[j]){
               i++;
               platform ++;    
            }else{
                j++;
                platform-- ;
            }
            max = Math.max(max,platform);
        }
        return max;
    }

}
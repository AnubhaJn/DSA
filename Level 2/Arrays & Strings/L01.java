public class L01{

    //****************************** 10 SEPTEMBER ***********************************
    // LEETCODE 925 -> https://leetcode.com/problems/long-pressed-name/submissions/
    //Complexity: O(n)

    public boolean isLongPressedName(String name, String typed) {
        if(name.length() > typed.length()){ // if typed length is less than name length
            return false;
        }
        
        int i = 0; 
        int j = 0;
        while(i< name.length() && j < typed.length()){
            if(name.charAt(i) == typed.charAt(j)){
                 i++;
                 j++;
            }else{ // if i j not equal
                if( i-1 >= 0 && name.charAt(i-1) == typed.charAt(j)){ // check if jth char is equal to i-1 th char
                    j++;
                }else{
                    return false;
                }
            }  
        }
        
        while(j < typed.length()){ // if j has nott fully reached end
            if(typed.charAt(j) != name.charAt(i-1)){
                return false;
            } else{
                j++;
            }
        }
        
        return i == name.length(); // if i is not fully reached end
    }


    // LEETCODE 11 -> https://leetcode.com/problems/container-with-most-water/
    //BRUTE FORCE ---> Complexity: O(n^2)
    public int maxArea(int[] height) {
        int maxWat = 0;
        
        for( int i = 0; i< height.length - 1 ; i++){
             int a = height[i] ;
            for(int j = i + 1; j< height.length ; j++){
                int b = height[j];
                int breadth = j-i;
                int length = Math.min(a,b);
                
                maxWat = Math.max(maxWat, length*breadth);
            }
        }
        
        return maxWat;
    }

    //OPTIMIZED ----> Complexity: O(n)
    //firstly we are giving priority to the length and hen smartly decreasing the length
    //by changing i and j depending which one is small

    public int maxArea(int[] height) {   
        int maxWater = 0;
        int i = 0;
        int j = height.length-1;
        while(i < j){
            int l = j-i;
            int h = Math.min(height[i],height[j]);
            int currentWater = l * h;
            
            maxWater = Math.max(maxWater,currentWater);
            
            if(height[i] < height[j]){
                // if ith height is small then there is no sense decreasing j as height will still remain height[i]even if height[j-1] is larger and if smaller then height will be even smaller
                //and length is toh decreasing as well so no chance area will increase
                // smart way is to increase i so that there is possiblity despite of length decreasing , may be height can be more so one factor can still increase
                i++;
            }else{
                // similarily in j case if height of j small decrease j 
                j--;
            }
        }
        return maxWater;
    }

    //LEETCODE 977 ->https://leetcode.com/problems/squares-of-a-sorted-array/
    // space : O(n) , time : O(n)
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        int i = 0;
        int j = nums.length -1;
        int k = nums.length -1;
        
        while(k >= 0){
            int val1 = nums[i] * nums[i];
            int val2 = nums[j] * nums[j];
            
            if( val1 > val2 ){
                res[k] = val1;
                i++;
            }else{
                res[k] = val2;
                j--;
            }
            
            k--;
        }
        
        return res;
    }

    
    //LEETCODE 169 ->https://leetcode.com/problems/majority-element/
    //space : O(1) , time : 0(n)
    public int majorityElement(int[] arr) {
        // when surity that majority element exists
        //MOORE'S VOTING ALGO
        int n = arr.length;
        int val = arr[0];
        int count = 1;
        
        for( int i = 1; i< n ; i++){
            if(arr[i] == val){
                count++;
            }else{
                if(count > 0){
                    count--; // virtually making pair so decreasing count value
                }else{
                     val = arr[i]; // when count 0 make element as new val
                     count = 1;
                }
            }
        }
        
        return val;
    }

    //LEETCODE 229 -> https://leetcode.com/problems/majority-element-ii/
    public boolean ismajority(int[] nums, int val){
        int count = 0;
        for(int i = 0; i < nums.length ; i++){
            if(nums[i] == val){
                count++;
            }
        }
            return count > nums.length / 3;
    }
   

    public List<Integer> majorityElement(int[] nums) {
        int  val1 = nums[0];
        int count1 = 1;
        int val2 = nums[0];
        int count2 = 0;
        
        for(int i = 1 ;i< nums.length; i++){
            if(nums[i] == val1){
                count1++;
            }else if(nums[i] == val2){
                count2++;
            }else{
                if(count1 == 0){
                    val1 = nums[i];
                    count1 = 0;
                }else if(count2 == 0){
                    val2 = nums[i];
                    count2 = 1;
                }else{
                    count1--;
                    count2--;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        
        if(ismajority(nums,val1)){
            res.add(val1);
        }
        
        if(val1 != val2 && ismajority(nums,val2) ){
            res.add(val2);
        }
        
        return res;
    }


    //HW. MAJORITY ELEMENT GENERAL
    //if k > 3 make hashmap
    public int majorityElement(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++)
        {
            if (mp.containsKey(arr[i]))
            {
                mp.put(arr[i], mp.get(arr[i]) + 1);
            }
            else
            {
                mp.put(arr[i], 1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : mp.entrySet())
        {
            if(entry.getValue() > n/K){
                res.add(entry.getKey);
            }
        }
        
        return res;
    }
    
    //****************************** 11 SEPTEMBER ***********************************
    //LEETCODE 556 -> https://leetcode.com/problems/next-greater-element-iii/
    public int dipIndex(char[] arr){
        int idx = -1;
        for(int i = arr.length -1 ;i> 0 ; i--){
            if(arr[i-1] < arr[i]){
                idx =  i-1;
                break;
            }
        }
        return idx;
    }
    
    public int ceilIdx (char[] arr, int dipIdx){
        int val = arr[dipIdx];
        int i = arr.length -1;
        while(val >= arr[i]){
            i--;
        }
        return i;
    }
    
    public void swap(int i,int j,char[] arr){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public void reverse(char[] arr, int i, int j){
        while(i < j){
            swap(i,j,arr);
            i++;
            j--;
        }
    }
    
    public String helper(String str){
        char[] arr = str.toCharArray();
        int dipIdx = dipIndex(arr);
        if( dipIdx == -1){
           return "-1";
       }
        
       int ceilIndex = ceilIdx(arr,dipIdx);
       swap(dipIdx,ceilIndex,arr);
        
       reverse(arr,dipIdx+1, arr.length-1);
       return String.valueOf(arr);
    }
    
    public int nextGreaterElement(int n) {
        if (n < 10) return -1;
        String nextNum = helper(""+ n);
        long num = Long.parseLong(nextNum);
        if(num > Integer.MAX_VALUE ){
            return (-1);
        }else{
            return (int)num;
        }
        
    }


    //LEETCODE 905 -> https://leetcode.com/problems/sort-array-by-parity/
    //even numbers aage odd numbers peeche
    public static void sortArrayByParity(int[] nums) {
        // write your code here
        int i = 0; // unsorted
        int j = 0; // odd partition starts
        
        while(i < nums.length){
            if(nums[i] % 2 != 0){
                i++;
            }else{
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                i++;
                j++;
            }
        }
        
        return;
    }

    //****************************** 12 SEPTEMBER ***********************************
    //LEETCODE 628 - https://leetcode.com/problems/maximum-product-of-three-numbers/
    // IF all numbers positive then simply take product of all three max numbers of array
    //but if negative numbers are there then this algo will fail eg -5 ,-6, 3,2, 4 
    //max ->-5 * -6 * 4 =120 and not 3*2*4 = 24

    //ALGO -> simply find the max 3 elements and min 2 elements and then check by forming 2 groups one being max1,max2,max3 and
    //second being max1, min1, min2 as min1 min2 can be negative and can make product (-)*(-) as positive

    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for(int val : nums){
            if(val > max1){
                max3 = max2;
                max2 = max1;
                max1 = val;
            }else if( val > max2 ){
                max3 = max2;
                max2 = val;
            }else if( val > max3){
                max3 = val;
            }
            if(val < min1 ){
                min2 = min1;
                min1 = val;
            }else if( val < min2){
                min2 = val;
            }
        }
            return Math.max(max1 * max2 * max3 , min1 * min2 * max1); 
    }


    // leetcode 769. https://leetcode.com/problems/max-chunks-to-make-sorted/
    //simply check the range,max and the numbers impact 
    //update max each time and check if max == i that is the position till the impact of max has reached
    //if reached it means usse phle jite numbers the unk impact was lesser than that max number so now it was a chunk
    //so increase the count of chunk when max and i i.e index position is same

    public int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        int max = 0;
        
        for(int i = 0 ; i < arr.length ;i++){
            max = Math.max(max,arr[i]);
            if(max == i){
                chunks++ ;
            }
        }
        return chunks;
    }

    // leetcode 768. https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        // prepare right min and manage left max while running with loop
        int[] rightMin = new int[n];
        rightMin[n - 1] = arr[n - 1];
        for(int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        // count chunks 
        int count = 1;
        int leftmax = Integer.MIN_VALUE;
        for(int i = 0; i < n - 1; i++) {
            leftmax = Math.max(leftmax, arr[i]);
            if(leftmax <= rightMin[i + 1]) {
                count++;
            }
        }
        return count;
    }


    // leetcode 747. https://leetcode.com/problems/largest-number-at-least-twice-of-others/
    public int dominantIndex(int[] nums) {
        if(nums.length == 1) return 0;
        int max = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int idx = 0;
        for(int i = 0; i< nums.length; i++){
            if(nums[i] > max){
                max2 = max;
                max = nums[i];
                idx = i;
            }else if( nums[i] > max2)
                max2 = nums[i];
        }
        return 2* max2 <= max? idx : -1;
    }

    // leetcode 345. https://leetcode.com/problems/reverse-vowels-of-a-string/
    public String reverseVowels(String s) {
        int x=0;
        int y=s.length()-1;
        
        List<Character> list=Arrays.asList('a','e','i','o','u','A','E','I','O','U');
       
        char[] arr=s.toCharArray();
        while(x<y){
            if(!list.contains(arr[x])){
                x++;
            }
            if(!list.contains(arr[y])){
                y--;
            }
            if(list.contains(arr[x]) && list.contains(arr[y])){
                char temp=arr[x];
                arr[x]=arr[y];
                arr[y]=temp;
                x++;
                y--;
            }
        }
        return new String(arr);
    }

    public static void main(String[] args){

    }
}
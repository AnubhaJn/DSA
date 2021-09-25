Public class L03{
    //****************************** 24 SEPTEMBER ***********************************

    // LEETCODE 204 : https://leetcode.com/problems/count-primes/
    // Sieve Of Eratosthenes : https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/arrays-&-strings-l2/sieve-of-eratosthenes/ojquestion

    // make a boolean array and mark it all true
    // now if number is true means it is prime then mark all its factors less than upper limi as false
    // do this till root of upper limit 

    //complexity is O(q + n), where q is the number of queries and n is the upper limit
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n+1];
        
        Arrays.fill(isPrime,true);
        int count = 0;
        
        for(int i = 2; i * i <= n ; i++){
            if(isPrime[i] != false){
            for(int j = i + i ; j <= n; j+= i){
                isPrime[j]= false;
            }
          }
        }
        
        for(int i = 2; i < n; i++ ){
            if(isPrime[i] == true){
                count++;
            }
        }
        return count;
    }

    // Segmented Sieve : https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/arrays-&-strings-l2/segmented-sieve/ojquestion

    // ******* APPROACH 1*******
    // travel from a to b ie in the range and check for each number if prime or not
    // this is not optimized as its complexity is:
    // b-a ie. differnce of range and for each number root of that number
    //(b-a) * root b

    public static boolean isPrime(int num) {
        if(num == 0 || num == 1) return false;
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
    public static void segmentedSieveAlgo(int m, int n) {
        for(int j = m; j <= n; j++) {
                    if(isPrime(j)) {
                        System.out.println(j);
                    }
                }
                System.out.println();
    }


    // ******* APPROACH 2*******
    private static ArrayList<Integer> sieve(int n) {
        // pre calculation
        boolean[] isprime = new boolean[n + 1];

        // begin from 2 to root(n)
        for(int i = 2; i * i <= n; i++) {
            if(isprime[i] == true) {
                continue; // because if it is marked as not prime then its multiple are also marked
            }
            for(int j = i + i; j <= n; j += i) {
                isprime[j] = true;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 2; i < isprime.length; i++) {
            if(isprime[i] == false) {
                res.add(i);
            }
        }
        return res;
    }

    public static void segmentedSieveAlgo(int a, int b) {
        int rootb = (int)Math.sqrt(b);
        ArrayList<Integer> primes = sieve(rootb);

        int n = b - a;
        boolean[] isprime = new boolean[n + 1];
        // isprime[i] == true -> associated value is not prime
        // isprime[i] == false -> associated value is prime

        for(int prime : primes) {
            int multiple = (int)Math.ceil(a * 1.0 / prime);

            if(multiple == 1) multiple++;

            int si = multiple * prime - a;
            for(int i = si; i < isprime.length; i += prime) {
                isprime[i] = true; // mark it as not prime
            }
        }

        // travel and print prime
        for(int i = 0; i < isprime.length; i++) {
            if(isprime[i] == false && i + a != 1) {
                System.out.println(i + a);
            }
        }
    }

    // LEETCODE 881 : Boats to Save People:  https://leetcode.com/problems/boats-to-save-people/
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boats = 0;
        while(i <= j){
            int sum = people[i] + people[j];
            if(sum <= limit){
                i++;
                j--;
            }else{
                j--;
            }
            boats++;
        }
        return boats;
    }

    //LEETCODE 763: Partition Labels : https://leetcode.com/problems/partition-labels/
    public List<Integer> partitionLabels(String s) {
        int[] lastOcc = new int[26];
        for(int i = 0 ; i < s.length(); i++){
            lastOcc[s.charAt(i) -'a'] = i;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;
        int max = 0;
        
        for(int i = 0; i < s.length() ; i++){
            int num = lastOcc[s.charAt(i)-'a'];
            max = Math.max(max,num);
            if (max == i){
                count++;
                list.add(count);
                max = 0;
                count = 0;
                continue;
            }
            count++;
        }
        return list;
    }


    //****************************** 25 SEPTEMBER ***********************************
    //LEETCODE 754 : Reach a Number :  https://leetcode.com/problems/reach-a-number/
    // Time complexity is root n (solved in register)

    public static int minJumps(int target) {
        // Write your code here
        target = Math.abs(target);
        int steps = 0;
        int jumps = 0;
        
        while( steps < target){
            jumps++;
            steps += jumps;
        }
        
        if( (steps - target) % 2 == 0){ //i.e if reached target or if crossed target but the difference is even 
             return jumps;
        } 
        
        else if(( steps + jumps + 1 - target ) % 2 == 0){ // here meaning that add 1 jump more and move in opposite direction with (steps - target) % 2 jump.
            return jumps + 1 ;
        }
        
        else return jumps + 2;
    }

    //PORTAL : Transpose Of Matrix With Dimension N X N : https://classroom.pepcoding.com/myClassroom/the-placement-program-gtbit-nov-27-2020/arrays-&-strings-l2/transpose-matrix-nxn/ojquestion

    //LEETCODE 867 : Transpose Matrix : https://leetcode.com/problems/transpose-matrix/
    
    //LEETCODE 48 : Rotate Image : https://leetcode.com/problems/rotate-image/

    //LEETCODE 838 : Push Dominoes : https://leetcode.com/problems/push-dominoes/
}
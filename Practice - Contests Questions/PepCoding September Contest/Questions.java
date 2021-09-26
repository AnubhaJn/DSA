//Contest Link -https://www.hackerrank.com/contests/pp-trees-test-sept/challenges
//Date - 26/09/2021 

//Question 1 - https://www.hackerrank.com/contests/pp-trees-test-sept/challenges/right-side-view
public static void rightView(Node root) {

    if(root == null){
        return;
    }
    LinkedList<Node> que = new LinkedList<>();
        que.add(root);
    
    while(que.size() != 0){
        int size = que.size();
        System.out.print(que.getFirst().data+" ");
        while(size-- > 0){
            Node rrnode = que.removeFirst();
            if(rrnode.right != null){
                que.addLast(rrnode.right);
            }
            if(rrnode.left != null){
                que.addLast(rrnode.left);
            }
        }
    }
    return;
}

//Question 2 - https://www.hackerrank.com/contests/pp-trees-test-sept/challenges/leaf-to-root-path-sum
public static boolean isPath(Node node,int sum) {

  boolean ans = false;
  int subAns = sum - node.data;
  if(subAns == 0  && node.left == null && node.right == null){
      return (ans = true);
  }
  
  if(node.left != null){
      ans = ans || isPath(node.left, subAns);
  }
  if(node.right != null){
      ans = ans || isPath(node.right, subAns);
  }
    return ans;  
}

//Question 3 - https://www.hackerrank.com/contests/pp-trees-test-sept/challenges/left-leaves
public static boolean isLeaf(Node node){
    if(node == null){
        return false;
    }
    if(node.left == null && node.right == null){
        return true;
    }
    
    return false;
}

 public static int leftLeaves(Node node) {

    int res = 0;
    if(node != null){
        if(isLeaf(node.left)){
            res += node.left.data;
        }else{
            res += leftLeaves(node.left);
        }
        
        res += leftLeaves(node.right);
    }
    return res;
}


//Question 4 - https://www.hackerrank.com/contests/pp-trees-test-sept/challenges/transform-using-depth
public static void helper(Node node, int level){
    if(node == null){
        return;
    }
    
    node.data = level;
    for(Node child: node.children){
        helper(child,level+1);
        helper(child, level+1);
    }
}
  public static void transform(Node root) {
    helper(root,0);
    return;
}

//Question 5 -https://www.hackerrank.com/contests/pp-trees-test-sept/challenges/distance-between-two-nodes
public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
    if (node.data == data) {
      ArrayList<Integer> path = new ArrayList<>();
      path.add(node.data);
      return path;
    }

    for (Node child : node.children) {
      ArrayList<Integer> ptc = nodeToRootPath(child, data);
      if (ptc.size() > 0) {
        ptc.add(node.data);
        return ptc;
      }
    }

    return new ArrayList<>();
  }
    
  	public static int distance(Node node,int d1,int d2) {
    ArrayList<Integer> p1 = nodeToRootPath(node, d1);
    ArrayList<Integer> p2 = nodeToRootPath(node, d2);

    int i = p1.size() - 1;
    int j = p2.size() - 1;

    while(i >= 0 && j >= 0 && p1.get(i) == p2.get(j)){
      i--;
      j--;
    }

    i++;
    j++;

    return i + j;
    }
public class FindDuplicate {

    public static void main(String[] args) {
 if (args.length > 0){
    int ans = 0;
    int n = args.length;
        for (int i = 0; i < n; i++){
            ans += Integer.parseInt(args[i]);
        }
        int total = (n*(n+1))/2;
        if (total == ans){
            System.out.println(false);
        }
        else
            System.out.println(true);
        }

 }
        //WRITE YOUR CODE HERE
    }
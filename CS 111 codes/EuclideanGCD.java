mport java.util.Scanner;

public class EuclideanGCD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the first integer (a): ");
        int a = scanner.nextInt();
        
        System.out.print("Enter the second integer (b): ");
        int b = scanner.nextInt();
        
        int[] result = euclideanGCD(a, b);
        
        System.out.println("GCD of " + a + " and " + b + " is " + result[0] + ", found in " + result[1] + " steps.");
        
        scanner.close();
    }
    
    public static int[] euclideanGCD(int a, int b) {
        int steps = 0;
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
            steps++;
        }
        
        int[] gcdAndSteps = { a, steps };
        return gcdAndSteps;
    }
}
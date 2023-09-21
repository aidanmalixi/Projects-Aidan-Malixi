import java.util.Scanner;
public class PalindromeDemo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.print("Input a String for Palindrome Test: ");
            String input = scanner.nextLine();

            String test = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

            if (isPalindrome(test)) {
                System.out.println("The string \"" + input + "\" is a palindrome.");
            } else {
                System.out.println("The string \"" + input + "\" is not a palindrome.");
            }

            System.out.print("Do you want to test another string? (y/n): ");
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("y")) {
                break;
            }
        } while (true);
    }

    public static boolean isPalindrome(String str) {
        LinkedStackDS<Character> stack = new LinkedStackDS<>();
        int length = str.length();
        int mid = length / 2;

        for (int i = 0; i < mid; i++) {
            stack.push(str.charAt(i));
        }

        int startIndex;
        if (length % 2 == 0) {
            startIndex = mid;
        } else {
            startIndex = mid + 1;
        }

        for (int i = startIndex; i < length; i++) {
            char ch = str.charAt(i);
            if (ch != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}
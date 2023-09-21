import java.util.Scanner;

public class LockClientDemoClass {
    public static void main(String[] args) {
        LockADT lock = new LockDataStructureClass();

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 6) {
            displayMenu();
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    setLockCombination(lock, scanner);
                    break;
                case 2:
                    close(lock);
                    break;
                case 3:
                    checkStatus(lock);
                    break;
                case 4:
                    attempt(lock);
                    break;
                case 5:
                    checkCurrentNumber(lock);
                    break;
                case 6:
                    System.out.println("Program exited.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("Lock Menu:");
        System.out.println("1. Set lock combination");
        System.out.println("2. Close lock");
        System.out.println("3. Check lock status");
        System.out.println("4. Attempt to open lock");
        System.out.println("5. Check current number");
        System.out.println("6. Exit the program");
        System.out.print("Select a number: ");
    }

    public static void setLockCombination(LockADT lock, Scanner scanner) {
        System.out.print("Enter the first number: ");
        int x = scanner.nextInt();

        System.out.print("Enter the second number: ");
        int y = scanner.nextInt();

        System.out.print("Enter the third number: ");
        int z = scanner.nextInt();

        lock.set(x, y, z);
        System.out.println("Lock combination set.");
    }

    public static void close(LockADT lock) {
        lock.close();
    }

    public static void checkStatus(LockADT lock) {
        if (lock.locked()) {
            System.out.println("Lock is open.");
        } else {
            System.out.println("Lock is closed.");
        }
    }

    public static void attempt(LockADT lock) {
        lock.attempt();
    }

    public static void checkCurrentNumber(LockADT lock) {
        int currentNumber = lock.getCurrent();
        System.out.println("The current number at the top is: " + currentNumber);
    }
}
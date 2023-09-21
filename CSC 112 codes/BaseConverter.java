import java.util.Scanner;
public class BaseConverter {
    private static final Scanner scanner = new Scanner(System.in);

    private class BaseNumber {
        private long number;
        private int base;

        public BaseNumber() {
            number = 0;
            base = 0;
        }

        public BaseNumber(long number, int base) {
            this.number = number;
            this.base = base;
        }

        public long getNumber() {
            return number;
        }

        public int getBase() {
            return base;
        }

        public void setNumber(long number) {
            this.number = number;
        }

        public void setBase(int base) {
            this.base = base;
        }
    }

    public BaseConverter() {
    }

    public void inputPrompt() {
        System.out.print("Enter number 1: ");
        long number1 = scanner.nextLong();
        System.out.print("Enter base for number 1 (2-9): ");
        int base1 = scanner.nextInt();

        System.out.print("Enter number 2: ");
        long number2 = scanner.nextLong();
        System.out.print("Enter base for number 2 (2-9): ");
        int base2 = scanner.nextInt();

        System.out.print("Enter number 3: ");
        long number3 = scanner.nextLong();
        System.out.print("Enter base for number 3 (2-9): ");
        int base3 = scanner.nextInt();

        BaseNumber baseNumber1 = new BaseNumber(number1, base1);
        BaseNumber baseNumber2 = new BaseNumber(number2, base2);
        BaseNumber baseNumber3 = new BaseNumber(number3, base3);

        convertAll(baseNumber1, baseNumber2, baseNumber3);
    }

    public String convert(BaseNumber baseNumber) {
        long number = baseNumber.getNumber();
        int base = baseNumber.getBase();

        ListStackDataStrucClass<Long> stack = new ListStackDataStrucClass<>();
        while (number > 0) {
            stack.push(number % base);
            number /= base;
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public void convertAll(BaseNumber baseNumber1, BaseNumber baseNumber2, BaseNumber baseNumber3) {
        String result1 = convert(baseNumber1);
        String result2 = convert(baseNumber2);
        String result3 = convert(baseNumber3);

        System.out.println("Converted numbers:");
        System.out.println("Number 1: " + result1);
        System.out.println("Number 2: " + result2);
        System.out.println("Number 3: " + result3);
    }

    public void processAndPrint() {
        inputPrompt();
    }
}
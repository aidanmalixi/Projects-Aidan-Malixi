public class PrimeFactorizationDemoClass {
    public static void main(String[] args) {
        try {
            ArrayStackDataStrucClass<Integer> stack = new ArrayStackDataStrucClass<>(50);

            int n1 = 3960;
            primeFactorization(stack, n1);
            System.out.println("Factorization of " + n1 + ": " + getFactorizationString(stack));

            int n2 = 1234;
            primeFactorization(stack, n2);
            System.out.println("Factorization of " + n2 + ": " + getFactorizationString(stack));

            int n3 = 222222;
            primeFactorization(stack, n3);
            System.out.println("Factorization of " + n3 + ": " + getFactorizationString(stack));

            int n4 = 13780;
            primeFactorization(stack, n4);
            System.out.println("Factorization of " + n4 + ": " + getFactorizationString(stack));
        } catch (StackUnderflowException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void primeFactorization(ArrayStackDataStrucClass<Integer> stack, int n) throws StackUnderflowException {
        stack.initializeStack();

        // Find prime factors
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                stack.push(i);
                n /= i;
            }
        }
    }

    public static String getFactorizationString(ArrayStackDataStrucClass<Integer> stack) throws StackUnderflowException {
        StringBuilder string = new StringBuilder();
        ArrayStackDataStrucClass<Integer> stack2 = new ArrayStackDataStrucClass<>(stack);

        string.append(stack2.peek());
        stack2.pop();

        while (!stack2.isEmptyStack()) {
            string.append(" * ").append(stack2.peek());
            stack2.pop();
        }

        return string.toString();
    }
}




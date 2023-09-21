public class ArrayStackDataStrucClass<T> implements ArrayStackADT<T> {
    private final int MAX_SIZE = 50;
    private int n;
    private T[] stack;

    public ArrayStackDataStrucClass() {
        n = 0;
        stack = (T[]) new Object[MAX_SIZE];
    }

    public ArrayStackDataStrucClass(int size) {
        n = 0;
        stack = (T[]) new Object[size];
    }

    public ArrayStackDataStrucClass(ArrayStackDataStrucClass<T> otherStack) {
        n = otherStack.n;
        stack = (T[]) new Object[otherStack.stack.length];

        for (int i = 0; i < n; i++) {
            stack[i] = otherStack.stack[i];
        }
    }

    public void initializeStack() {
        n = 0;
    }

    public boolean isEmptyStack() {
        return (n == 0);
    }

    public boolean isFullStack() {
        return (n == MAX_SIZE);
    }

    public void push(T newItem) {
        if (!isFullStack()) {
            stack[n] = newItem;
            n++;
        } else {
            throw new StackOverflowException();
        }
    }

    public T peek() throws StackUnderflowException {
        if (!isEmptyStack()) {
            return stack[n - 1];
        } else {
            throw new StackUnderflowException();
        }
    }

    public void pop() throws StackUnderflowException {
        if (!isEmptyStack()) {
            n--;
            stack[n] = null;
        } else {
            throw new StackUnderflowException();
        }
    }
}
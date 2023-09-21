public interface ArrayStackADT<T> {
    void initializeStack();
    boolean isEmptyStack();
    boolean isFullStack();
    void push(T newItem);
    T peek() throws StackUnderflowException;
    void pop() throws StackUnderflowException;
}
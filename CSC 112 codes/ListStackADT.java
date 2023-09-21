public interface ListStackADT<T> {
    T getTop();
    void setTop(T item);
    boolean isEmpty();
    void push(T item);
    T peek();
    T pop();
    String toString();
}

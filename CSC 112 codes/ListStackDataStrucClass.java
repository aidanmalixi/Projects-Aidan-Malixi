class ListStackDataStrucClass<T> implements ListStackADT<T> {
    private StackNode<T> top;
    private int size;

    public ListStackDataStrucClass() {
        top = null;
        size = 0;
    }

    public ListStackDataStrucClass(ListStackDataStrucClass<T> otherStack) {
        if (otherStack.top == null) {
            top = null;
        } else {
            StackNode<T> tempNode = otherStack.top;
            StackNode<T> newNode = new StackNode<>(tempNode.getValue());
            top = newNode;
            StackNode<T> newTempNode = top;

            while (tempNode.getNext() != null) {
                tempNode = tempNode.getNext();
                newNode = new StackNode<>(tempNode.getValue());
                newTempNode.setNext(newNode);
                newTempNode = newTempNode.getNext();
            }
        }

        size = otherStack.size;
    }

    public T getTop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getValue();
    }

    public void setTop(T value) {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        top.setValue(value);
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        StackNode<T> newNode = new StackNode<>(value);
        newNode.setNext(top);
        top = newNode;
        size++;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getValue();
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T topValue = top.getValue();
        top = top.getNext();
        size--;
        return topValue;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StackNode<T> current = top;

        while (current != null) {
            sb.append(current.getValue()).append(" ");
            current = current.getNext();
        }

        return sb.toString().trim();
    }

    private static class StackNode<T> {
        private T value;
        private StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
            next = null;
        }

        public StackNode(T value, StackNode<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public StackNode<T> getNext() {
            return next;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setNext(StackNode<T> next) {
            this.next = next;
        }
    }
}


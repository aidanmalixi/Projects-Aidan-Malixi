public class LinkedStackDS<T> implements LinkedtStackADT<T> {
    private StackNode<T> top;

    public LinkedStackDS() {
        top = null;
    }

    public LinkedStackDS(LinkedStackDS<T> otherStack) {
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
    }

    public boolean isEmptyStack() {
        return top == null;
    }

    public void push(T value) {
        StackNode<T> newNode = new StackNode<>(value);
        newNode.setNext(top);
        top = newNode;
    }

    public T peek() {
        if (isEmptyStack()) {
            throw new StackUnderflowException();
        }
        return top.getValue();
    }

    public T pop() {
        if (isEmptyStack()) {
            throw new StackUnderflowException();
        }
        T topValue = top.getValue();
        top = top.getNext();
        return topValue;
    }

    private static class StackNode<T> {
        private T value;
        private StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
            next = null;
        }

        public T getValue() {
            return value;
        }

        public StackNode<T> getNext() {
            return next;
        }

        public void setNext(StackNode<T> next) {
            this.next = next;
        }
    }
}
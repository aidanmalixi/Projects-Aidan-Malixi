class PolyNodeClass {
    private int coefficient;
    private int exponent;
    private PolyNodeClass next;

    public PolyNodeClass() {
        coefficient = 0;
        exponent = 0;
        next = null;
    }

    public PolyNodeClass(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        next = null;
    }

    public PolyNodeClass(PolyNodeClass node) {
        this.coefficient = node.coefficient;
        this.exponent = node.exponent;
        this.next = null;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public int getExponent() {
        return exponent;
    }

    public void setNext(PolyNodeClass next) {
        this.next = next;
    }

    public PolyNodeClass getNext() {
        return next;
    }
}
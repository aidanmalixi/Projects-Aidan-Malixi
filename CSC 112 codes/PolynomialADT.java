public interface PolynomialADT {
    void addPolyNodeFirst(int coefficient, int exponent);
    void addPolyNodeLast(int coefficient, int exponent);
    void addPolyNode(int coefficient, int exponent);
    void addPolynomials(PolynomialDataStrucClass p);
    String toString();
}
public class PolynomialDemoClass {
    public static void main(String[] args) {
        // Instantiate and initialize PolynomialDataStrucClass objects
        PolynomialDataStrucClass p1 = new PolynomialDataStrucClass();
        PolynomialDataStrucClass p2 = new PolynomialDataStrucClass();
        PolynomialDataStrucClass p3 = new PolynomialDataStrucClass();
        PolynomialDataStrucClass p4 = new PolynomialDataStrucClass();

        // Add terms to the polynomials
        p1.addPolyNodeLast(4, 3);
        p1.addPolyNodeLast(3, 2);
        p1.addPolyNodeLast(-5, 0);

        p2.addPolyNodeLast(3, 5);
        p2.addPolyNodeLast(4, 4);
        p2.addPolyNodeLast(1, 3);
        p2.addPolyNodeLast(-4, 2);
        p2.addPolyNodeLast(4, 1);
        p2.addPolyNodeLast(2, 0);

        p3.addPolyNodeLast(-5, 0);
        p3.addPolyNodeLast(3, 2);
        p3.addPolyNodeLast(4, 3);

        p4.addPolyNodeLast(-4, 0);
        p4.addPolyNodeLast(4, 3);
        p4.addPolyNodeLast(5, 4);

        // Print out p1, p2, and the sum of the polynomials
        System.out.println("p1 = " + p1.toString());
        System.out.println("p2 = " + p2.toString());
        p1.addPolynomials(p2);
        System.out.println("Sum of p1 and p2 = " + p1.toString());

        // Print out p3, p4, and the sum of the polynomials
        System.out.println("p3 = " + p3.toString());
        System.out.println("p4 = " + p4.toString());
        p3.addPolynomials(p4);
        System.out.println("Sum of p3 and p4 = " + p3.toString());
    }
}
class PolynomialDataStrucClass implements PolynomialADT {
    private PolyNodeClass firstNode;

    public PolynomialDataStrucClass() {
        firstNode = null;
    }

    public PolynomialDataStrucClass(PolynomialDataStrucClass p) {
        if (p.firstNode != null) {
            PolyNodeClass current = p.firstNode;
            firstNode = new PolyNodeClass(current);
            PolyNodeClass newNode = firstNode;

            while (current.getNext() != null) {
                current = current.getNext();
                newNode.setNext(new PolyNodeClass(current));
                newNode = newNode.getNext();
            }
        }
    }

    public boolean isEmpty() {
        return (firstNode == null);
    }

    public void setFirstNode(PolyNodeClass node) {
        firstNode = node;
    }

    public PolyNodeClass getFirstNode() {
        return firstNode;
    }

    public void addPolyNodeFirst(int coefficient, int exponent) {
        PolyNodeClass newNode = new PolyNodeClass(coefficient, exponent);
        newNode.setNext(firstNode);
        firstNode = newNode;
    }

    public void addPolyNodeLast(int coefficient, int exponent) {
        PolyNodeClass newNode = new PolyNodeClass(coefficient, exponent);

        if (isEmpty()) {
            firstNode = newNode;
        } else {
            PolyNodeClass current = firstNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void addPolyNode(int coefficient, int exponent) {
        if (isEmpty() || exponent > firstNode.getExponent()) {
            addPolyNodeFirst(coefficient, exponent);
        } else if (exponent == firstNode.getExponent()) {
            int newCoefficient = coefficient + firstNode.getCoefficient();
            if (newCoefficient != 0) {
                firstNode.setCoefficient(newCoefficient);
            } else {
                firstNode = firstNode.getNext();
            }
        } else {
            PolyNodeClass current = firstNode;
            PolyNodeClass previous = null;

            while (current != null && exponent < current.getExponent()) {
                previous = current;
                current = current.getNext();
            }

            if (current == null || exponent > current.getExponent()) {
                PolyNodeClass newNode = new PolyNodeClass(coefficient, exponent);
                newNode.setNext(current);
                previous.setNext(newNode);
            } else {
                int newCoefficient = coefficient + current.getCoefficient();
                if (newCoefficient != 0) {
                    current.setCoefficient(newCoefficient);
                } else {
                    previous.setNext(current.getNext());
                }
            }
        }
    }

    public void addPolynomials(PolynomialDataStrucClass p) {
        PolyNodeClass current = p.getFirstNode();
        while (current != null) {
            addPolyNode(current.getCoefficient(), current.getExponent());
            current = current.getNext();
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        PolyNodeClass current = firstNode;

        while (current != null) {
            int coefficient = current.getCoefficient();
            int exponent = current.getExponent();

            if (coefficient != 0) {
                if (coefficient > 0 && result.length() > 0) {
                    result.append(" + ");
                } else if (coefficient < 0) {
                    result.append(" - ");
                }

                if (Math.abs(coefficient) != 1 || exponent == 0) {
                    result.append(Math.abs(coefficient));
                }

                if (exponent > 0) {
                    result.append("x");
                    if (exponent > 1) {
                        result.append("^").append(exponent);
                    }
                }
            }

            current = current.getNext();
        }

        return result.toString();
    }
}

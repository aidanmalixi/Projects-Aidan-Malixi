public class LockDataStructureClass implements LockADT {
    private int x, y, z;
    private int current;
    private boolean isLocked;

    public LockDataStructureClass() {
        this(0, 0, 0);
    }

    public LockDataStructureClass(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.current = 0;
        this.isLocked = true;
    }

    public LockDataStructureClass(LockDataStructureClass other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.current = other.current;
        this.isLocked = other.isLocked;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void set(int x, int y, int z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void turn(char direction, int target) {
        System.out.println("Turn the lock knob " + direction + " until " + target + " is on top:");

        int increment = (direction == 'c') ? 1 : -1;
        int current = getCurrent();

        while (current != target) {
            current = (current + increment + 40) % 40;
            System.out.print(current + " ");
        }

        current = target;
        System.out.println();
    }

    public void close() {
        isLocked = true;
        System.out.println("Lock closed.");
    }

    public void attempt() {
        if (!isLocked && current == z) {
            System.out.println("Atempt successful, Lock: Open");
        } else {
            System.out.println("Attempt unsuccessful, Lock: Closed");
        }
    }

    public boolean locked() {
        return !isLocked;
    }

    public int getCurrent() {
        return current;
    }

    public String toString() {
        return "Lock combination: (" + x + ", " + y + ", " + z + ")";
    }
}
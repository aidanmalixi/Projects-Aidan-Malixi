	public class Lock {
    private int x, y, z;
    private int number;
    private boolean open;

    public Lock() {
        this(0, 0, 0);
    }

    public Lock(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.number = 0;
        this.open = false;
    }

    public void setCombination(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void turnKnob(char direction, int target) {
        System.out.println("Turn the knob " + direction + " until " + target + " is on top:");
        
        if (direction == 'R') {  // Clockwise
            while (number != target) {
                number = (number + 1) % 40;
                System.out.print(number + " ");
            }
        } else if (direction == 'L') {  // Anti-clockwise
            while (number != target) {
                number = (number - 1 + 40) % 40;
                System.out.print(number + " ");
            }
        }
        
        System.out.println();
    }

    public void closeLock() {
        open = false;
        System.out.println("Lock: Closed.");
    }

    public void attemptToOpen() {
        if (number == z && open) {
            System.out.println("Attempt Successful. Lock: Opened.");
        } else {
            System.out.println("Attempt Failed. Lock: Closed.");
        }
    }

    public boolean isLockOpen() {
        return open;
    }

    public int getNumber() {
        return number;
    }

    public static void main(String[] args) {
        Lock lock = new Lock();

        lock.setCombination(1, 2, 3);
        lock.turnKnob('R', 10);
        lock.turnKnob('L', 2);
        lock.turnKnob('R', 8);
        lock.closeLock();
        lock.attemptToOpen();
        System.out.println("Lock status: " + (lock.isLockOpen() ? "Open" : "Closed"));
        System.out.println("Current number at the top: " + lock.getNumber());
    }
}
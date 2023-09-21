public interface LockADT {
    void set(int x, int y, int z);
    void turn(char direction, int target);
    void close();
    void attempt();
    boolean locked();
    int getCurrent();
}
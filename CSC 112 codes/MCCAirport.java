import java.util.Random;

class ArrayQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int count;

    public ArrayQueue() {
        final int QUEUE_SIZE = 100;
        queue = (T[]) new Object[QUEUE_SIZE];
        front = 0;
        rear = -1;
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int count() {
        return count;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T item = queue[front];
        front = (front + 1) % queue.length;
        count--;
        return item;
    }

    public void enqueue(T item) {
        if (count == queue.length) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        count++;
    }
}

public class MCCAirport {
    private final double LANDING_TIME = 3;
    private final double TAKE_OFF_TIME = 2;
    private final double LANDING_RATE = 10;
    private final double TAKE_OFF_RATE = 10;
    private final int ITERATIONS = 1440;
    private Random generator;

    public MCCAirport() {
        generator = new Random(System.currentTimeMillis());
    }

    public void simulate() {
        ArrayQueue<Double> landingQueue = new ArrayQueue<>();
        ArrayQueue<Double> takeoffQueue = new ArrayQueue<>();
        double totalLandingQueueLength = 0;
        double totalTakeoffQueueLength = 0;
        double totalLandingQueueTime = 0;
        double totalTakeoffQueueTime = 0;

        for (int minute = 0; minute < ITERATIONS; minute++) {
            double landingArrival = generator.nextDouble();
            double takeoffArrival = generator.nextDouble();

            if (landingArrival < LANDING_RATE / 60) {
                landingQueue.enqueue((double) minute);
            }

            if (takeoffArrival < TAKE_OFF_RATE / 60) {
                takeoffQueue.enqueue((double) minute);
            }

            if (!landingQueue.isEmpty() && landingQueue.peek() <= minute - LANDING_TIME) {
                double landingStartTime = landingQueue.dequeue();
                double landingQueueLength = landingQueue.count();
                double landingQueueTime = minute - landingStartTime;
                totalLandingQueueLength += landingQueueLength;
                totalLandingQueueTime += landingQueueTime;
            } else if (!takeoffQueue.isEmpty() && takeoffQueue.peek() <= minute - TAKE_OFF_TIME) {
                double takeoffStartTime = takeoffQueue.dequeue();
                double takeoffQueueLength = takeoffQueue.count();
                double takeoffQueueTime = minute - takeoffStartTime;
                totalTakeoffQueueLength += takeoffQueueLength;
                totalTakeoffQueueTime += takeoffQueueTime;
            }
        }

        double averageLandingQueueLength = totalLandingQueueLength / ITERATIONS;
        double averageTakeoffQueueLength = totalTakeoffQueueLength / ITERATIONS;
        double averageLandingQueueTime = totalLandingQueueTime / (ITERATIONS - landingQueue.count());
        double averageTakeoffQueueTime = totalTakeoffQueueTime / (ITERATIONS - takeoffQueue.count());

        System.out.printf("Average landing queue length: %.6f%n", averageLandingQueueLength);
        System.out.printf("Average takeoff queue length: %.6f%n", averageTakeoffQueueLength);
        System.out.printf("Average landing queue time: %.6f%n", averageLandingQueueTime);
        System.out.printf("Average takeoff queue time: %.6f%n", averageTakeoffQueueTime);
    }
}


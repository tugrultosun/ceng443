/**
 * class which will implement barrier method for synchronization of multiple threads
 */
public class Barrier {
    private int numberOfThreadsCurrentlyWaiting;
    private int numberOfThreadsToReachBarrierPoint;

    public Barrier(int numberOfThreadsToReachBarrierPoint) {
        this.numberOfThreadsToReachBarrierPoint=numberOfThreadsToReachBarrierPoint;
        numberOfThreadsCurrentlyWaiting=0;
    }


    /**
     * @return number of threads which needs to reach barrier point
     */
    public int getNumberOfThreadsToReachBarrierPoint() {
        return numberOfThreadsToReachBarrierPoint;
    }

    /**
     * @param numberOfThreadsCurrentlyWaiting sets number of waiting threads
     */
    public void setNumberOfThreadsCurrentlyWaiting(int numberOfThreadsCurrentlyWaiting) {
        this.numberOfThreadsCurrentlyWaiting = numberOfThreadsCurrentlyWaiting;
    }

    /**
     * @return true if waiting number of threads are equal to number of threads to reach barrier otherwise false
     */
    public boolean hasreachedbarrier(){
        return numberOfThreadsCurrentlyWaiting==numberOfThreadsToReachBarrierPoint;
    }

    /**
     * @param numberOfThreadsToReachBarrierPoint sets number of threads to reach to barrier
     */
    public synchronized void setNumberOfThreadsToReachBarrierPoint(int numberOfThreadsToReachBarrierPoint) {
        this.numberOfThreadsToReachBarrierPoint = numberOfThreadsToReachBarrierPoint;
    }

    /**
     * notifies all threads waiting at this barrier
     */
    public synchronized void awakeallthreads(){
        notifyAll();
    }

    /**
     * Increases number of waiting threads by one at this barrier then waits to be notified
     */
    public synchronized void await( /* ... */) {
        try {
            numberOfThreadsCurrentlyWaiting++;
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}



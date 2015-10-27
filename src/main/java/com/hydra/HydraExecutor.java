package com.hydra;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HydraExecutor {

    private static final int THREAD_POOL = 5;
    private ExecutorService executor;

    /**
     * Creates a new HydraExecutor with a fixed 5 thread pool
     */
    public HydraExecutor() {
        executor = Executors.newFixedThreadPool(THREAD_POOL);
    }

    /**
     *
     * @param runnable the runnable to be executed
     * @throws HydraException in case the executor has already been closed
     */
    public void dispatch(Runnable runnable) throws HydraException {
        if (!executor.isShutdown()) executor.execute(runnable);
        else throw new HydraException("Executor is closed");
    }

    /**
     * Shuts down the executor after every task is finished if it has not been already closed
     */
    public void shutdown() {
        if (!executor.isShutdown()) executor.shutdown();
    }

    /**
     * Shuts down the executor if it has not been already closed
     * @return a list containing all the tasks that have not been executed
     */
    public List<Runnable> shutdownNow() {
        if (!executor.isShutdown()) return executor.shutdownNow();
        return null;
    }

    /**
     * Returns {@code true} if all tasks have completed following shut down.
     * Note that {@code isTerminated} is never {@code true} unless
     * either {@code shutdown} or {@code shutdownNow} was called first.
     * @return {@code true} if all tasks have completed following shut down
     * @throws HydraException if the executor has not been shut down before
     */
    public boolean awaitUntilTermination() throws HydraException {
        if (!executor.isShutdown()) throw new HydraException("Executor has not been shut down");
        try {
            while (!executor.isTerminated()) {
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns {@code true} if all tasks have completed following shut down.
     * @param timeout the maximum time to wait in seconds
     * @return {@code true} if this executor terminated and
     *         {@code false} if the timeout elapsed before termination
     * @throws InterruptedException if interrupted while waiting
     * @throws HydraException if the executor has not been shut down before
     */
    public boolean awaitTermination(long timeout) throws HydraException {
        if (!executor.isShutdown()) throw new HydraException("Executor has not been shut down");
        try {
           return executor.awaitTermination(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}

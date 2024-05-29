package terminalUtils;

public class ProgressBar {


    private volatile int progress;
    private volatile boolean sortingComplete;
    private final int totalSteps;

    public ProgressBar() {
        this.progress = 0;
        this.sortingComplete = false;
        this.totalSteps = 100;
    }

    public synchronized void updateProgress(int currentStep, int totalSteps) {
        this.progress = (currentStep * this.totalSteps) / totalSteps;
    }

    public int getProgress() {
        return progress;
    }

    public synchronized  void setProgress(int progress) {
        this.progress = progress;
    }

    public synchronized  boolean isSortingComplete() {
        return sortingComplete;
    }

    public synchronized void setSortingComplete(boolean sortingComplete) {
        this.sortingComplete = sortingComplete;
    }

    public synchronized int getTotalSteps() {
        return totalSteps;
    }
}

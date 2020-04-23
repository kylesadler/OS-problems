public class Semaphore{

    private int available;
    private int size;

    public Semaphore(int size_, int available_){
        this.size = size_;
        this.available = available_;
    }

    public synchronized void wait_() throws InterruptedException{ // waits for the next resource
        while(this.available <= 0){
            Thread.sleep(1);
        }
        this.available--;
    }

    public void signal(){ // releases resource
        if(this.available < size){
            this.available++;
        }
    }
}
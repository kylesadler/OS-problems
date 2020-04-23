import java.util.Random;

class Producer extends OSThread{ 
    
    public Producer(Buffer buffer_, Semaphore empty_, Semaphore full_, Mutex mutex_){
        super(buffer_, empty_, full_, mutex_);
    }

    public void work() throws InterruptedException{ 
        empty.wait_();
        mutex.acquire();
        int item = r.nextInt();
        buffer.insert(item);
        System.out.println("Producer produced " + item);
        mutex.release();
        full.signal();
    } 
} 

class Consumer extends OSThread{ 
    
    public Consumer(Buffer buffer_, Semaphore empty_, Semaphore full_, Mutex mutex_){
        super(buffer_, empty_, full_, mutex_);
    }
    
    public void work() throws InterruptedException{
        full.wait_();
        mutex.acquire();
        System.out.println("\tConsumer consumed " + buffer.remove());
        mutex.release();
        empty.signal();
    } 
} 


class OSThread implements Runnable{
    protected Random r;
    protected Buffer buffer;
    protected Semaphore full;
    protected Semaphore empty;
    protected Mutex mutex;

    public OSThread(Buffer buffer_, Semaphore empty_, Semaphore full_, Mutex mutex_){
        this.buffer = buffer_;
        this.empty = empty_;
        this.full = full_;
        this.mutex = mutex_;
        this.r = new Random();
    }

    public void run(){ 
        try{ 
            for(int i = 0; i < 100; i++){
                Thread.sleep(r.nextInt(501));
                work();
            }
            
        } catch (Exception e){}
    }

    public void work() throws InterruptedException{

    }
}
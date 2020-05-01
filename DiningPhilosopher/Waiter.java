/* 
    Name: Kyle Sadler
    ID:   010808898
*/

public class Waiter {
    
    private boolean[] chopsticks;  // true if available
    private int size;

    public Waiter(int n){
        this.size = n;
        this.chopsticks = new boolean[n];
        
        for(int i = 0; i < this.size; i++){
            this.chopsticks[i] = true;
        }
    }

    public synchronized void pickUp(int id) throws InterruptedException{
        // philosopher with given id makes a request to pick up both chopsticks 
        int idx = (id+1) % this.size;
        while(!this.chopsticks[id] || !this.chopsticks[idx]){
            Thread.sleep(1);
        }
        this.chopsticks[id] = false;
        this.chopsticks[idx] = false;
    }

    public void putDown(int id){
        this.chopsticks[id] = true;
        this.chopsticks[(id+1) % this.size] = true;
    }

}
import java.util.Random;

class Philosopher implements Runnable{ 
    private Random r;
    private Waiter waiter;
    private int id;

    public Philosopher(int id_, Waiter waiter_){
        this.id = id_;
        this.waiter = waiter_;
        this.r = new Random();
    }

    public void run(){ 
        try{ 
            for(int i = 0; i < 100; i++){

                System.out.println("Philosopher " + (this.id + 1) + " Thinking");
                Thread.sleep(r.nextInt(1001));

                if(r.nextInt(2) == 0){ // eats half of the time
                    waiter.pickUp(this.id);
                    System.out.println("Philosopher " + (this.id + 1) + " Picked up left chopstick");
                    System.out.println("Philosopher " + (this.id + 1) + " Picked up right chopstick");

                    System.out.println("Philosopher " + (this.id + 1) + " Eating");
                    Thread.sleep(r.nextInt(1501));

                    System.out.println("Philosopher " + (this.id + 1) + " Put down left chopstick");
                    System.out.println("Philosopher " + (this.id + 1) + " Put down right chopstick");
                    waiter.putDown(this.id);
                }
            }
            
        } catch (Exception e){}
    }
} 
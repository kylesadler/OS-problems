/* 
    Name: Kyle Sadler
    ID:   010808898
*/

public class ProducerConsumer {
    
    public static void main(String args[]) throws InterruptedException{
        Buffer buffer = new Buffer(5);

        int seconds = 0;
        int producers = 0;
        int consumers = 0;

        Semaphore empty = new Semaphore(5, 5);
        Semaphore full = new Semaphore(5, 0);
        Mutex mutex = new Mutex();

        if(args.length != 3){
            exitError("Incorrect number of arguments.");
        }

        try{
            seconds = Integer.parseInt(args[0]);
            producers = Integer.parseInt(args[1]);
            consumers = Integer.parseInt(args[2]);
        } catch(Exception e){
            exitError("Arguments must be integers.");
        }

        print("Using arguments from command line");
        print("Sleep time = " + seconds);
        print("Producer threads = " + producers);
        print("Consumer threads = " + consumers);
        print("");

        // make producers
        for (int i = 0; i < producers; i++){ 
            Thread prod = new Thread(new Producer(buffer, empty, full, mutex)); 
            prod.start(); 
        } 

        // make consumers
        for (int i = 0; i < consumers; i++){ 
            Thread consumer = new Thread(new Consumer(buffer, empty, full, mutex)); 
            consumer.start(); 
        } 

        Thread.sleep(seconds*1000);
        System.exit(0);

    }

    private static void exitError(String err){
        print(err + "\n"+
        "Usage: java ProducerConsumer runTime numProducers numConsumers\n"+
        "\t@input sleepTime time for the program to run (in seconds)\n"+
        "\t@input numProducers number of producer threads to create\n"+
        "\t@input numConsumers number of consumer threads to create");
        System.exit(1);
    }

    private static void print(String toPrint){
        System.out.println(toPrint);
    }

}
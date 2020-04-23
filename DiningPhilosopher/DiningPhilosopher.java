/* 
    Name: Kyle Sadler
    ID:   010808898
*/

class DiningPhilosopher{
    public static void main(String args[]) throws InterruptedException{
        int numPhilosophers = 5;
        int seconds = 0;
        
        if(args.length != 1){
            exitError("Incorrect number of arguments.");
        }

        try{
            seconds = Integer.parseInt(args[0]);
        } catch(Exception e){
            exitError("Arguments must be integers.");
        }

        print("Using arguments from command line");
        print("Run time = " + seconds);
        print("");

        Waiter waiter = new Waiter(numPhilosophers);

        for(int id = 0; id < 5; id++){ 
            Thread philosopher = new Thread(new Philosopher(id, waiter)); 
            philosopher.start(); 
        } 

        Thread.sleep(seconds*1000);
        System.exit(0);
    }

    private static void exitError(String err){
        print(err + "\n"+
        "Usage: java DiningPhilosopher runTime\n"+
        "\t@input runTime time for the program to run (in seconds)\n");
        System.exit(1);
    }

    private static void print(String toPrint){
        System.out.println(toPrint);
    }
}
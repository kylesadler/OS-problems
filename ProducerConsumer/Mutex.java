public class Mutex {
    
    private static boolean available = true; // resource current availability

    public void acquire(){
        while(!available){}
        available = false;
    }

    public void release(){
        available = true;
    }
}
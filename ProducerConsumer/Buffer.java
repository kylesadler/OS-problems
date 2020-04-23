public class Buffer {
    
    private int size;
    private int[] buffer;
    private int start;
    private int end;
    
    public Buffer(int n){
        size = n;
        buffer = new int[n];
        start = 0;
        end = 0;
    }

    public void insert(int n){
        buffer[start] = n;
        start = (start + 1) % size;
    }

    public int remove(){
        int item = buffer[end];
        end = (end + 1) % size;
        return item;
    }
}
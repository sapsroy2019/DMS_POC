import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Poller {

    static final int MAX_T = 2;

    public static void polling(MinHeap heap) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        while(true){

            System.out.println("Inside poller true");
            DataAsset obj = (DataAsset) HeapAccess.access("RETRIEVE_MIN", heap, null);
            if(obj !=null) {
                Task t = new Task(obj, heap);
                pool.execute(t);
            }else{
                Thread.sleep(420000);
            }
        }

    }
}

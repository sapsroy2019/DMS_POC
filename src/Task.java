import java.util.Date;

public class Task implements Runnable {

  private DataAsset ds ;
  private MinHeap heap;

  public Task(DataAsset ds,MinHeap heap){
      this.ds = ds;
      this.heap = heap;
  }

  @Override
    public void run(){

      //System.out.println(ds);

      try {
          Thread.sleep(5000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      ds.setLastPolledTime(new Date());

      HeapAccess.access("ADD", heap, ds);
  }
}

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

      boolean flagToAdd = true;
      ds.setLastPolledTime(new Date());

      if(CommonStore.updateMap.containsKey(ds.getAsset())){
          ds = CommonStore.updateMap.get(ds.getAsset());
          CommonStore.updateMap.remove(ds.getAsset());
      }

      if(CommonStore.deleteMap.containsKey(ds.getAsset())){
          CommonStore.deleteMap.remove(ds.getAsset());
          flagToAdd = false;
      }

      if(flagToAdd) {
          HeapAccess.access("ADD", heap, ds);
      }
  }
}

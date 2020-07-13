
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class Driver {


    public static void main(String[]args) throws InterruptedException {

            ArrayList<DataAsset> list = new ArrayList<DataAsset>();
            Date dt = new Date();
            list.add(new DataAsset("asset1",60,dt));
            list.add(new DataAsset("asset2",300,dt));
            list.add(new DataAsset("asset3",20,dt));
            list.add(new DataAsset("asset4",300,dt));
            list.add(new DataAsset("asset5",10,dt));

            MinHeap heap = new MinHeap(list);

          /*  while(true){

                  Scanner sc = new Scanner(System.in);
                  System.out.println("Retrieve min ??");
                  sc.nextLine();
                  System.out.println("Min element retrieved ..." + obj.extractMin().getAsset());
            } */



          Thread tAdd = new Thread(){
              public void run(){
                  while(true){
                      Date dt = new Date();
                      HeapAccess.access("ADD",heap,new DataAsset("asset"+dt.getTime(),200,dt));
                      try {
                          Thread.sleep(300000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          };

          tAdd.start();

          Poller.polling(heap);
          tAdd.join();
    }
}

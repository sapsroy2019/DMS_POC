
import java.util.ArrayList;
import java.util.Date;

import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Driver {


    public static void main(String[]args) throws InterruptedException {

            ArrayList<DataAsset> list = new ArrayList<DataAsset>();
            Date dt = new Date();
            list.add(new DataAsset("asset1",60,dt));
            list.add(new DataAsset("asset2",300,dt));
            list.add(new DataAsset("asset3",20,dt));
            list.add(new DataAsset("asset4",300,dt));
            list.add(new DataAsset("asset5",10,dt));



            list.forEach(item -> {
                CommonStore.inputMap.put(item.getAsset(),item);
            });

         //CommonStore.inputMap.keySet().forEach(k->{ System.out.println("key-->"+k);});

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
                      DataAsset newItem = new DataAsset("asset"+dt.getTime(),200,dt);
                      if(!CommonStore.inputMap.containsKey(newItem.getAsset())) {
                          System.out.println("Add api called....");
                          HeapAccess.access("ADD", heap, newItem);
                          CommonStore.inputMap.put(newItem.getAsset(),newItem);
                      }else{
                          System.out.println("The requested element already exists...");
                      }
                      try {
                          Thread.sleep(300000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          };


          tAdd.start();

          Thread tUpdate = new Thread(){
            public void run(){
                while(true){

                    try {
                        Thread.sleep(480000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DataAsset updateItem = new DataAsset("asset2",350,dt);

                    if(CommonStore.inputMap.containsKey(updateItem.getAsset())){
                        System.out.println("Update api called....");
                        CommonStore.inputMap.put(updateItem.getAsset(),updateItem);
                        CommonStore.updateMap.put(updateItem.getAsset(),updateItem);
                    }else{
                        System.out.println("Asset not present to update...");
                    }
                }

            }
          };

          tUpdate.start();

        Thread tDelete = new Thread(){
            public void run(){
                while(true){

                    try {
                        Thread.sleep(720000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DataAsset deleteItem = new DataAsset("asset2",350,dt);

                    if(CommonStore.inputMap.containsKey(deleteItem.getAsset())){
                        System.out.println("Delete api called....");
                        CommonStore.inputMap.remove(deleteItem.getAsset());
                        CommonStore.deleteMap.put(deleteItem.getAsset(),deleteItem);
                    }else{
                        System.out.println("Asset not present to delete...");
                    }
                }

            }
        };

        tDelete.start();

          Poller.polling(heap);
          tAdd.join();
          tUpdate.join();
          tDelete.join();
    }
}

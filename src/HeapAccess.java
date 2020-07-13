import java.util.Date;

public class HeapAccess {

    public static synchronized Object access(String operation,MinHeap ref,Object obj){

        Object returnObj = null;

        switch(operation){
            case "RETRIEVE_MIN": returnObj =  ref.extractMin(); break;
            case "ADD": ref.add((DataAsset)obj);


        }

        return returnObj;
    }
}
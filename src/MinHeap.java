import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MinHeap {

    private ArrayList<DataAsset> list;

    public MinHeap() {
        this.list = new ArrayList<DataAsset>();
    }

    public MinHeap(ArrayList<DataAsset> items) {

        this.list = items;
        buildHeap();
    }

    public void buildHeap() {


        Date currentDate = new Date();
        System.out.println("Curr Date from build Heap -->"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate));
        for (int i = list.size() / 2; i >= 0; i--) {
            minHeapify(i,currentDate);
        }
    }

    public DataAsset extractMin() {

        Date currentDate = new Date();
        if (list.size() == 0) {

            //throw new IllegalStateException("MinHeap is EMPTY");
            System.out.println("The heap is empty !!!!");
            return null;
        } else if (list.size() == 1) {
            System.out.println("Curr Date from extract Min -->"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate)+"Asset:"+getMin());
            DataAsset min = list.remove(0);
            return min;
        }


        System.out.println("Curr Date from extract Min -->"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentDate)+"Asset:"+getMin());
        // remove the last item ,and set it as new root
        DataAsset min = list.get(0);
        DataAsset lastItem = list.remove(list.size() - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0,currentDate);

        // return min key
        return min;
    }
    private void minHeapify(int i,Date currDt) {

        int left = left(i);
        int right = right(i);
        int smallest = -1;

        // find the smallest key between current node and its children.
        if (left <= list.size() - 1 && list.get(left).getValue(currDt) < list.get(i).getValue(currDt)) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= list.size() - 1 && list.get(right).getValue(currDt) < list.get(smallest).getValue(currDt)) {
            smallest = right;
        }

        // if the smallest key is not the current key then bubble-down it.
        if (smallest != i) {

            swap(i, smallest);
            minHeapify(smallest,currDt);
        }
    }

    public void add(DataAsset item){

        this.list.add(item);
        System.out.println("Adding item to the heap -->"+item);
        Date currentDate = new Date();
        bubbleUp(this.list.size()-1,currentDate);

    }

    private void bubbleUp(int index,Date currDt){
        int parent = parent(index);
        while (index > 0 && list.get(parent).getValue(currDt) > list.get(index).getValue(currDt)) {

            swap(index, parent);
            index = parent;
            parent = parent(parent);
        }
    }

    public DataAsset getMin() {

        return list.get(0);
    }

    public boolean isEmpty() {

        return list.size() == 0;
    }

    private int right(int i) {

        return 2 * i + 2;
    }

    private int left(int i) {

        return 2 * i + 1;
    }

    private int parent(int i) {

        if (i % 2 == 1) {
            return i / 2;
        }

        return (i - 1) / 2;
    }

    private void swap(int i, int parent) {

        DataAsset temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }


}

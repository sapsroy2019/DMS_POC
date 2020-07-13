
import java.util.concurrent.ConcurrentHashMap;
public class CommonStore {

    public static ConcurrentHashMap<String,DataAsset> updateMap = new ConcurrentHashMap<String,DataAsset>();

    public static ConcurrentHashMap<String,DataAsset> inputMap = new ConcurrentHashMap<String,DataAsset>();

    public static ConcurrentHashMap<String,DataAsset> deleteMap = new ConcurrentHashMap<String,DataAsset>();
}

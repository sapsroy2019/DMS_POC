import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


public class DataAsset {

    private String dataAssetId;
    private int dataAssetCadence;
    private Date lastPolledTime;

    private Calendar c = Calendar.getInstance();

    public long getValue(Date currDt){
        c.setTime(this.lastPolledTime);
        c.add(Calendar.SECOND,this.dataAssetCadence);
        return c.getTimeInMillis() - currDt.getTime();
    }

    public DataAsset(String id,int cadence,Date dt){
        this.dataAssetId = id;
        this.dataAssetCadence = cadence;
        this.lastPolledTime = dt;
    }

    public String getAsset(){
        return dataAssetId;
    }

    public void setLastPolledTime(Date dt){
        this.lastPolledTime = dt;
    }

    public String toString(){

        return "Id-->"+this.dataAssetId+"  "+"lastPolledTime-->"+
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.lastPolledTime) + "  "+
                "cadence-->"+this.dataAssetCadence;
    }
}

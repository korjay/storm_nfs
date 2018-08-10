package Air;

import AirMap.AirMap;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by honey on 17. 10. 23.
 */
public class AirSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;

    static private ObjectArray ground = null;
    static private ObjectArray step2_region = null;
    static private ObjectArray emit_data1 = null;
    static private ObjectArray so_emit_data1 = null;
    static private ObjectArray step2_data = null;
    static private ObjectArray so_step2_data = null;
    static private MWNumericArray n = null;
    static private MWNumericArray region_n = null;
    static private MWNumericArray th = null;
    static double time = 0;
    static public int count = 0;
    Object[] result_step1_1 = null;
    Object[] result_step1_2 = null;
    Object[] result_step2 = null;
    Object[] result_step2_1 = null;
    Object[] result_step2_2 = null;
    Object[] result_step2_3 = null;
    Object[] bld3d = null;
    Object[] result_step2_4 = null;
    AirMap airMap;



    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        try {
            Thread.sleep(10 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.collector = collector;

        try {
            ground = new ObjectArray();
            step2_data = new ObjectArray();
            step2_region = new ObjectArray();
            airMap = new AirMap();

                int j = 0;
                region_n = new MWNumericArray(Double.valueOf(j), MWClassID.DOUBLE);
                n = new MWNumericArray(0, MWClassID.DOUBLE);
                th = new MWNumericArray(Double.valueOf(0.1),MWClassID.DOUBLE);
                result_step1_1 = airMap.step1_1(5, region_n);
                result_step1_2 = airMap.step1_2(5);
                result_step2_1 = airMap.step2_1(1, region_n);

        } catch (Exception e) {
            System.out.println("open Exception: " + e.toString());
        }
    }

    public void close(){

        MWArray.disposeArray(n);
        MWArray.disposeArray(result_step1_1);
        MWArray.disposeArray(result_step1_2);
        MWArray.disposeArray(result_step2_1);

        if (airMap != null)
            airMap.dispose();

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer){
        outputFieldsDeclarer.declare(new Fields("ground_data", "step", "num", "time"));
    }
    public void nextTuple(){
        time = System.currentTimeMillis();
        double beginTime = System.currentTimeMillis();
        try {
            for(int i = 0; i <= 100; i++){

                for(int num = 0; num <=51; num++) {

                    if(num == 0) {
                        System.out.println("----------------------------------------");
                        System.out.println("region_n = " + i);

                        //ground data emit
                        ground.setFlag(1);
                        ground.setNum(i);
                        ground.setStartTime(System.currentTimeMillis());

                        Object[] result_step1_3 = airMap.step1_3(1, result_step1_2[1],
                                result_step1_2[3], result_step1_2[4], i);
                        ground.setValue(result_step1_3);
                        System.out.println("*** Spout Emit... index :" + count);

                        this.collector.emit(new Values(ground.getValue(), ground.getFlag(), ground.getNum()
                                , ground.getStartTime()));
                        Thread.sleep(1000 * 1);
                    }
                    else{
                        ground.setFlag(2);
                        ground.setStartTime(System.currentTimeMillis());
                        n = new MWNumericArray(Double.valueOf(num), MWClassID.DOUBLE);
                        Object[] result_step2_2 = airMap.step2_2(2, result_step2_1[0], n, region_n);
                        ground.setValue(result_step2_2);
                        count++;
                        System.out.println("*** Spout Emit... index :" + count);
                        ground.setStartTime(System.currentTimeMillis());
                        this.collector.emit(new Values(ground.getValue(), ground.getFlag(),
                                ground.getNum(), ground.getStartTime()));
                        System.out.println("########## spout no2 data emit##########");

                        Thread.sleep(1000 * 1);

                    }
                }
            }

            Thread.sleep(1000 * 30);


        } catch (Exception e) {
            System.out.println("@@@Spout Exception: " + e.toString());
        }
        double endTime = System.currentTimeMillis();

    }

}

//timer
//index=o, time;
//        bolt2;
//        if index==0 starttime;
//        count++;
//        if count==51 pirntf(current time - starttime;);
//        endtime;
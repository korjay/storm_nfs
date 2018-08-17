package Air;

import AirMap.AirMap;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * Created by honey on 17. 10. 23.
 */
public class No2Bolt_2 extends BaseRichBolt {
    private OutputCollector collector;

    static private MWNumericArray n = null;
    static private MWNumericArray th = null;
    static private MWNumericArray region_n = null;
    static private ObjectArray step1_data = null;
    static private Object[] result_step1_1 = null;
    static private Object[] result_step1_2 = null;
    static private Object[] result_step1_3 = null;
    static private Object[] result_step1_4 = null;
    static private Object[] result_step2 = null;
    static private Object[] result_step2_1 = null;
    static private Object[] result_step2_2 = null;
    static private Object[] result_step2_3 = null;
    static private Object[] result_step2_4 = null;
    static private Object[] result_step2_5 = null;
    static private Object[] result_step3 = null;
    static private Object[] bld3d = null;
    static private AirMap airMap = null;
    static private ObjectArray ground = null;
    static private ObjectArray step2_region = null;
    static private ObjectArray step2_data = null;
    private int count = 0;
    static public long startTime, stopTime;
    static int count1 = 0;
    static long total = 0;
    long time;


    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        try {
            ground = new ObjectArray();
            step2_region = new ObjectArray();
            airMap = new AirMap();
            int j = 0;
            region_n = new MWNumericArray(Double.valueOf(j), MWClassID.DOUBLE);
            th = new MWNumericArray(Double.valueOf(0.1), MWClassID.DOUBLE);

            result_step1_1 = airMap.step1_1(5, region_n);
            result_step1_2 = airMap.step1_2(5);
            result_step2_1 = airMap.step2_1(1, region_n);
        } catch (Exception e) {
            System.out.println("No2Bolt Exception : " + e.toString());
        }
    }


    public void execute(Tuple tuple) {
        double beginTime = System.currentTimeMillis();
        int count1 = 0;
        try{
            count++;
            ground.setValue((Object[])tuple.getValueByField("no2"));
            ground.setFlag((Integer)tuple.getValueByField("n"));
            ground.setNum((Integer)tuple.getValueByField("length"));
            ground.setStartTime((Long)tuple.getValueByField("time"));
            ground.setStartGlobalTime((Long)tuple.getValueByField("globaltime_2"));
//          ground.setValue((Object[])tuple.getValueByField("ground_data"));
            System.out.print("@@@NO_img execute Start @@@");
            System.out.println("*** Region index : " +ground.getNum());
            region_n = new MWNumericArray(Double.valueOf(ground.getNum()), MWClassID.DOUBLE);
            startTime = ground.getStartGlobalTime();

//            if((count % 52) == 1)
//                startTime = ground.getStartTime();

            if(ground.getFlag() == 1){
                result_step1_3 = ground.getValue();
                result_step1_4 = airMap.step1_4(result_step1_3[0], result_step1_2[3], region_n);
                System.out.println("**** Ground Clear ****");
            }
        else if(ground.getFlag() == 2){
            result_step2_4 = ground.getValue();
//            System.out.println("**** result_step1_2 = " +result_step1_2[3]);
//            System.out.println("**** Step2_4[0] = " +result_step2_4[0]);
//            System.out.println("**** Step2_4[1] = " +result_step2_4[1]);
//            System.out.println("**** Step2_4[2] = " +result_step2_4[2]);
            result_step2_5 = airMap.step2_5(result_step1_2[3], result_step2_4[0], result_step2_4[1],
                    result_step2_4[2],region_n);
            System.out.println("**** No2Bolt_2 Clear count = " +count);
            System.out.println("** count : " +count+" ** Processing Time : " +(ground.getTime()/1000.000) +"s");
            System.out.println("---------------------------------------------------");

            if((count %52) == 0){
                airMap.step3(result_step1_1[0], result_step1_1[1], result_step1_1[2],
                        result_step1_1[4], result_step2_1[0], region_n);
                stopTime = System.currentTimeMillis();
//                startTime = ground.getStartTime();
                System.out.println("\n---------------------------------------------------");
                System.out.println("** 3D City Processing Time : " +((stopTime-startTime)/1000.000) +"s");
                System.out.println("---------------------------------------------------\n");
//                count1++;
            }

        }

        }catch (Exception e){
            System.out.println("No2_Bolt1 input stream Exception : " + e.toString());

        }



        double endTime = System.currentTimeMillis();
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
    public void cleanup() {
        System.out.println("------- FINAL COUNT -------");
        System.out.println("FINAL COUNT IS :  " + count);
        System.out.println("---------------------------");
    }
}

package Air;

import AirMap.AirMap;
import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by honey on 17. 10. 23.
 */
public class No2Bolt extends BaseRichBolt {
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
    static int count = 0;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
        try {
            ground = new ObjectArray();
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
        try{
            ground.setFlag((Integer)tuple.getValueByField("step"));
            ground.setValue((Object[])tuple.getValueByField("ground_data"));
            ground.setNum((Integer)tuple.getValueByField("num"));
            ground.setStartTime((Long)tuple.getValueByField("time"));
            ground.setStartGlobalTime((Long)tuple.getValueByField("globaltime"));
//        ground.setValue((Object[])tuple.getValueByField("ground_data"));
            System.out.print("@@@NO Step 2 execute Start @@@");


        if(ground.getFlag() == 1){
            int i = ground.getNum();
            Object[] result_step1_2 = ground.getValue();
            Object[] result_step1_3 = airMap.step1_3(1, result_step1_2[1],
                                result_step1_2[3], result_step1_2[4], i);
            System.out.println("**** step1_3 tuple read ok ****");
            ground.setValue(result_step1_3);
            this.collector.emit(new Values(ground.getValue(), ground.getFlag(), ground.getNum()
                    , ground.getStartTime(), ground.getStartGlobalTime()));
            Thread.sleep(10 * 1);

//            result_step1_4 = airMap.step1_4(result_step1_3[0], result_step1_2[3], region_n);
        }

        else if(ground.getFlag() == 2) {


            result_step2_2 = ground.getValue();
            result_step2_3 = airMap.step2_3(2, result_step2_2[0], result_step2_2[1],
                    0.1, result_step2_1[0]);
            result_step2_4 = airMap.step2_4(3, result_step1_2[3], result_step1_2[0],
                    result_step2_1[0], result_step2_3[0], result_step2_3[1], region_n);

            System.out.println("@@@ result_step2_4 Clear @@@");
            ground.setValue(result_step2_4);
//            System.out.println("**** Step2_4[0] = " +result_step2_4[0]);
            System.out.println("########## spout no2 data emit##########");
            this.collector.emit(new Values(ground.getValue(), ground.getFlag(),
                    ground.getNum(), ground.getStartTime(), ground.getStartGlobalTime()));
            Thread.sleep(10 * 1);
             }

//            result_step3 = airMap.step3(result_step1_1[0], result_step1_1[1], result_step1_1[2],
//                    result_step1_1[4], result_step2_1[0], region_n);
        }catch (Exception e){
            System.out.println("Spout input stream Exception : " + e.toString());

        }


        double endTime = System.currentTimeMillis();
//        System.out
//                .println("------------------------------------------------------");
//        System.out.println("#####Making the NO Step2Bolt FIN took " + (endTime - beginTime) / 1000
//                + " seconds.#####");
//        System.out
//                .println("------------------------------------------------------");

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("no2", "n", "length", "time", "globaltime_2"));
    }
}

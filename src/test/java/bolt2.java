import com.mathworks.toolbox.javabuilder.MWClassID;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.apache.storm.tuple.Tuple;

public class bolt2 {
//    public void execute(Tuple tuple) {
//        double beginTime = System.currentTimeMillis();
//        try{
//            count++;
//            ground.setValue((Object[])tuple.getValueByField("no2"));
//            ground.setFlag((Integer)tuple.getValueByField("n"));
//            ground.setNum((Integer)tuple.getValueByField("length"));
//            ground.setStartTime((Long)tuple.getValueByField("time"));
////          ground.setValue((Object[])tuple.getValueByField("ground_data"));
//            System.out.print("@@@NO_img execute Start @@@");
//            System.out.println("*** Region index : " +ground.getNum());
//            region_n = new MWNumericArray(Double.valueOf(ground.getNum()), MWClassID.DOUBLE);
//
//            if(ground.getFlag() == 1){
//                result_step1_3 = ground.getValue();
//                result_step1_4 = airMap.step1_4(result_step1_3[0], result_step1_2[3], region_n);
//                System.out.println("**** Ground Clear ****");
//            }
//
//            else if(ground.getFlag() == 2){
//                result_step2_4 = ground.getValue();
////            System.out.println("**** result_step1_2 = " +result_step1_2[3]);
////            System.out.println("**** Step2_4[0] = " +result_step2_4[0]);
////            System.out.println("**** Step2_4[1] = " +result_step2_4[1]);
////            System.out.println("**** Step2_4[2] = " +result_step2_4[2]);
//                result_step2_5 = airMap.step2_5(result_step1_2[3], result_step2_4[0], result_step2_4[1],
//                        result_step2_4[2],region_n);
//                System.out.println("**** No2Bolt_2 Clear count = " +count);
//                System.out.println("** count : " +count+" ** Processing Time : " +(ground.getTime()/1000.000) +"s");
//                System.out.println("---------------------------------------------------");
//
//                if((count %52) == 0){
//                    airMap.step3(result_step1_1[0], result_step1_1[1], result_step1_1[2],
//                            result_step1_1[4], result_step2_1[0], region_n);
//                    stopTime = System.currentTimeMillis();
//                    System.out.println("\n---------------------------------------------------");
//                    System.out.println("** 3D City Processing Time : " +((stopTime-startTime)/1000.000) +"s");
//                    System.out.println("---------------------------------------------------\n");
//                }
//
//            }
}

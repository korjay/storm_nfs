import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class bolt1 {
//    public void execute(Tuple tuple) {
//        double beginTime = System.currentTimeMillis();
//        try{
//            ground.setFlag((Integer)tuple.getValueByField("step"));
//            ground.setValue((Object[])tuple.getValueByField("ground_data"));
//            ground.setNum((Integer)tuple.getValueByField("num"));
//            ground.setStartTime((Long)tuple.getValueByField("time"));
////        ground.setValue((Object[])tuple.getValueByField("ground_data"));
//            System.out.print("@@@NO Step 2 execute Start @@@");
//
//
//            if(ground.getFlag() == 1){
//                result_step1_2 = ground.getValue();
//                System.out.println("**** step1_3 tuple read ok ****");
//
//                Object[] result_step1_3 = airMap.step1_3(1, result_step1_2[1],
//                        result_step1_2[3], result_step1_2[4], region_n);
//                ground.setValue(result_step1_3);
//
//                this.collector.emit(new Values(ground.getValue(), ground.getFlag(), ground.getNum()
//                        , ground.getStartTime()));
//                Thread.sleep(1000 * 1);
//            }
//
//            else if(ground.getFlag() == 2) {
//
//
//                result_step2_2 = ground.getValue();
//                result_step2_3 = airMap.step2_3(2, result_step2_2[0], result_step2_2[1],
//                        0.1, result_step2_1[0]);
//                result_step2_4 = airMap.step2_4(3, result_step1_2[3], result_step1_2[0],
//                        result_step2_1[0], result_step2_3[0], result_step2_3[1], region_n);
//
//                System.out.println("@@@ result_step2_4 Clear @@@");
//                ground.setValue(result_step2_4);
////            System.out.println("**** Step2_4[0] = " +result_step2_4[0]);
//                System.out.println("########## spout no2 data emit##########");
//                this.collector.emit(new Values(ground.getValue(), ground.getFlag(),
//                        ground.getNum(), ground.getStartTime()));
//                Thread.sleep(1000 * 1);
//            }
//
////            result_step3 = airMap.step3(result_step1_1[0], result_step1_1[1], result_step1_1[2],
////                    result_step1_1[4], result_step2_1[0], region_n);
//        }catch (Exception e){
//            System.out.println("Spout input stream Exception : " + e.toString());
//
//        }


        double endTime = System.currentTimeMillis();
//        System.out
//                .println("------------------------------------------------------");
//        System.out.println("#####Making the NO Step2Bolt FIN took " + (endTime - beginTime) / 1000
//                + " seconds.#####");
//        System.out
//                .println("------------------------------------------------------");

//    }
}

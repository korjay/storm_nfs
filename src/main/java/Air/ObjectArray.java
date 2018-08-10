package Air;

import java.io.Serializable;

/**
 * Created by root on 4/17/17.
 */

public class ObjectArray implements Serializable{
    private static Object[] ObjArr = null;
    private static Object[] ObjArr1 = null;
    private static int flag=0;
    public int num = 0;
    public static long startTime = 0;

    public void setValue(Object[] obj){
        ObjArr=obj;
    }

    public void setFlag(int i)
    {
        flag = i;
    }

    public Object[] getValue(){
        return ObjArr;
    }

    public void setValue1(Object[] obj1) { ObjArr1=obj1; }

    public Object[] getValue1() {return ObjArr1;}

    public void setNum(int Region) {num = Region;}

    public int getNum(){
        return num;
    }

    public int getFlag()
    {
        return flag;
    }

    public void setStartTime(long t) {startTime = t;}

    public long getStartTime(){return startTime;}

    public long getTime(){return System.currentTimeMillis()-startTime; }


}

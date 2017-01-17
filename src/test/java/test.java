import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

/**
 * Created by ccl on 16/11/15.
 */
public class test {
    @Test
    public void test(){
        try {
            int a = 100/0;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("hahah");
        }
    }


    @Test
    public void testDate(){
        DateTime now = new DateTime();
        System.out.println("当前时间:" + now.toString());
        System.out.println("当前时间:" + now.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        DateTime utc = new DateTime(DateTimeZone.UTC);
        System.out.println("当前UTC时间:" + utc.toString());
        System.out.println("当前UTC时间:" + utc.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        DateTime newUtc = new DateTime(now,DateTimeZone.UTC);
        System.out.println("当前时间转UTC:" + newUtc.toString());
        System.out.println("当前时间转UTC:" + newUtc.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"));
        DateTime local = new DateTime(utc,DateTimeZone.getDefault());
        System.out.println("UTC转当前时间:" + local.toString());
        System.out.println("UTC转当前时间:" + local.toString("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }

    @Test
    public void testDate2(){
        DateTime now = new DateTime();
        System.out.println("当前时间:     " + now.toString());
        now = new DateTime(now,DateTimeZone.UTC);
        System.out.println("当前时间UTC:  " + now.toString());
    }


    @Test
    public void testNumber(){
        int a=9;
        int b=9;
        System.out.printf("input a=%d,b=%d\n",a,b);
        if (a<b){
            a = b;
        }
        System.out.printf("out max=%d",a);
    }
}

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public static int MAX_REGION_NUMBER = 99;
    public static int THREAD_COUNT = 4;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        List<NumberBuilder> numberBuilderList = new ArrayList<>();
        int threadSize = (int) Math.ceil((double) MAX_REGION_NUMBER/THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {

            int startNumber = i*threadSize + 1;
            int finishNumber = Math.min( (i+1)*threadSize , MAX_REGION_NUMBER );

            NumberBuilder numberBuilder= new NumberBuilder(startNumber,finishNumber,i);
            numberBuilderList.add(numberBuilder);
            numberBuilder.start();

        }

        try {
            for (NumberBuilder numberBuilder: numberBuilderList) {
                numberBuilder.join();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }




}

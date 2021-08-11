import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Loader {

    public static int MAX_REGION_NUMBER = 99;
    public static int THREAD_COUNT = 4;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        List<Callable<NumberBuilder>> numberBuilderList = new ArrayList<>();
        int threadSize = (int) Math.ceil((double) MAX_REGION_NUMBER/THREAD_COUNT);

        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < THREAD_COUNT; i++) {

            int startNumber = i*threadSize + 1;
            int finishNumber = Math.min( (i+1)*threadSize , MAX_REGION_NUMBER );

            NumberBuilder numberBuilder= new NumberBuilder(startNumber,finishNumber,i);
            numberBuilderList.add(numberBuilder);
        }

        try {
            List<Future<NumberBuilder>> futures = service.invokeAll(numberBuilderList);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }




}

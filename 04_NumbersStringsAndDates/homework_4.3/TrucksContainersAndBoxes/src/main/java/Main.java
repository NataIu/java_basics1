import java.util.Scanner;

public class Main {

    private static final int MAX_CONTAINERS_QUANTITY_IN_TRUCK = 12;
    private static final int MAX_BOXES_QUANTITY_IN_CONTAINER = 27;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int boxesQuantity = Integer.parseInt(boxes);

        int recentNumberOfTruck = 0;
        int recentNumberOfContainer = 0;

        for (int i = 0; i <boxesQuantity; i++) {

            if (i % (MAX_CONTAINERS_QUANTITY_IN_TRUCK*MAX_BOXES_QUANTITY_IN_CONTAINER) == 0) {
                recentNumberOfTruck++;
                System.out.println("Грузовик: " + recentNumberOfTruck);

            }

            if ((i - (recentNumberOfTruck-1)*MAX_CONTAINERS_QUANTITY_IN_TRUCK*MAX_BOXES_QUANTITY_IN_CONTAINER) % MAX_BOXES_QUANTITY_IN_CONTAINER == 0) {
                recentNumberOfContainer++;
                System.out.println("\tКонтейнер: " + recentNumberOfContainer);
            }

            System.out.println("\t\tЯщик: " + (i+1));
        }

        System.out.println("Необходимо:"+System.lineSeparator()+"грузовиков - "+recentNumberOfTruck+" шт."+System.lineSeparator()+"контейнеров - "+recentNumberOfContainer+" шт.");
    }

}

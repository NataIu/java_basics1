import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int maxContainersQuantityInTruck = 12;
        int maxBoxesQuantityInContainer = 27;

        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int boxesQuantity = Integer.parseInt(boxes);

        int recentNumberOfTruck = 0;
        int recentNumberOfContainer = 0;

        for (int i = 0; i <boxesQuantity; i++) {

            if (i % (maxContainersQuantityInTruck*maxBoxesQuantityInContainer) == 0) {
                recentNumberOfTruck++;
                System.out.println("Грузовик: " + recentNumberOfTruck);

            }

            if ((i - (recentNumberOfTruck-1)*maxContainersQuantityInTruck*maxBoxesQuantityInContainer) % maxBoxesQuantityInContainer == 0) {
                recentNumberOfContainer++;
                System.out.println("\tКонтейнер: " + recentNumberOfContainer);
            }

            System.out.println("\t\tЯщик: " + (i+1));
        }

        System.out.println("Необходимо:"+System.lineSeparator()+"грузовиков - "+recentNumberOfTruck+" шт."+System.lineSeparator()+"контейнеров - "+recentNumberOfContainer+" шт.");
    }

}

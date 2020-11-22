import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int maxContainersQuantityInTruck = 12;
        int maxBoxesQuantityInContainer = 27;

        Scanner scanner = new Scanner(System.in);
        String boxes = scanner.nextLine();

        int boxesQuantity = Integer.parseInt(boxes);

        int neededQuantityOfContainers = (int) Math.ceil( ((double) boxesQuantity) / maxBoxesQuantityInContainer);
        int quantityOfBoxesInLastContainer = (boxesQuantity % maxBoxesQuantityInContainer == 0 ? maxBoxesQuantityInContainer : boxesQuantity % maxBoxesQuantityInContainer);
        int neededQuantityOfTrucks = (int) Math.ceil( ((double) neededQuantityOfContainers)/maxContainersQuantityInTruck);
        int quantityOfContainersInLastTruck = (neededQuantityOfContainers % maxContainersQuantityInTruck == 0 ?
                maxContainersQuantityInTruck : neededQuantityOfContainers % maxContainersQuantityInTruck);

        int recentNumberOfContainer = 0;
        int recentNumberOfBox = 0;


        for (int i = 0; i < neededQuantityOfTrucks; i++) {


            System.out.println("Грузовик: "+(i+1));
            int maxCounterForContainers = ((i+1) == neededQuantityOfTrucks ? quantityOfContainersInLastTruck : maxContainersQuantityInTruck);
            for (int j = 0; j < maxCounterForContainers; j++) {

                recentNumberOfContainer++;
                int maxCounterForBoxes = ((i+1) == neededQuantityOfTrucks && (j+1) == maxCounterForContainers ? quantityOfBoxesInLastContainer : maxBoxesQuantityInContainer);

                System.out.println("\tКонтейнер: "+recentNumberOfContainer);
                for (int k = 0; k < maxCounterForBoxes; k++) {
                    recentNumberOfBox++;
                    System.out.println("\t\tЯщик: "+recentNumberOfBox);

                }

            }

        }

        System.out.println("Необходимо:\nгрузовиков - "+neededQuantityOfTrucks+" шт.\nконтейнеров - "+neededQuantityOfContainers+" шт.");
    }

}

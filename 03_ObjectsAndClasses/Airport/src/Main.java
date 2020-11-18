import com.skillbox.airport.Airport;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
        int aircraftQuantity = airport.getAllAircrafts().size();
        System.out.println("Количество самолетов = "+aircraftQuantity);
    }
}

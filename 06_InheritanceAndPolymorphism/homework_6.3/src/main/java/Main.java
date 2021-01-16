

public class Main {

    public static void main(String[] args) {
        Client Ivanov = new PhysicalPerson();
        Client Petrov = new LegalPerson();
        Client Sidorov = new IndividualBusinessman();

        testPerson(Ivanov);
        testPerson(Petrov);
        testPerson(Sidorov);

    }

    public static void testPerson(Client person) {

        person.put(2000);
        System.out.println(person.getAmount());
        person.put(50);
        System.out.println(person.getAmount());
        person.put(-100);
        System.out.println(person.getAmount());
        person.take(200);
        System.out.println(person.getAmount());
        person.take(person.getAmount());
        System.out.println(person.getAmount());

        System.out.println();
    }




}

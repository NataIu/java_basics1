
public class Loader
{
    public static void main(String[] args)
    {
//        Cat cat = new Cat();
//
//        System.out.println(cat.getStatus());

        //creating cats
        Cat vasya = new Cat();
        Cat murka = new Cat();
        Cat petr = new Cat();
        Cat dymok = new Cat();
        Cat bonya = new Cat();

        //printing cats' weight
        System.out.println("Vasya weight = "+ vasya.getWeight());
        System.out.println("Murka weight = "+ murka.getWeight());
        System.out.println("Petr weight = "+ petr.getWeight());
        System.out.println("Dymok weight = "+ dymok.getWeight());
        System.out.println("Bonya weight = "+ bonya.getWeight());

        //feeding two cats and printing their weights
        vasya.feed(100.0);
        murka.feed(50.0);
        System.out.println();
        System.out.println("After feeding:");
        System.out.println("Vasya weight = "+ vasya.getWeight());
        System.out.println("Murka weight = "+ murka.getWeight());

        //feeding too much
        System.out.println();
        System.out.println("Before exploding: Vasya status = "+vasya.getStatus()+", Vasya weight = "+ vasya.getWeight());
        while (! vasya.getStatus().equals("Exploded")) {
            vasya.feed(100.0);
        }
        System.out.println("After exploding: Vasya status = "+vasya.getStatus()+", Vasya weight ="+ vasya.getWeight());

        //meowing too much
        System.out.println();
        System.out.println("Before meowing: Murka status = "+murka.getStatus()+", Murka weight = "+ murka.getWeight());
        while (! murka.getStatus().equals("Dead")) {
            murka.meow();
         }
        System.out.println("After meowing: Murka status = "+murka.getStatus()+", Murka weight = "+ murka.getWeight());


    }
}
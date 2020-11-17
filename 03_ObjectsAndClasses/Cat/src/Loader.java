
public class Loader
{
    public static void main(String[] args)
    {

//        Cat cat = new Cat();
//
//        System.out.println(cat.getStatus());

        //creating cats
        System.out.println("Count of cats = "+Cat.getCount());
        Cat vasya = new Cat();
        Cat murka = new Cat();

        System.out.println("Count of cats = "+Cat.getCount());
        Cat petr = new Cat();
        Cat dymok = new Cat();
        Cat bonya = new Cat();

        System.out.println("Count of cats = "+Cat.getCount());

        //feeding too much
        System.out.println();
        System.out.println("Before exploding: Vasya status = "+vasya.getStatus()+", Vasya weight = "+ vasya.getWeight());
        while (! vasya.getStatus().equals("Exploded")) {
            vasya.feed(100.0);
        }
        System.out.println("After exploding: Vasya status = "+vasya.getStatus()+", Vasya weight ="+ vasya.getWeight());

        System.out.println("Count of cats = "+Cat.getCount());

        //testing feeding, drinking, peeing, meowing for exploded cat
        vasya.feed(10.0);
        vasya.drink(1.0);
        vasya.pee();
        vasya.meow();
        System.out.println("Test for exploded cat: Vasya status = "+vasya.getStatus()+", Vasya weight ="+ vasya.getWeight());

        //meowing too much
        System.out.println();
        System.out.println("Before meowing: Murka status = "+murka.getStatus()+", Murka weight = "+ murka.getWeight());
        while (! murka.getStatus().equals("Dead")) {
            murka.meow();
        }
        System.out.println("After meowing: Murka status = "+murka.getStatus()+", Murka weight = "+ murka.getWeight());

        //testing feeding, drinking, peeing, meowing for dead cat
        murka.feed(10.0);
        murka.drink(1.0);
        murka.pee();
        murka.meow();
        System.out.println("Test for dead cat: Murka status = "+murka.getStatus()+", Murka weight ="+ murka.getWeight());

        System.out.println("Count of cats = "+Cat.getCount());


    }
}
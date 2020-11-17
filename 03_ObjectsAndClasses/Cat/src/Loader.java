
public class Loader
{
    public static void main(String[] args)
    {

        Cat vasya = new Cat();
        System.out.println("Vasya weigt = "+vasya.getWeight()+", eaten food weigt = "+vasya.getEatenFoodWeight());

        vasya.feed(100.0);
        System.out.println("Vasya weigt = "+vasya.getWeight()+", eaten food weigt = "+vasya.getEatenFoodWeight());

        vasya.feed(150.0);
        System.out.println("Vasya weigt = "+vasya.getWeight()+", eaten food weigt = "+vasya.getEatenFoodWeight());

        vasya.pee();
        System.out.println("Vasya weigt = "+vasya.getWeight()+", eaten food weigt = "+vasya.getEatenFoodWeight());

        for (int i = 0; i < 7; i++) {
            vasya.pee();
        }
        System.out.println("Vasya weigt = "+vasya.getWeight()+", eaten food weigt = "+vasya.getEatenFoodWeight());

    }
}
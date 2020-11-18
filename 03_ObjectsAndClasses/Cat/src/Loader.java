
public class Loader
{
    public static void main(String[] args)
    {

        Cat kitten1 = new Cat();
        kitten1.setColor(Color.ORANGE);
        kitten1.feed(120.0);
        System.out.println("Kitten1: weight = "+kitten1.getWeight()+", origin weight = "+kitten1.getOriginWeight()+
                ", color = "+kitten1.getColor()+", eaten food weigt = "+kitten1.getEatenFoodWeight());

        Cat kitten2 = kitten1.deepCopyCat();
        System.out.println("Kitten2: weight = "+kitten2.getWeight()+", origin weight = "+kitten2.getOriginWeight()+
                ", color = "+kitten2.getColor()+", eaten food weigt = "+kitten2.getEatenFoodWeight());

        kitten2.feed(300.0);

        System.out.println("Kitten1: weight = "+kitten1.getWeight()+", origin weight = "+kitten1.getOriginWeight()+
                ", color = "+kitten1.getColor()+", eaten food weigt = "+kitten1.getEatenFoodWeight());

        System.out.println("Kitten2: weight = "+kitten2.getWeight()+", origin weight = "+kitten2.getOriginWeight()+
                ", color = "+kitten2.getColor()+", eaten food weigt = "+kitten2.getEatenFoodWeight());


    }


    private static Cat getKitten() {
        return new Cat(1100.0);
    }

}
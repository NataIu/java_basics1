
public class Loader
{
    public static void main(String[] args)
    {

        Cat kitten1 = getKitten();
        Cat kitten2 = getKitten();
        Cat kitten3 = getKitten();

        kitten1.setColor(Color.ORANGE);
        System.out.println(kitten1.getColor().toString());

    }


    private static Cat getKitten() {
        return new Cat(1100.0);
    }

}
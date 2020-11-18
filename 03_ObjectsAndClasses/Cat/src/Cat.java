
public class Cat
{
    private final static int eyesCount = 2; //количество глаз
    private final static double minWeight = 1000.0; //минимальный вес
    private final static double maxWeight = 9000.0; //максимальный вес

    private static int count;

    private double originWeight;
    private double weight;
    private double eatenFoodWeight;
    private Color color;

    public Cat(double weight) {
        this.weight = weight;
    }

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
//        minWeight = 1000.0;
//        maxWeight = 9000.0;
        count++;

    }

    public Cat deepCopyCat() {
        Cat newCat = new Cat();
        newCat.setOriginWeight(this.getOriginWeight());
        newCat.setWeight(this.getWeight());
        newCat.setEatenFoodWeight(this.getEatenFoodWeight());
        newCat.setColor(this.getColor());
        return newCat;
    }

    public void meow()
    {
        if (!isAlive()) {
            System.out.println("Cat isn't alive, it can't meow!");
            return;
        }
        weight = weight - 1;
        System.out.println("Meow");
        decreaseCountIfNeeded();
    }

    public void feed(Double amount)
    {
        if (!isAlive()) {
            System.out.println("Cat isn't alive, it can't eat!");
            return;
        }
        weight = weight + amount;
        eatenFoodWeight = eatenFoodWeight +amount;
        decreaseCountIfNeeded();
    }

    public void drink(Double amount)
    {
        if (!isAlive()) {
            System.out.println("Cat isn't alive, it can't drink!");
            return;
        }
        weight = weight + amount;
        decreaseCountIfNeeded();
    }

    public Double getWeight()
    {
        return weight;
    }

    public String getStatus()
    {
        if(weight < minWeight) {
            return "Dead";
        }
        else if(weight > maxWeight) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

    public double getEatenFoodWeight() {
        return eatenFoodWeight;
    }

    public void pee() {
        if (!isAlive()) {
            System.out.println("Cat isn't alive, it can't pee!");
            return;
        }
        weight = weight - 1;
        System.out.println("Ready!");
        decreaseCountIfNeeded();
    }

    private void decreaseCountIfNeeded() {
        if (!isAlive()) {
            //думала, надо ли ставить проверку на то,что до последнего действия кошка была живая.
            //(иначе можем дважды уменьшить count)
            // Но не стала, т.к. во всех методах, которые меняют вес (и, соответственно, статус)
            // стоит запрет работы с неживой кошкой
            count--;
        }
    }

    public static int getCount() {
        return count;
    }

    private boolean isAlive() {
        return getStatus().equals("Sleeping") || getStatus().equals("Playing");
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEatenFoodWeight(double eatenFoodWeight) {
        this.eatenFoodWeight = eatenFoodWeight;
    }
}
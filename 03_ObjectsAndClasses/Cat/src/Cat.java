
public class Cat
{
    private double originWeight;
    private double weight;
    private double eatenFoodWeight;

    private double minWeight;
    private double maxWeight;

    private static int count;

    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        count++;

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


}
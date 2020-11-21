public class GeometryCalculator {
    // метод должен использовать абсолютное значение radius
    public static double getCircleSquare(double radius) {
        return Math.PI * radius * radius;
    }

    // метод должен использовать абсолютное значение radius
    public static double getSphereVolume(double radius) {
        return Math.PI * Math.pow( Math.abs(radius),3)* 4 / 3;
    }

    public static boolean isTrianglePossible(double a, double b, double c) {
        if ((a > 0) && (b > 0) && (c > 0) && (a + b > c) && (a + c > b) && (b + c > a)) {
            return true;
        }
        else {
            return false;
        }
    }

    // перед расчетом площади рекомендуется проверить возможен ли такой треугольник
    // методом isTrianglePossible, если невозможен вернуть -1.0
    public static double getTriangleSquare(double a, double b, double c) {
        if (!isTrianglePossible(a,b,c)) {return -1;}
        double halfPerimeter = (a + b + c) * 0.5;
        return Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
    }
}

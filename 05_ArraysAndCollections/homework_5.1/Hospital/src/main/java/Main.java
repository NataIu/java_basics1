public class Main {
    public static void main(String[] args) {

//        float[] temperatureData = Hospital.generatePatientsTemperatures(10);

        String line = "32.1 33.1 32.5 33.5 34.5 36.5 38.5 39.5 33.3 32.7 36.9 36.5 34.3 37.5 32.5 32.5 32.4 34.5 35.4 32.5 34.5 39.4 32.5 36.5 36.4 39.6 37.5 32.5 37.5 39.4";
        String[] lines = line.split("\\s");
        float[] temperatureData = new float[lines.length];
        for (int i = 0; i < lines.length; i++) {
            temperatureData[i] = Float.valueOf(lines[i]);
        }
        System.out.println(Hospital.getReport(temperatureData));

        //Пример вывода в консоль:
        //Температуры пациентов: 36.7 38.9 34.7
        //Средняя температура: 36.76
        //Количество здоровых: 1

        //Округлите среднюю температуру с помощью Math.round до 2 знаков после запятой.
    }
}

public class Hospital {

    public static final float MIN_HEALTH_TEMPERATURE = (float) 36.2;
    public static final float MAX_HEALTH_TEMPERATURE = (float) 36.9;

    public static float[] generatePatientsTemperatures(int patientsCount) {

        float[] patientTemperatures = new float[patientsCount];
        for (int i = 0; i < patientsCount; i++) {
            patientTemperatures[i] = ((float) Math.round(10* (32 + Math.random() * 8))) /10;
        }

        return patientTemperatures;
    }

    public static String getReport(float[] temperatureData) {

        StringBuilder stringOfTemperatures = new StringBuilder();
        float averageTemperature = 0;
        int countOfHealthyPatients = 0;
//        float minHealthTemperature = (float) 36.2;
//        float maxHealthTemperature = (float) 36.9;

        for (float temperature:temperatureData) {

            if (stringOfTemperatures.length() != 0) {
                stringOfTemperatures.append(" ");
            }
            stringOfTemperatures.append(temperature);

            averageTemperature = averageTemperature +temperature;

            if (Float.compare(temperature,MIN_HEALTH_TEMPERATURE)>= 0 &&
                    Float.compare(MAX_HEALTH_TEMPERATURE,temperature) >= 0){
                countOfHealthyPatients++;
            }

        }

        averageTemperature =averageTemperature / temperatureData.length;
        averageTemperature = ((float) (Math.round(averageTemperature*100)))/100;

        String report =
                "Температуры пациентов: " + stringOfTemperatures.toString() +
                        "\nСредняя температура: " + averageTemperature +
                        "\nКоличество здоровых: " + countOfHealthyPatients;

        return report;
    }
}

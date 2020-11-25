public class Main {

    public static void main(String[] args) {

        String safe = searchAndReplaceDiamonds("Номер кредитной карты <4008 1234 5678> 8912", "***");
        System.out.println(safe);

    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {

        String resultText = "";
        boolean lookingForLeftSign = true;
        int leftSignIndex;
        int rightSignIndex;

        while (text.length() > 0) {

            if (lookingForLeftSign) {
                //ищем символ "<"
                leftSignIndex = text.indexOf('<');
                if (leftSignIndex >= 0) {
                    resultText = resultText + text.substring(0, leftSignIndex);
                    text = text.substring(leftSignIndex + 1);
                    lookingForLeftSign = false;
                } else {
                    resultText = resultText + text;
                    text = "";
                }

            } else {
                //символ "<" уже нашли, ищем ">"
                rightSignIndex = text.indexOf(">");
                if (rightSignIndex >= 0) {
                    resultText = resultText + placeholder;
                    text = text.substring(rightSignIndex + 1);
                    lookingForLeftSign = true;
                }
                else {
                    resultText = resultText + "<" + text;
                    text = "";
                }

            }

        }

        return resultText;
    }

}
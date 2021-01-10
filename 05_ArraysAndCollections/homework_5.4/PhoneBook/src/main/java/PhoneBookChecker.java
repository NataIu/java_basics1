public class PhoneBookChecker {

    private final static String NAME_PATTERN = "[А-ЯЁ][а-яё]*";
    private final static String PHONE_PATTERN = "79[0-9]{9}";

    public static boolean isPhone(String text) {

        boolean result = false;
        if (text.matches(PHONE_PATTERN)){
            result = true;
        }
        return result;
    }

    public static boolean isName(String text) {

        boolean result = false;
        if (text.matches(NAME_PATTERN)){
            result = true;
        }
        return result;
    }
}

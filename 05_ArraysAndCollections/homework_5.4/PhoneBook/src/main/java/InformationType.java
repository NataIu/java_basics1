public enum InformationType {
    PHONE_NUMBER,
    NAME,
    LIST,
    ANOTHER;


    public static InformationType findInformationType(String text) {

        InformationType result = InformationType.ANOTHER;

        if (text.equals("LIST")) {
            result = InformationType.LIST;
        } else if (PhoneBookChecker.isName(text)) {
            result = InformationType.NAME;
        } else if (PhoneBookChecker.isPhone(text)) {
            result = InformationType.PHONE_NUMBER;
        }

        return result;
    }

}

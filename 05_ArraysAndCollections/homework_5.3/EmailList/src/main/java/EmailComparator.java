import java.util.Comparator;

public class EmailComparator implements Comparator<String> {

    @Override
    public int compare(String email1, String email2) {

        String domen1 = getDomenFromEmail(email1);
        String domen2 = getDomenFromEmail(email2);

        return domen1.compareTo(domen2);
    }

    private String getDomenFromEmail(String email) {
        int domenIndex = email.indexOf("@");
        String domen = email.substring(domenIndex+1);
        return domen;
    }
}

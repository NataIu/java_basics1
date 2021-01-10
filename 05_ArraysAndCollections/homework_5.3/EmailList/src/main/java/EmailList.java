import java.util.*;

public class EmailList {

    private EmailComparator emailComparator = new EmailComparator();
    private TreeSet<String> treeSet = new TreeSet<>(emailComparator);
    private final static String reg = "[0-9a-z]*@[a-z]*\\.[a-z]*";

    public void add(String email) {

        String lowerCaseEmail = email.toLowerCase(Locale.ROOT);

        if (lowerCaseEmail.matches(reg)) {
            treeSet.add(lowerCaseEmail);
        } else {
            System.out.println(Main.WRONG_EMAIL_ANSWER);
        }
    }



    public List<String> getSortedEmails() {
        return new ArrayList<>(treeSet);
    }

}

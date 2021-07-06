
import java.util.*;


public class LinkStorage {


    private static SortedSet<String> siteMap = Collections.synchronizedSortedSet(new TreeSet<>());
    private static Set<String> foundLinks = Collections.synchronizedSet(new HashSet<>());
    private static Set<String> badLinks = Collections.synchronizedSet(new HashSet<>());

    public static Set<String> getFoundLinks() {
        return foundLinks;
    }


    public static SortedSet<String> getSiteMap() {
        return siteMap;
    }

    public static Set<String> getBadLinks() {
        return badLinks;
    }

    public static void addSiteLink(String link) {
        siteMap.add(link);
    }

    public static void addFoundLink(String link) {
        foundLinks.add(link);
    }

    public static void addBadLink(String link) {
        badLinks.add(link);
    }


}

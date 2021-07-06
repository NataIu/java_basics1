import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

import static java.lang.Thread.sleep;


public class LinkReader extends RecursiveTask<Integer> { // что тут возвращать???

    private String link;
    private String linkRegExpression;
    private Set<String> foundLinks;

    public LinkReader(String link, String linkRegExpression) {
        this.link = link;
        this.linkRegExpression = linkRegExpression;
    }

    @Override
    protected Integer compute() {

        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<LinkReader> taskList = new HashSet<>();
        Set<String> childrenLinks = getChildrenLinks();

        int i =1;
        for (String childLink : childrenLinks) {
            if (!LinkStorage.getSiteMap().contains(childLink)) {
                LinkReader task = new LinkReader(childLink, linkRegExpression);
                taskList.add(task);
                task.fork();
            }
        }

        for (LinkReader task: taskList) {
            task.join();
        }
        return 0;

    }

    public Set<String> getChildrenLinks() {

        Set<String> result = new HashSet<>();

        if (LinkStorage.getSiteMap().contains(link)) {
            //эта ссылка уже обрабатывается либо обработана. Пропускаем
            System.out.println("No 1: "+this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
            return result;
        }

        if (LinkStorage.getBadLinks().contains(link)) {
            //эта ссылка некорректная. Пропускаем
            System.out.println("No 4: "+this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
            return result;
        }

        Document doc = null;
        try {
            doc = Jsoup.connect(link).maxBodySize(0).get();
                if (LinkStorage.getSiteMap().contains(link)) {
                    //эта ссылка уже обрабатывается либо обработана. Пропускаем
                    System.out.println("No 2: "+this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
                    return result;
                }

                if (LinkStorage.getBadLinks().contains(link)) {
                    //эта ссылка некорректная. Пропускаем
                    System.out.println("No 5: "+this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
                    return result;
                }

                LinkStorage.addSiteLink(link); //это настоящая ссылка, добавим ее в список обрабатываемых-обработанных
                System.out.println(this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
        } catch (IOException e) {
            System.out.println("No 3: "+this.toString()+ " - " +link + " - " + LinkStorage.getSiteMap().size());
            LinkStorage.addBadLink(link);
            return result;
        }

        Elements elements = doc.select("a");
        doc.select("a");
        for (Element element : elements) {
            if (element.absUrl("href").matches(linkRegExpression)
            && ! element.absUrl("href").matches(".*#.*"))
            {
                LinkStorage.addFoundLink(link); //добавим ее в список всех найденных
                String tmpLink = element.absUrl("href");
                if (! result.contains(tmpLink) && ! LinkStorage.getFoundLinks().contains(tmpLink)) {
                    result.add(tmpLink);
                }
            }
        }
        return result;
    }

}

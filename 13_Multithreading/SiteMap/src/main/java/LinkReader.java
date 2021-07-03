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
    private Set<String> siteMap;

    public LinkReader(String link, String linkRegExpression, Set<String> siteMap) {
        this.link = link;
        this.linkRegExpression = linkRegExpression;
        this.siteMap = siteMap;
    }

    @Override
    protected Integer compute() {

        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<LinkReader> taskList = new ArrayList<>();
        Set<String> childrenLinks = getChildrenLinks();


        for (String childLink : childrenLinks) {
            if (!siteMap.contains(childLink)) {
                LinkReader task = new LinkReader(childLink, linkRegExpression, siteMap);
//                siteMap.add(childLink);
                taskList.add(task);
//                System.out.println(childLink);
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

        if (siteMap.contains(link)) {
            //эта ссылка уже обрабатывается. Пропускаем
            return result;
        }

        Document doc = null;
        try {
            doc = Jsoup.connect(link).maxBodySize(0).get();
            synchronized (siteMap) {
                siteMap.add(link); //это настоящая ссылка, добавим ее в список
                System.out.println(this.toString()+ " - " +link + " - " + siteMap.size());
            }
        } catch (IOException e) {
//            logger.error(e.getMessage());
            return result;
        }

        Elements elements = doc.select("a");
        doc.select("a");
        for (Element element : elements) {
            if (element.absUrl("href").matches(linkRegExpression)
            && ! element.absUrl("href").matches(".*#.*")
            )
            {
                String tmpLink = element.absUrl("href");
                if (! result.contains(tmpLink)) {
                    result.add(tmpLink);
                }
            }
        }
        return result;
    }

}

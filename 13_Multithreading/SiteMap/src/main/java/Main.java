

import java.io.*;

import java.util.Date;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

        public static final String WEB_SITE = "https://lenta.ru/";
//    public static final String WEB_SITE = "https://skillbox.ru";
//    public static final String WEB_SITE = "https://marialife.com/";
    public static final String LINK_REG_EXPRESSION = WEB_SITE + ".*";
    public static volatile Set<String> siteMap = new TreeSet<>();
    public static final String FILE_PATH = "myLentaSiteMap"+ (new Date());


    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Start" + (new Date()));
//        siteMap.add(WEB_SITE);
        int res = new ForkJoinPool().invoke(new LinkReader(WEB_SITE, LINK_REG_EXPRESSION, siteMap));
        System.out.println("I've parsed! Start printing.");
        writeSiteMapToFile();
        System.out.println("Finish"+ (new Date()));

    }

    private static void writeSiteMapToFile() throws FileNotFoundException {
        Stack<String> stack = new Stack<>();
        String spaceText = "";

        PrintWriter writer = new PrintWriter(FILE_PATH);

        for (String siteMapElement : siteMap) {
            if (stack.size() == 0) {
                writeStringToFile(stack, writer, siteMapElement, spaceText);
            } else {
                while (!siteMapElement.matches("" + stack.peek() + ".*")) {
                    stack.pop();
                    spaceText = decreaseSpaceText(spaceText);
                }
                if (siteMapElement.matches("" + stack.peek() + ".*")) {
                    spaceText = increaseSpaceText(spaceText);
                    writeStringToFile(stack, writer, siteMapElement, spaceText);
                }
            }
        }

        writer.flush();
        writer.close();
    }

    private static String increaseSpaceText(String spaceText) {
        return spaceText + "\t";
    }

    private static String decreaseSpaceText(String spaceText) {
        if (spaceText.length() > 0) {
            spaceText = spaceText.substring(1, spaceText.length());
        }
        return spaceText;
    }

    private static void writeStringToFile(Stack<String> stack, PrintWriter writer, String siteMapElement, String spaceText) {
        stack.push(siteMapElement);
        writer.write(spaceText + siteMapElement + "\n");
//        System.out.println(spaceText + siteMapElement);
    }


}

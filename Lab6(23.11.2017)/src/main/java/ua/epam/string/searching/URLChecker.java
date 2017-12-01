package ua.epam.string.searching;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Collections.emptyList;

/**
 * Написать регулярное выражение, определяющее является ли данная строчка валидным URL адресом.
 * В данной задаче правильным URL считаются адреса http и https, явное указание протокола также может отсутствовать.
 * Учитываются только адреса, состоящие из символов, т.е. IP адреса в качестве URL не присутствуют при проверке.
 * Допускаются поддомены, указание порта доступа через двоеточие, GET запросы с передачей параметров,
 * доступ к подпапкам на домене, допускается наличие якоря через решетку. Однобуквенные домены считаются запрещенными.
 * Запрещены спецсимволы, например «–» в начале и конце имени домена. Запрещен символ «_» и пробел в имени домена.
 * При составлении регулярного выражения ориентируйтесь на список правильных и неправильных выражений заданных ниже.
 * Пример правильных выражений: http://www.zcontest.ru, http://zcontest.ru.
 * Пример неправильных выражений:  Just TextElement, http://a.com.
 */
public class URLChecker {

    /**
     * Good characters for Internationalized Resource Identifiers (IRI).
     * This comprises most common used Unicode characters allowed in IRI
     * as detailed in RFC 3987.
     * Specifically, those two byte Unicode characters are not included.
     */
    public static final String GOOD_IRI_CHAR =
            "a-zA-Z0-9\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF";

    public static final Pattern IP_ADDRESS
            = Pattern.compile(
            "((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                    + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                    + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                    + "|[1-9][0-9]|[0-9]))");

    /**
     * RFC 1035 Section 2.3.4 limits the labels to a maximum 63 octets.
     */
    private static final String IRI
            = "[" + GOOD_IRI_CHAR + "]([" + GOOD_IRI_CHAR + "\\-]{0,61}[" + GOOD_IRI_CHAR + "]){0,1}";

    private static final String GOOD_GTLD_CHAR =
            "a-zA-Z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF";
    private static final String GTLD = "[" + GOOD_GTLD_CHAR + "]{2,63}";
    private static final String HOST_NAME = "(" + IRI + "\\.)+" + GTLD;

    public static final Pattern DOMAIN_NAME
            = Pattern.compile("(" + HOST_NAME + "|" + IP_ADDRESS + ")");

    /**
     *  Regular expression pattern to match most part of RFC 3987
     *  Internationalized URLs, aka IRIs.  Commonly used Unicode characters are
     *  added.
     */
    public static final Pattern WEB_URL = Pattern.compile(
            "((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)"
                    + "\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_"
                    //+ "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?"
                      + "\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)"
                    //+ "(?:" + DOMAIN_NAME + ")"
                    + "(?:" + HOST_NAME + ")"
                    + "(?:\\:\\d{1,5})?)" // plus option port number
                    + "(\\/(?:(?:[" + GOOD_IRI_CHAR + "\\;\\/\\?\\:\\@\\&\\=\\#\\~"  // plus option query params
                    + "\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?"
                    + "(?:\\b|$)"); // and finally, a word boundary or end of
    // input.  This is to stop foo.sure from
    // matching as foo.su

    public static final Pattern EMAIL_ADDRESS
            = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    private final String REGEX =
            "(https?://)*"+                         //[http[s]://]
            "(www\\.)?"+                            //[www.]
            "(([a-zA-Z]\\w+\\.)+)([a-zA-Z]{3,6})"+  //[domain.]subdomain.com
            "(\\:[0-9]{1,5})?"+                     //[:port]
            "(\\/\\w+)*(\\.\\w+)?"+                 //[/folder or file][.ext]
            "([\\?|\\&]\\w+\\=\\w*)*"+              //[? or &parameter=value]
            "(\\#\\w+)?";                           // #-tag

    /**
     * Перевіряє, чи стрічка є допустимимою URL-адресою
     * @param line
     * @return
     */
    public boolean isValidUrl(String line){
        Matcher matcher = WEB_URL.matcher(line);
        return  matcher.matches();
    }

    /**
     * Повертає масив URL-адрес, які знайдено в стрічці
     * @param line
     * @return
     */
    public Deque<String> extractUrls(String line){

        if(line==null || line.length()==0)
            return new ArrayDeque<>();

        Deque<String> deque = new ArrayDeque();
        Set<String> set = new LinkedHashSet<>();

        System.out.print("Getting URL list");
        int count = set.size();

        Matcher matcher = WEB_URL.matcher(line);

        while (matcher.find()) {
            //URLList.add(matcher.group());
            set.add(matcher.group());
        }
        System.out.print(" - OK Found: "+(set.size()-count)+" URLs ");
        for (String item: set){
            deque.push(item);
        }
        return deque;
    }
}

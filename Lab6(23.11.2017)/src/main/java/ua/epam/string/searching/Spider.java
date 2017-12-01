package ua.epam.string.searching;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Клас для сканування сайта, пошук в ньому URL-адрес, індексація слів по частоті входження
 */
public class Spider {

    public static Set<String> URLList = new HashSet<>();
    public static Map<String, Map<String, Integer>> wordSequency = new LinkedHashMap<>();
    public URLChecker urlChecker = new URLChecker();

    public static final int MIN_WORD_LENGTH = 4;

    private DataProvider dataProvider;

    public Spider() {
    }

    /**
     * Аналіз сайту
     * @param url
     */
    public boolean scan(String url) {
        /**
         * Scan top level
         */
        System.out.print("Get information from site: " + url);
        String text = loadSite(url);
        Deque<String> deque = urlChecker.extractUrls(text);
        URLList.addAll(deque);
        getWordsList(text, url);

        /**
         * Scan sublevel
         */
        int count = 0;
        while (!deque.isEmpty()&&count++<20) {
            String x = deque.pop();
            System.out.print(count+" Get information from site: " + x + " ");
            String t = loadSite(x);
            //deque.addAll(urlChecker.extractUrls(t));
            getWordsList(t, x);
        }

        sortWordList();
        return true;
    }

    public String getWordWithMAXURLCounts(){
        if(wordSequency==null|| wordSequency.isEmpty()) {
            return null;
        }
        Iterator<Map.Entry<String, Map<String, Integer>>> iterator = wordSequency.entrySet().iterator();
        Map.Entry<String, Map<String, Integer>> maxItem = null;
        if(iterator.hasNext()) {
            maxItem = iterator.next();
        }
        if(maxItem==null)
            return null;
        while (iterator.hasNext()){
            Map.Entry<String, Map<String, Integer>> nextItem = iterator.next();
            if (maxItem.getValue().entrySet().size() < nextItem.getValue().entrySet().size())
                maxItem = nextItem;
        }
        return maxItem.getKey();
    }

    /**
     * Get word with max frequency
     * @return
     */
    public String getWordWithMAXStat(){
        if(wordSequency==null|| wordSequency.isEmpty()) {
            return null;
        }
        Iterator<Map.Entry<String, Map<String, Integer>>> iterator = wordSequency.entrySet().iterator();
        if (iterator.hasNext())
            return iterator.next().getKey();
        return null;

    }

    /**
     * Статистика по слову
     * @param string
     */
    public void showWordStats(String string) {
        if(wordSequency==null|| wordSequency.isEmpty()) {
            System.out.println("Data not found");
            return;
        }
        Map<String, Integer> map = wordSequency.get(string);
        if (map != null) {
            System.out.print("\nWord " + string + " found: ");
            showWordStatsDetail(map);
        } else {
            System.out.println("word " + string + " not found");
        }
    }

    /**
     * статистика по TOP-10 слів
     */
    public void showTopWordList() {
        if(wordSequency==null|| wordSequency.isEmpty()) {
            System.out.println("Data not found");
            return;
        }
        System.out.println("TOP-10 words");
        int count = 0;
        for (Map.Entry<String, Map<String, Integer>> word : wordSequency.entrySet()) {
            count++;
            Map<String, Integer> value = word.getValue();
            String key = word.getKey();
            System.out.print(count + ") " + key + " found: ");
            showWordStatsDetail(value);
            if (count >= 10)
                break;
            System.out.println();
        }
    }

    /**
     * Деталізація по слову
     * @param map
     */
    private void showWordStatsDetail(Map<String, Integer> map) {
        if(map==null || map.isEmpty())
            return;
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String url = entry.getKey();
            Integer count = entry.getValue();
            if (url.equals("total"))
                System.out.println(count + " times");
            else
                System.out.println((++index)+") "+count + " times on " + url);
        }
    }

    public void sortWordList() {

        /**
         *  Sort words in list by frequency
         */
        List<Map.Entry<String, Map<String, Integer>>> arrayList = new ArrayList(wordSequency.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<String, Map<String, Integer>>>() {
            @Override
            public int compare(Map.Entry<String, Map<String, Integer>> o1, Map.Entry<String, Map<String, Integer>> o2) {
                Set<Map.Entry<String, Integer>> entries1 = o1.getValue().entrySet();
                Iterator<Map.Entry<String, Integer>> iterator1 = entries1.iterator();
                Integer value1 = iterator1.next().getValue();

                Set<Map.Entry<String, Integer>> entries2 = o2.getValue().entrySet();
                Iterator<Map.Entry<String, Integer>> iterator2 = entries2.iterator();
                Integer value2 = iterator2.next().getValue();

                return value2.compareTo(value1);
            }
        });
        wordSequency.clear();
        /**
         * Sort URL for word by frequency
         */
        for (Map.Entry<String, Map<String, Integer>> entry : arrayList) {
            String key = entry.getKey();
            Map<String, Integer> value = entry.getValue();

            List<Map.Entry<String, Integer>> arrayList1 = new ArrayList(value.entrySet());
            Collections.sort(arrayList1, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            Iterator<Map.Entry<String, Integer>> iterator = arrayList1.iterator();

            value.clear();

            while (iterator.hasNext()) {
                Map.Entry<String, Integer> next = iterator.next();
                value.put(next.getKey(), next.getValue());
            }

            wordSequency.put(key, value);
        }
    }

    /**
     * Make word's list for the text and store them
     * @param text
     * @param url
     */
    public void getWordsList(String text, String url) {

        if(text==null || url==null)
            return;

        String[] words = text.split("[\\s=\\.\\-/><&)({}\\[\\];\"'_#+\\*,@:\\?\\|!]");
        System.out.print("Found: " + words.length + " words, ");
        int newWord = 0;

        for (String line : words) {
            if (line.trim().length() >= MIN_WORD_LENGTH) {

                Map<String, Integer> map = wordSequency.get(line);
                if (map != null) {
                    Integer value = map.get("total");
                    if (value == null)
                        value = 0;
                    value++;
                    map.put("total", value);

                    value = map.get(url);
                    if (value == null) {
                        value = 0;
                    }
                    value++;
                    map.put(url, value);

                } else {
                    map = new LinkedHashMap<>();
                    map.put("total", 1);
                    map.put(url, 1);
                    newWord++;
                }
                wordSequency.put(line, map);
            }
        }
        System.out.println("added new unique: " + newWord);
    }

    /**
     * Get site data as text
     *
     * @param siteUrl
     * @return
     */
    public String loadSite(String siteUrl) {
        System.out.print("Getting content");
        String result = dataProvider.getContent(siteUrl);
        System.out.print(" - OK ");
        return result;
    }

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }
}

package ua.epam.string.searching;

/**
 * написать поисковый сервис
 * по произвольному URL перейти и спомощью регулярного выражения
 * найти 20 URL каждому указываете ключевые слова с частотами по указаному слову
 * найти все URL упорядоченые по возрастанию частоты слова
 */
public class DemoApp {

    public static void main(String[] args) {
        Spider spider = new Spider();
        DataProvider dataProvider = new DataProvider();
        spider.setDataProvider(dataProvider);

        //String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        String siteUrl = "http://localhost:8080/";

        spider.scan(siteUrl);

        System.out.println("\n\nTop words list: ");
        spider.showTopWordList();

        System.out.println();
        String search = "mockito";
        System.out.println("Search for word "+search);
        spider.showWordStats(search);

        System.out.println();
        String maxFrequency = spider.getWordWithMAXStat();
        System.out.println("Word with max frequency:");
        spider.showWordStats(maxFrequency);


        System.out.println();
        String maxURLCount = spider.getWordWithMAXURLCounts();
        System.out.println("Word with max URL counts");
        spider.showWordStats(maxURLCount);
    }
}

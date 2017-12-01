package ua.epam.string.searching;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SpiderTest {

    String result = "Get information from site: http://www.vogella.com/tutorials/Mockito/article.htmlGetting content - OK Getting URL list - OK Found: 88 URLs Found: 23779 words, added new unique: 1251\n" +
            "1 Get information from site: http://www.vogella.com/tutorials/Mockito/article.html Getting content - OK Found: 23779 words, added new unique: 0\n" +
            "2 Get information from site: http://vg08.met.vgwort.de/na/27d90952a5254731bed50a1ef8bd6fb3 Getting content - OK Found: 3 words, added new unique: 1\n" +
            "java.io.IOException: Server returned HTTP response code: 403 for URL: http://creativecommons.org/licenses/by-nc-sa/3.0/de/deed.en\n" +
            "\tat sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1876)\n" +
            "\tat sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1474)\n" +
            "\tat ua.epam.string.searching.DataProvider.getContent(DataProvider.java:20)\n" +
            "\tat ua.epam.string.searching.DataProvider$MockitoMock$544250818.getContent$accessor$lKUyYo3Z(Unknown Source)\n" +
            "\tat ua.epam.string.searching.DataProvider$MockitoMock$544250818$auxiliary$FyX1zNiT.call(Unknown Source)\n" +
            "\tat org.mockito.internal.invocation.RealMethod$FromCallable.invoke(RealMethod.java:52)\n" +
            "\tat org.mockito.internal.creation.bytebuddy.InterceptedInvocation.callRealMethod(InterceptedInvocation.java:129)\n" +
            "\tat org.mockito.internal.stubbing.answers.CallsRealMethods.answer(CallsRealMethods.java:43)\n" +
            "\tat org.mockito.Answers.answer(Answers.java:100)\n" +
            "\tat org.mockito.internal.handler.MockHandlerImpl.handle(MockHandlerImpl.java:97)\n" +
            "\tat org.mockito.internal.handler.NullResultGuardian.handle(NullResultGuardian.java:29)\n" +
            "\tat org.mockito.internal.handler.InvocationNotifierHandler.handle(InvocationNotifierHandler.java:35)\n" +
            "\tat org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:65)\n" +
            "\tat org.mockito.internal.creation.bytebuddy.MockMethodInterceptor.doIntercept(MockMethodInterceptor.java:51)\n" +
            "\tat org.mockito.internal.creation.bytebuddy.MockMethodInterceptor$DispatcherDefaultingToRealMethod.interceptSuperCallable(MockMethodInterceptor.java:135)\n" +
            "\tat ua.epam.string.searching.DataProvider$MockitoMock$544250818.getContent(Unknown Source)\n" +
            "\tat ua.epam.string.searching.Spider.loadSite(Spider.java:247)\n" +
            "\tat ua.epam.string.searching.Spider.scan(Spider.java:48)\n" +
            "\tat ua.epam.string.searching.SpiderTest.scan(SpiderTest.java:74)\n" +
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
            "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
            "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
            "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
            "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)\n" +
            "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)\n" +
            "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)\n" +
            "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)\n" +
            "\tat org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:26)\n" +
            "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)\n" +
            "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)\n" +
            "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)\n" +
            "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
            "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
            "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
            "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
            "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
            "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
            "\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\n" +
            "\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\n" +
            "\tat com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)\n" +
            "\tat com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)\n" +
            "\tat com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)\n" +
            "3 Get information from site: http://creativecommons.org/licenses/by-nc-sa/3.0/de/deed.en Getting content - OK Found: 1 words, added new unique: 0\n" +
            "4 Get information from site: http://chiuki.github.io/advanced-android-espresso Getting content - OK Found: 13418 words, added new unique: 355\n" +
            "5 Get information from site: http://martinfowler.com/articles/mocksArentStubs.html Getting content - OK Found: 72 words, added new unique: 6\n" +
            "6 Get information from site: https://github.com/mockito/mockito/blob/master/doc/release-notes/official.md Getting content - OK Found: 102421 words, added new unique: 2025\n" +
            "7 Get information from site: https://github.com/mockito/mockito Getting content - OK Found: 57988 words, added new unique: 369\n" +
            "8 Get information from site: https://dzone.com/refcardz/mockito Getting content - OK Found: 83917 words, added new unique: 1548\n" +
            "9 Get information from site: http://site.mockito.org Getting content - OK Found: 7219 words, added new unique: 209\n" +
            "10 Get information from site: http://www.vogella.com/img/common/sourcecode.svg Getting content - OK Found: 70 words, added new unique: 6\n" +
            "11 Get information from site: http://www.vogella.com/img/common/license_new.svg Getting content - OK Found: 80 words, added new unique: 6\n" +
            "12 Get information from site: http://www.vogella.com/license.html Getting content - OK Found: 5630 words, added new unique: 93\n" +
            "13 Get information from site: http://www.vogella.com/img/common/discussions.svg Getting content - OK Found: 157 words, added new unique: 6\n" +
            "14 Get information from site: http://www.vogella.com/img/common/donate.svg Getting content - OK Found: 223 words, added new unique: 9\n" +
            "15 Get information from site: https://github.com/jayway/powermock/wiki/MockitoUsage Getting content - OK Found: 11307 words, added new unique: 65\n" +
            "16 Get information from site: https://github.com/mockito/mockito/wiki/FAQ#what-are-the-limitations-of-mockito Getting content - OK Found: 17091 words, added new unique: 145\n" +
            "17 Get information from site: https://github.com/mockito/mockito/wiki/Mockito-And-Private-Methods Getting content - OK Found: 11194 words, added new unique: 25\n" +
            "18 Get information from site: https://mvnrepository.com/artifact/org.hamcrest/hamcrest-library Getting content - OK Found: 4344 words, added new unique: 185\n" +
            "19 Get information from site: http://download.eclipse.org/tools/orbit/downloads Getting content - OK Found: 9682 words, added new unique: 141\n" +
            "20 Get information from site: http://search.maven.org Getting content - OK Found: 6595 words, added new unique: 160\n";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void scan() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        Spider spider = new Spider();
        DataProvider dataProvider = mock(DataProvider.class, CALLS_REAL_METHODS);
        //when(dataProvider.getContent("http://www.vogella.com/tutorials/Mockito/article.html")).thenReturn(result).thenCallRealMethod();
        spider.setDataProvider(dataProvider);
        spider.scan(siteUrl);
    }

    @Test
    public void getWordWithMAXURLCounts() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        Spider spider = new Spider();
        DataProvider dataProvider = mock(DataProvider.class);
        //when(dataProvider.getContent("http://www.vogella.com/tutorials/Mockito/article.html")).thenReturn(result).thenCallRealMethod();
        spider.setDataProvider(dataProvider);

        spider.getWordWithMAXURLCounts();
    }

    @Test
    public void getWordWithMAXStat() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        Spider spider = new Spider();
        DataProvider dataProvider = mock(DataProvider.class);
        //when(dataProvider.getContent("http://www.vogella.com/tutorials/Mockito/article.html")).thenReturn(result).thenCallRealMethod();
        spider.setDataProvider(dataProvider);

        spider.getWordWithMAXStat();
    }

    @Test
    public void showWordStats() throws Exception {
    }

    @Test
    public void showTopWordList() throws Exception {
    }

    @Test
    public void sortWordList() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        Spider spider = new Spider();
        DataProvider dataProvider = mock(DataProvider.class);
        //when(dataProvider.getContent("http://www.vogella.com/tutorials/Mockito/article.html")).thenReturn(result).thenCallRealMethod();
        spider.setDataProvider(dataProvider);

        spider.sortWordList();
    }

    @Test
    public void getWordsList() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";
        Spider spider = new Spider();
        DataProvider dataProvider = mock(DataProvider.class);
        //when(dataProvider.getContent("http://www.vogella.com/tutorials/Mockito/article.html")).thenReturn(result).thenCallRealMethod();
        spider.setDataProvider(dataProvider);

        spider.getWordsList(null,null);
    }

    @Test
    public void loadSite() throws Exception {
        String siteUrl = "http://www.vogella.com/tutorials/Mockito/article.html";

        Spider spider = mock(Spider.class);
        when(spider.loadSite(siteUrl)).thenReturn(result);
        String lines = spider.loadSite(siteUrl);

    }

}
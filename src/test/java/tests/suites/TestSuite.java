package tests.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.ChangeAppConditionTests;
import tests.ListTests;
import tests.SearchTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //ArticleTests.class,
        //SearchTests.class,
        ListTests.class
        //ChangeAppConditionTests.class
})
public class TestSuite {
}

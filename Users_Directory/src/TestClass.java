import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import ConsoleOperations.*;
import Catalog.*;
import java.io.IOException;

/**
 * Created by 1231 on 31.03.2015.
 */

    public class TestClass extends TestCase {

    static Catalog catalog = new Catalog();
    static  OperationsUser operationsUser=new OperationsUser();
    static Authorization authorization=new Authorization();

    public TestClass(String testName){
        super(testName);
    }

    /*проверка пользователя на существование в каталоге с заданым логином и паролем*/
    public void testExistUser() throws IOException, ClassNotFoundException {

        assertTrue(authorization.existUser("rex", 666));
    }

    /*проверка, пуст ли каталог*/
    public void testCatalogEmpty(){

        assertNotNull("Catalog is empty!", catalog.getUsers());
    }

    public static void main(String[]args){
        TestRunner runner=new TestRunner();
        TestSuite suite=new TestSuite();
        suite.addTest(new TestClass("testExistUser"));
        suite.addTest(new TestClass("testCatalogEmpty"));
        runner.doRun(suite);
    }
}

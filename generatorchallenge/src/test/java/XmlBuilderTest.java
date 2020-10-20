import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

import static org.junit.Assert.fail;

/*
* Three test created together with the test class(Person)
* test1 is with the object of the class person
* test2 is with the object Department
*
*
* */
public class XmlBuilderTest {

    Person testPerson = new Person();
    Department testDepartment = new Department();
    Country testCountry = new Country();
    EmptyClass testEmpty = new EmptyClass();

    XmlBuilder test1,test2,test3,test4;

    @Before
    public void prepareBean(){
        test1 = new XmlBuilder(testPerson.getClass());
        test2 = new XmlBuilder(testDepartment.getClass());
        test3 = new XmlBuilder(testCountry.getClass());
        test4 = new XmlBuilder(testEmpty.getClass());
    }

    @Test
    public void testPerson() {
        String xml = test1.buildXml();
        test(xml);
    }

    @Test
    public void testDepartment() {
        String xml = test2.buildXml();
        test(xml);
    }

    // Test class country
    //class country has no annotation and hence returns a string of form with no values
    @Test
    public void testCountry() {
        String xml = test3.buildXml();
        test(xml);
    }

    // Test Empty Class
    //class country has no annotation and hence returns a string of form with no values
    @Test
    public void testEmptyClass() {
        String xml = test4.buildXml();
        test(xml);
    }

    /*
    * This methods prints a failed test or print the xml(when test is passed)
    * 
    * */
    public void test(String xml){
        String emptyClass = "<h:form id=\"formEmptyClass\">\n" +
                "</h:form>";
        String emptyMethod = "<h:form id=\"formCountry\">\n" +
                "\t<util:inputText\n" +
                "\t />\n" +
                "\t<util:inputText\n" +
                "\t />\n" +
                "\t<util:inputNumber\n" +
                "\t />\n" +
                "\t<util:inputText\n" +
                "\t />\n" +
                "\t<util:inputText\n" +
                "\t />\n" +
                "</h:form>";
        if(xml.equals("")){
            fail("The class has no annotations");
        }
        //When methods have no annotations
        else if(xml.equals(emptyClass)){
            fail("The class has no annotations, using class name for forms");
            System.out.println(xml);
        }
        else{
            System.out.println(xml);

        }
    }



}
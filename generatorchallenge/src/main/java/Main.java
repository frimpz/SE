/**
 *
 * @author Frimpong boadu
 */

public class Main  {

    public static void main(String[] args){

        Person obj = new Person();
        XmlBuilder xmlBuilder = new XmlBuilder(obj.getClass());
        xmlBuilder.buildXml();
        System.out.println(xmlBuilder.getXml());
    }

}

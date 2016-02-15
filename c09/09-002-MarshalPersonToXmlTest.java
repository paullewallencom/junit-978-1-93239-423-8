package junit.cookbook.xmlunit.test;

import java.io.StringWriter;
import junit.cookbook.xmlunit.*;
import org.custommonkey.xmlunit.XMLTestCase;

public class MarshalPersonToXmlTest extends XMLTestCase {
    public void testJbRainsberger() throws Exception {
        Person person = new Person("J. B.", "Rainsberger");
        XmlMarshaller marshaller = new XmlMarshaller(
            Person.class, "person");
        StringWriter output = new StringWriter();
        marshaller.marshal(person, output);

        String xmlDocumentAsString = output.toString();

        assertXpathExists("/person", xmlDocumentAsString);

        assertXpathEvaluatesTo(
            "J. B.",
            "/person/firstName",
            xmlDocumentAsString);

        assertXpathEvaluatesTo(
            "Rainsberger",
            "/person/lastName",
            xmlDocumentAsString);
    }
}
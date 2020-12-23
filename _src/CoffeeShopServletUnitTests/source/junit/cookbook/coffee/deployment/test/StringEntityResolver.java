package junit.cookbook.coffee.deployment.test;

import java.io.*;

import org.xml.sax.*;

public class StringEntityResolver implements EntityResolver {
	private String entityText;
    
    public StringEntityResolver(String entityText) {
        this.entityText = entityText;
    }
    
	public InputSource resolveEntity(String publicId, String systemId)
		throws SAXException, IOException {

		return new InputSource(new StringReader(entityText));
	}
}

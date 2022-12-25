package xml.task1.parsers;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.*;

public class Preprocessing extends DefaultHandler {
    private Set<String> tags = new HashSet<>();

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        tags.add(qName);
    }
}
import java.net.URL;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.IOException;

class XMLTestParser implements ContentHandler {
        
        private Locator locator;
        private boolean useLocator;
        
        private XMLTestParser() {}
        
        public static String loadRule(URL file, FromFileRule rule) {
            XMLReader xml = null;
            try {
                xml = XMLReaderFactory.createXMLReader();
            } catch(SAXException saxe) {
                return "SAXException in XMLReaderFactory.createXMLReader(): " + saxe.getMessage();
            }
            xml.setContentHandler(new XMLTestParser());
            try {
                xml.parse(file.toString());
            } catch(IOException ioe) {
                return "IOException in parse(String systemId): " + ioe.getMessage();
            } catch(SAXException saxe) {
                return "SAXException in parse(String systemId): " + saxe.getMessage();
            }
            return "";
        }
        
        public void characters(char[] ch, int start, int length) {
            StringBuilder chars = new StringBuilder();
            chars.append("[");
            for(int i = 0; i < length; i++) {
                chars.append(", '");
                chars.append(ch[i+start]);
                chars.append("'");
            }
            chars.append("]");
            chars.delete(1, 3);
            System.err.println("characters(" + chars + ", " + start + ", " + length + ")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void endDocument() {
            System.err.println("endDocument()");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void endElement(String uri, String localName, String qName) {
            System.err.println("endElement(\"" + uri + "\", \"" + localName + "\", \"" + qName + "\")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void endPrefixMapping(String prefix) {
            System.err.println("endPrefixMapping(\"" + prefix + "\")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void ignorableWhitespace(char[] ch, int start, int length) {
            StringBuilder chars = new StringBuilder();
            chars.append("[");
            for(int i = 0; i < length; i++) {
                chars.append(", '");
                chars.append(ch[i+start]);
                chars.append("'");
            }
            chars.append("]");
            chars.delete(2, 4);
            System.err.println("ignorableWhitespace(" + chars + ", " + start + ", " + length + ")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void processingInstruction(String target, String data) {
            System.err.println("processingInstruction(\"" + target + "\", \"" + data + "\")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void setDocumentLocator(Locator locator) {
            System.err.println("Got Locator!");
            System.err.println();
            this.locator = locator;
        }
        
        public void skippedEntity(String name) {
            System.err.println("skippedEntity(\"" + name + "\")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void startDocument() {
            useLocator = locator != null;
            System.err.println("startDocument()");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void startElement(String uri, String localName, String qName, Attributes atts) {
            System.err.println("startElement(\"" + uri + "\", \"" + localName + "\", \"" + qName + "\")");
            if(atts.getLength() != 0) {
                StringBuilder attributes = new StringBuilder();
                for(int i = 0; i < atts.getLength(); i++) {
                    attributes.append("Attribute: [\"");
                    attributes.append(atts.getURI(i));
                    attributes.append("\", \"");
                    attributes.append(atts.getLocalName(i));
                    attributes.append("\", \"");
                    attributes.append(atts.getQName(i));
                    attributes.append("\", \"");
                    attributes.append(atts.getType(i));
                    attributes.append("\", \"");
                    attributes.append(atts.getValue(i));
                    attributes.append("\"]\n");
                }
                attributes.deleteCharAt(attributes.length() - 1);
            }
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
        
        public void startPrefixMapping(String prefix, String uri) {
            System.err.println("startPrefixMapping(\"" + prefix + "\", \"" + uri + "\")");
            if(useLocator) System.err.println("Locator: [\"" + locator.getPublicId() + "\", \"" + locator.getSystemId() + "\", \"" + locator.getLineNumber() + "\", \"" + locator.getColumnNumber() + "\"]");
            System.err.println();
        }
    }
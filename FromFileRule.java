import java.awt.Color;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.IOException;

/**
 * A Class that processes and represents a Rule loaded from a file
 * Should I use a static method to return a new reference and read xml there or should I do that in RulesFromFile <--Leaning slightly toward this
 * @see <a href="http://totheriver.com/learn/xml/xmltutorial.html">xml</a>
 * @author 1000000000 
 */
public class FromFileRule implements Rules
{
    private String name;
    private int defaultCellState;
    
    private HashMap<Character,Integer> readTable = new HashMap<Character,Integer>();
    private HashMap<Integer,Integer> ruleConversionTable = new HashMap<Integer,Integer>();
    //would it be better to use Arrays and have one HashMap as a sort of phonebook for the Array vaules?
    
    private int[] acceptableCellStates; //Do we really need this it is the key set of most of our HashMaps
    private HashMap<Integer,Color> cellStateColors = new HashMap<Integer,Color>();
    private HashMap<Integer,Integer> cellStateNeighborVals = new HashMap<Integer,Integer>();
    
    /*private HashMap<Integer,Integer> cellStates = new HashMap<Integer,Integer>(); // Used to get "address" of a particular cell state for use in cellStateColors and 
    private Color[] cellStateColors;
    private int[] cellStateNeighborVals;*/
    
    
    private FromFileRule() {}
    
    public static FromFileRule loadRuleFile(URL ruleFile) {
        FromFileRule rule = new FromFileRule();
        XMLRuleParser parser = new XMLRuleParser();
        String error = parser.loadRule(ruleFile, rule);
        if(error != "") {
            System.err.println("[Error Parsing XML Rule]: " + error);
            return null;
        }
        return rule;
    }
    
    
    public int getDefaultCellState() {
        return defaultCellState;
    }
    
    
    public int[] getAcceptableCellStates() {
        return acceptableCellStates;
    }
    
    
    public Color getCellColor(int cellState) {
        return cellStateColors.get(cellState);
    }
    
    
    public int getNeighborValue(int cellState) {
        return cellStateNeighborVals.get(cellState);
    }
    
    
    public int getCellState(int cellState, int neighborCount) {
        return 0;
    }
    
    
    public int getCellState(int cellState) {
        return ruleConversionTable.get(cellState);
    }
    
    
    public int readCellState(char c) {
        return readTable.get(c);
    }
    
    public boolean loadRule() {return false;}
    
    private boolean isDataValid() {
        return (acceptableCellStates != null && cellStateColors.size() == acceptableCellStates.length && isNeighborValuesValid());
    }
    
    private boolean isNeighborValuesValid() {
        return false;
    }
    
    public String toString() {
        return name;
    }
    
    private static class XMLRuleParser implements ContentHandler {
        
        private Locator locator;
        private boolean useLocator;
        private String path = "";
        private FromFileRule rule;
        
        
        public String loadRule(URL file, FromFileRule rule) {
            XMLReader xml = null;
            try {
                xml = XMLReaderFactory.createXMLReader();
            } catch(SAXException saxe) {
                return "SAXException in XMLReaderFactory.createXMLReader(): " + saxe.getMessage();
            }
            xml.setContentHandler(new XMLRuleParser());
            try {
                xml.parse(file.toString());
            } catch(IOException ioe) {
                return "IOException in parse(String systemId): " + ioe.getMessage();
            } catch(SAXException saxe) {
                return "SAXException in parse(String systemId): " + saxe.getMessage();
            }
            return "Unimplemented";
        }
        
        private void reportStartElement(String localName) {
            path += "/" + localName;
        }
        
        private void reportChars(String chars) {
            
        }
        
        private void reportEndElement(String localName) {
            path = path.substring(0, path.length() - localName.length());
        }
        
        public void characters(char[] ch, int start, int length) {
            reportChars(new String(ch,start,length));
        }
        
        public void endDocument() {
            
        }
        
        public void endElement(String uri, String localName, String qName) {
            reportEndElement(localName);
        }
        
        public void endPrefixMapping(String prefix) {
            
        }
        
        public void ignorableWhitespace(char[] ch, int start, int length) {
            
        }
        
        public void processingInstruction(String target, String data) {
            
        }
        
        public void setDocumentLocator(Locator locator) {
            System.out.println("Got Locator!");
            this.locator = locator;
        }
        
        public void skippedEntity(String name) {
            
        }
        
        public void startDocument() {
            useLocator = locator != null;
            System.err.println("startDocument()");
        }
        
        public void startElement(String uri, String localName, String qName, Attributes atts) {
           reportStartElement(localName);
        }
        
        public void startPrefixMapping(String prefix, String uri) {
           
        }
        
        private boolean hasCharacterData() {
            return false;
        }
    }
}

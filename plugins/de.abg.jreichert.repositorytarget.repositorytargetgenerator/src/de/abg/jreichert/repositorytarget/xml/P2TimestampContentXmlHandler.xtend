package de.abg.jreichert.repositorytarget.xml

import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class P2TimestampContentXmlHandler extends DefaultHandler {

   private String p2Timestamp = null;

   override characters(char[] ch, int start, int length)
         throws SAXException {
   }

	override startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
      if (localName.equals("property")) {
         val propertyName = atts.getValue("name")
         if("p2.timestamp".equals(propertyName)) {
            p2Timestamp = atts.getValue("value")
         }
      }
	}
	
	def getP2Timestamp() {
	   return p2Timestamp
	}
}

package de.abg.jreichert.repositorytarget.xml

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader
import org.xml.sax.ContentHandler
import org.xml.sax.InputSource
import org.xml.sax.helpers.XMLReaderFactory

import static extension com.google.common.io.CharStreams.*

class ContentParser {

	def String getContent(InputStream input) {
		val buf = new BufferedReader(new InputStreamReader(input))
		buf.readLines.join("\n")
	}
	
	def parse(String content, ContentHandler contentHandler) {
		val xmlReader = XMLReaderFactory::createXMLReader();
      	xmlReader.setContentHandler(contentHandler);
      	val reader = new StringReader(content.toString)
      	val inputSource = new InputSource(reader);
      	xmlReader.parse(inputSource);
	}
}
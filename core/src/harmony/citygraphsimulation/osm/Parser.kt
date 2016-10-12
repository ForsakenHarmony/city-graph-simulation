package harmony.citygraphsimulation.osm

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.File
import javax.xml.parsers.SAXParserFactory

/**
 * @author ForsakenHarmony
 */
object Parser {
  fun parse(file: File): Map? {
    val handler = handler
    
    try {
      SAXParserFactory.newInstance().newSAXParser().parse(file.inputStream(), handler)
    } catch (e: Exception) {
      e.printStackTrace()
      return null
    }
    
    return handler.map
  }
}

private object handler : DefaultHandler() {
  var map = Map()
  var indent = 0
  var currentElement: Element? = null
  
  override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
    if (attributes == null) {
      return
    }
    
    if (qName == "bounds") {
      map.minlat = attributes.getValue("minlat").toFloat()
      map.maxlat = attributes.getValue("maxlat").toFloat()
      map.minlon = attributes.getValue("minlon").toFloat()
      map.maxlon = attributes.getValue("maxlon").toFloat()
    }
    
    if (qName == "node") {
      val id = attributes.getValue("id").toLong()
      val lat = attributes.getValue("lat").toFloat()
      val long = attributes.getValue("lon").toFloat()
      val visible = attributes.getValue("visible").toBoolean()
      
      val node = Node(id, lat, long, visible)
      
      currentElement = node
      map.nodes.add(node)
    }
    
    if (qName == "way") {
      val id = attributes.getValue("id").toLong()
      val visible = attributes.getValue("visible").toBoolean()
      
      val way = Way(id, visible)
      
      currentElement = way
      map.ways.add(way)
    }
    
    if (qName == "relation") {
      val id = attributes.getValue("id").toLong()
      val visible = attributes.getValue("visible").toBoolean()
      
      val relation = Relation(id, visible)
      
      currentElement = relation
      map.relations.add(relation)
    }
    
    if (qName == "tag") {
      val key = attributes.getValue("k")
      val value = attributes.getValue("v")
      
      if (currentElement is Node) {
        (currentElement as Node).tags.put(key, value)
      } else if (currentElement is Way) {
        (currentElement as Way).tags.put(key, value)
      } else if (currentElement is Relation) {
        (currentElement as Relation).tags.put(key, value)
      }
    }
    
    if (qName == "nd") {
      val ref = attributes.getValue("ref").toLong()
      
      if (currentElement is Way) {
        (currentElement as Way).nodes.add(ref)
      }
    }
    
    if (qName == "member") {
      val type = attributes.getValue("type")
      val ref = attributes.getValue("ref").toLong()
      val role = attributes.getValue("ref")
      
      val member = Member(type, ref, role)
  
      if (currentElement is Relation) {
        (currentElement as Relation).members.add(member)
      }
    }
  }
  
  override fun endElement(uri: String?, localName: String?, qName: String?) {
    
  }
}
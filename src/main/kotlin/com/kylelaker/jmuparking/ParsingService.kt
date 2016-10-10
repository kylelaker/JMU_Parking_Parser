package com.kylelaker.jmuparking

import javax.xml.parsers.DocumentBuilderFactory

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

object ParsingService {

    val JMU_PARKING_XML = "http://www.jmu.edu/cgi-bin/parking_get_sign_data.cgi"

    fun parse(xmlUrl: String = JMU_PARKING_XML): List<Sign> {
        val doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(xmlUrl)
        doc.documentElement.normalize()

        val signNodeList = doc.getElementsByTagName("Sign")
        val signs: MutableList<Sign> = ArrayList()

        for (signNode in signNodeList) {
            val subElements = signNode.childNodes
            val id = subElements.findFirst("SignId").textContent.trim().toInt()
            val display = subElements.findFirst("Display").textContent.trim()
            signs.add(Sign(id, display))
        }

        return signs
    }

}

fun NodeList.findFirst(name: String): Node {
    for (node in this) if (node.nodeName.equals(name, true)) return node
    throw NoSuchElementException("The expected element, $name, was not found")
}

operator fun NodeList.iterator(): Iterator<Node> = object : Iterator<Node> {
    var index = 0
    override fun hasNext() = item(index + 1) != null
    override fun next() = if (hasNext()) item(index++) else throw NoSuchElementException()
}

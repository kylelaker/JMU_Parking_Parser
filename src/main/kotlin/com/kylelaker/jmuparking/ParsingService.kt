package com.kylelaker.jmuparking

import javax.xml.parsers.DocumentBuilderFactory

import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*

object ParsingService {

    val JMU_PARKING_XML = "https://www.jmu.edu/cgi-bin/parking_get_sign_data.cgi"

    fun parse(xmlUrl: String = JMU_PARKING_XML): Set<Sign> {
        val signs = TreeSet<Sign>()
        for (signNode in pullSignList(xmlUrl)) signs += nodeToSign(signNode)
        return signs
    }

    private fun textContent(list: NodeList, name: String) = list.findFirst(name).textContent.trim()

    private fun pullSignList(url: String) = DocumentBuilderFactory
            .newInstance()
            .newDocumentBuilder()
            .parse(url)
            .getElementsByTagName("Sign")

    private fun nodeToSign(node: Node): Sign {
        val nodes = node.childNodes
        val id = textContent(nodes, "SignID").toInt()
        val display = textContent(nodes, "Display")
        return Sign(id, display)
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

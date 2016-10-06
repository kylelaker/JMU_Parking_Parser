package com.kylelaker.jmuparking

import javax.xml.parsers.DocumentBuilderFactory

import org.w3c.dom.Node
import org.w3c.dom.NodeList

import java.util.ArrayList

object ParsingService {

    val JMU_PARKING_XML = "http://www.jmu.edu/cgi-bin/parking_get_sign_data.cgi"

    fun parse(xmlUrl: String = JMU_PARKING_XML): List<Sign> {

        val doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(xmlUrl)
        doc.documentElement.normalize()

        val nodeList = doc.getElementsByTagName("Sign")
        val signs: MutableList<Sign> = ArrayList<Sign>()

        for (i in 0..nodeList.length - 1) {
            val subElements = nodeList.item(i).childNodes
            val id = subElements.findFirst("SignId")?.textContent?.trim()?.toInt() ?: -1
            val display = subElements.findFirst("Display")?.textContent?.trim() ?: ""
            signs.add(Sign(id, display))
        }

        return signs
    }

    fun NodeList.findFirst(name: String): Node? {
        for (i in 0..this.length - 1) if (this.item(i).nodeName.equals(name, ignoreCase = true)) return this.item(i)
        return null
    }

}

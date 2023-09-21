package com.skyworth.framework.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Xml工具
 */
public class XmlHelper {
    public static String writeToString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error on converting to String", ex);
        }
    }

    public static boolean writeToFile(Document doc, String filePath) {
        try {
            FileWriter fw = new FileWriter(filePath);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(fw));
        } catch (Exception ex) {
//        	Logger.error(ex.toString());
            return false;
        }
        return true;
    }

    public static Document createDocFromString(String xml) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Create docBuilder error", e);
        }
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        try {
            Document doc = docBuilder.parse(is);
            return doc;
        } catch (SAXException e) {
            throw new RuntimeException("Create docBuilder error", e);
        } catch (IOException e) {
            throw new RuntimeException("Create docBuilder error", e);
        }
    }

    public static Document createDocFromFile(String path) {
        DocumentBuilder docBuilder;
        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Create docBuilder error", e);
        }
        try {
            Document doc = docBuilder.parse(new File(path));
            return doc;
        } catch (SAXException e) {
            throw new RuntimeException("Create docBuilder error", e);
        } catch (IOException e) {
            throw new RuntimeException("Create docBuilder error", e);
        }
    }

    public static Document createNewDoc() {
        DocumentBuilder docBuilder;
        try {
            docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Create docBuilder error", e);
        }
        Document doc = docBuilder.newDocument();
        return doc;
    }

    public static Node createAttributeNode(Document doc, String name, String value) {
        Node node = doc.createAttribute(name);
        if (node != null) {
            node.setNodeValue(value);
        }
        return node;
    }
}

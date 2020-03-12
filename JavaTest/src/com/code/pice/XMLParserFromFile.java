package com.code.pice;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * author: King.Z
 * date:  2016/2/25 10:55
 * description: XML解析
 *
 *  <  ?xml version="1.0"?>
    <students>
        <student>
            <name>John</name>
            <grade>B</grade>
            <age>12</age>
        </student>
        <student>
            <name>Mary</name>
            <grade>A</grade>
            <age>11</age>
        </student>
        <student>
            <name>Simon</name>
            <grade>A</grade>
            <age>18</age>
        </student>
    </students>
 */
public class XMLParserFromFile {

    public void getAllUserNames(String fileName) {
        try {
            //工厂模式
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(fileName);
            if (file.exists()) {
                //If the file is exists,parser the file，get the Element.
                Document doc = db.parse(file);
                Element docEle = doc.getDocumentElement();
                // Print root element of the document
                System.out.println("Root element of the document: " + docEle.getNodeName());
                //Get Nodelist by ElementsTagName
                NodeList studentList = docEle.getElementsByTagName("student");
                // Print total student elements in document
                System.out.println("Total students: " + studentList.getLength());
                //If the child node List is not null.
                if (studentList != null && studentList.getLength() > 0) {
                    for (int i = 0; i < studentList.getLength(); i++) {
                        Node node = studentList.item(i);
                        //judge NodeType
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            System.out.println("=====================");
                            Element e = (Element) node;
                            NodeList nodeList = e.getElementsByTagName("name");
                            System.out.println("Name: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            nodeList = e.getElementsByTagName("grade");
                            System.out.println("Grade: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());

                            nodeList = e.getElementsByTagName("age");
                            System.out.println("Age: "+ nodeList.item(0).getChildNodes().item(0).getNodeValue());
                        }
                    }
                } else {
                    System.exit(1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {

        XMLParserFromFile parser = new XMLParserFromFile();
        parser.getAllUserNames("c:\\test.xml");
    }
}

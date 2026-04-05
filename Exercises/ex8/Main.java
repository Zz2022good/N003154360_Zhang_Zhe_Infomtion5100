package Exercises.ex8;

import java.io.File;
import java.io.FileReader;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(" XML PARSER OUTPUT ");
            Document xmlDoc = parseXML("Exercises/ex8/books.xml");
            printXMLBooks(xmlDoc);

            System.out.println("\n XML AFTER ADDING NEW BOOK ");
            addBookToXML(
                    xmlDoc,
                    "The Added New Book 1",
                    2026,
                    500,
                    new String[]{"Zhe"}
            );
            printXMLBooks(xmlDoc);

            System.out.println("\nJSON PARSER OUTPUT");
            JSONObject jsonObject = parseJSON("Exercises/ex8/books.json");
            printJSONBooks(jsonObject);

            System.out.println("\nJSON AFTER ADDING NEW BOOK");
            addBookToJSON(
                    jsonObject,
                    "The Added New Book 2",
                    2026, 1000,
                    new String[]{"Zhang"}
            );
            printJSONBooks(jsonObject);

            System.out.println("\n FULL JSON DOCUMENT");
            System.out.println(jsonObject.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XML

    public static Document parseXML(String fileName) throws Exception {
        File file = new File(fileName);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        document.getDocumentElement().normalize();
        return document;
    }

    public static void printXMLBooks(Document document) {
        NodeList bookList = document.getElementsByTagName("Book");

        for (int i = 0; i < bookList.getLength(); i++) {
            Element book = (Element) bookList.item(i);

            String title = book.getElementsByTagName("title").item(0).getTextContent();
            String publishedYear = book.getElementsByTagName("publishedYear").item(0).getTextContent();
            String numberOfPages = book.getElementsByTagName("numberOfPages").item(0).getTextContent();

            System.out.println("Book " + (i + 1));
            System.out.println("Title: " + title);
            System.out.println("Published Year: " + publishedYear);
            System.out.println("Number of Pages: " + numberOfPages);
            System.out.print("Authors: ");

            Element authorsElement = (Element) book.getElementsByTagName("authors").item(0);
            NodeList authorList = authorsElement.getElementsByTagName("author");

            for (int j = 0; j < authorList.getLength(); j++) {
                System.out.print(authorList.item(j).getTextContent());
                if (j < authorList.getLength() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }

    public static void addBookToXML(Document document, String title, int publishedYear,
                                    int numberOfPages, String[] authors) {

        Element root = document.getDocumentElement();

        Element book = document.createElement("Book");

        Element titleElement = document.createElement("title");
        titleElement.appendChild(document.createTextNode(title));
        book.appendChild(titleElement);

        Element yearElement = document.createElement("publishedYear");
        yearElement.appendChild(document.createTextNode(String.valueOf(publishedYear)));
        book.appendChild(yearElement);

        Element pagesElement = document.createElement("numberOfPages");
        pagesElement.appendChild(document.createTextNode(String.valueOf(numberOfPages)));
        book.appendChild(pagesElement);

        Element authorsElement = document.createElement("authors");
        for (String authorName : authors) {
            Element authorElement = document.createElement("author");
            authorElement.appendChild(document.createTextNode(authorName));
            authorsElement.appendChild(authorElement);
        }
        book.appendChild(authorsElement);

        root.appendChild(book);
    }



    // JSON

    public static JSONObject parseJSON(String fileName) throws Exception {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(fileName);

        Object obj = parser.parse(reader);
        reader.close();

        return (JSONObject) obj;
    }

    public static void printJSONBooks(JSONObject jsonObject) {
        JSONArray bookArray = (JSONArray) jsonObject.get("BookShelf");

        for (int i = 0; i < bookArray.size(); i++) {
            JSONObject book = (JSONObject) bookArray.get(i);

            System.out.println("Book " + (i + 1));
            System.out.println("Title: " + book.get("title"));
            System.out.println("Published Year: " + book.get("publishedYear"));
            System.out.println("Number of Pages: " + book.get("numberOfPages"));

            System.out.print("Authors: ");
            JSONArray authors = (JSONArray) book.get("authors");

            for (int j = 0; j < authors.size(); j++) {
                System.out.print(authors.get(j));
                if (j < authors.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("\n");
        }
    }

    @SuppressWarnings("unchecked")
    public static void addBookToJSON(JSONObject jsonObject, String title, int publishedYear,
                                     int numberOfPages, String[] authors) {

        JSONArray bookArray = (JSONArray) jsonObject.get("BookShelf");

        JSONObject newBook = new JSONObject();
        newBook.put("title", title);
        newBook.put("publishedYear", publishedYear);
        newBook.put("numberOfPages", numberOfPages);

        JSONArray authorsArray = new JSONArray();
        for (String authorName : authors) {
            authorsArray.add(authorName);
        }

        newBook.put("authors", authorsArray);
        bookArray.add(newBook);
    }
}

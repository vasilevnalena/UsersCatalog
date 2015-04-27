package ConsoleOperations;

import ConstantStrings.Strings;
import UsersData.User;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.File;
/**
 * Created by 1231 on 01.04.2015.
 */
public class OperationsXML {

    private static DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    private static Strings message=new Strings();

    public OperationsXML() {

    }

    /*создание пустого документа*/
    private Document createDocument(Element rootElement){
        return new Document(rootElement);
    }

    /*удаление документа*/
    public void deleteDocument(File doc){
        doc.delete();
    }

    /*проверка наличия заданного атрибута по тегу*/
    public boolean findInXMLByTag(String nameXML,String tagXML,String contentTag)throws Exception {

        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        org.w3c.dom.Document document = builder.parse(new File(message.getPATH_TO_THE_XML_FILES()+nameXML));
        boolean flag=false;
        NodeList list = document.getElementsByTagName(tagXML);
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NamedNodeMap map = node.getAttributes();
            if (map.getNamedItem(tagXML).getNodeValue().equals(contentTag))
            {flag=true; break;}
        }
        return flag;
    }

    /*поиск пользователя в XML*/
    public File findUserInXML(String name, String login)throws Exception{
        File[] filesList;
        File foundXML=null;
        File filesPath = new java.io.File(message.getPATH_TO_THE_XML_FILES());
        filesList = filesPath.listFiles(); // записываем файлы из папки в массив объектов типа File
        for(int i=0; i<filesList.length; i++) {
            String buf = filesList[i].getName(); // читаем текущее имя файла
            if (buf.endsWith(".xml")){
                if(findInXMLByTag(buf,"name",name)&&(findInXMLByTag(buf,"login",login))){
                     foundXML=filesList[i];
                    break;
               }
            }
        }
        return foundXML;
    }

    /*создание xml-документа*/
    public Document createDocumentXML(User user, String nameXML) {

        Document doc=createDocument(addContentDocument(user));//создаем документ и заполняем

        try {
            XMLOutputter outputter = new XMLOutputter();//преобразовываем его в xml
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter(message.getPATH_TO_THE_XML_FILES()+nameXML+".xml");

            outputter.output(doc, fw);
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return doc;
    }

    /*заполнение xml-документа*/
    private Element addContentDocument(User user){
        Element rootElement = new Element("user");

        rootElement.addContent(new Element("name").setAttribute(new Attribute("name", "" + user.getName())));
        rootElement.addContent(new Element("surname").setAttribute(new Attribute("surname", "" + user.getSurname())));
        rootElement.addContent(new Element("birthday").setAttribute(new Attribute("birthday", "" + format.format(user.getBirthday()))));
        rootElement.addContent(new Element("familyStatus").setAttribute(new Attribute("familyStatus", "" + user.getFamilyStatus())));
        rootElement.addContent(new Element("profession").setAttribute(new Attribute("profession", "" + user.getProfessionUser())));
        rootElement.addContent(new Element("login").setAttribute(new Attribute("login", "" + user.getLogin())));
        rootElement.addContent(new Element("password").setAttribute(new Attribute("password", "" + user.getPassword())));
        rootElement.addContent(new Element("email").setAttribute(new Attribute("email", "" + user.getEmail())));

        return rootElement;
    }

    }



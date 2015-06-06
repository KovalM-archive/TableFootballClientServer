package edit;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tablemodel.StudentModel;
import tablemodel.StudentsList;


import javax.swing.JTabbedPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

public class XMLFile {
    private Element rootElement;
    private String nameFile;
    private StudentsList studentsList;

    public XMLFile(String nameFile, StudentsList studentsList) {
        this.nameFile = nameFile;
        this.studentsList = studentsList;
    }

    public void writeFile() throws IOException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.newDocument();

        rootElement = document.createElement(XMLTagConst.TABLE);
        document.appendChild(rootElement);

        List<StudentModel> students = studentsList.getStudentList();

        for (StudentModel studentModel : students) {
            Element currentStudent = document.createElement(XMLTagConst.STUDENT);
            currentStudent.setAttribute(XMLTagConst.FIRST_NAME, studentModel.getFirstName());
            currentStudent.setAttribute(XMLTagConst.SECOND_NAME, studentModel.getSecondName());
            currentStudent.setAttribute(XMLTagConst.THIRD_NAME, studentModel.getThirdName());
            currentStudent.setAttribute(XMLTagConst.DAY,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.DAY_OF_MONTH)));
            currentStudent.setAttribute(XMLTagConst.MONTH,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.MONTH)));
            currentStudent.setAttribute(XMLTagConst.YEAR,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.YEAR)));
            currentStudent.setAttribute(XMLTagConst.FOOTBALL_TEAM, studentModel.getFootballTeamName());
            currentStudent.setAttribute(XMLTagConst.FACULTY, studentModel.getFacultyName());
            currentStudent.setAttribute(XMLTagConst.SQUAD,
                    String.valueOf(studentModel.getSquad()));
            currentStudent.setAttribute(XMLTagConst.POSITION,
                    String.valueOf(studentModel.getPosition()));

            rootElement.appendChild(currentStudent);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Properties outFormat = new Properties();
            outFormat.setProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperties(outFormat);
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(nameFile));
            transformer.transform(source, result);
        }
    }

    public void readFile(){
        try {
            File xmlFile = new File(nameFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList studentListXML = document.getElementsByTagName(XMLTagConst.STUDENT);
            for (int temp = 0; temp < studentListXML.getLength(); temp++) {
                Node nNode = studentListXML.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    StudentModel currentStudent = new StudentModel();
                    currentStudent.setFirstName(eElement.getAttribute(XMLTagConst.FIRST_NAME));
                    currentStudent.setSecondName(eElement.getAttribute(XMLTagConst.SECOND_NAME));
                    currentStudent.setThirdName(eElement.getAttribute(XMLTagConst.THIRD_NAME));
                    currentStudent.setDateBirth(new GregorianCalendar(
                            Integer.parseInt(eElement.getAttribute(XMLTagConst.YEAR)),
                            Integer.parseInt(eElement.getAttribute(XMLTagConst.MONTH)),
                            Integer.parseInt(eElement.getAttribute(XMLTagConst.DAY))));
                    currentStudent.setFootballTeamName(eElement.getAttribute(XMLTagConst.FOOTBALL_TEAM));
                    currentStudent.setFacultyName(eElement.getAttribute(XMLTagConst.FACULTY));
                    currentStudent.setSquad(Integer.parseInt(eElement.getAttribute(XMLTagConst.SQUAD)));
                    currentStudent.setPosition(Integer.parseInt(eElement.getAttribute(XMLTagConst.POSITION)));
                    studentsList.addStudent(currentStudent);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


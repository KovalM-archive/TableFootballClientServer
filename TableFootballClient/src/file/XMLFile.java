package file;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tablemodel.StudentModel;
import tableview.StudentTableModel;
import tableview.TablePanel;
import tableview.ChangeTablePanel;
import tableview.StudentTableView;

import javax.swing.JScrollPane;
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
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

public class XMLFile {
    private Element rootElement;
    private String nameFile;
    private JTabbedPane tableTab;

    public XMLFile(String nameFile, JTabbedPane tableTab) {
        this.nameFile = nameFile;
        this.tableTab = tableTab;
    }

    public void writeFile() throws IOException, TransformerException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.newDocument();

        rootElement = document.createElement(XMLTag.TABLE);
        document.appendChild(rootElement);

        TablePanel tablePanel = (TablePanel)tableTab.getSelectedComponent();
        StudentTableView tableView = tablePanel.getTableView();

        List<StudentModel> students = tableView.getStudentList();

        for (StudentModel studentModel : students) {
            Element currentStudent = document.createElement(XMLTag.STUDENT);
            currentStudent.setAttribute(XMLTag.FIRST_NAME, studentModel.getFirstName());
            currentStudent.setAttribute(XMLTag.SECOND_NAME, studentModel.getSecondName());
            currentStudent.setAttribute(XMLTag.THIRD_NAME, studentModel.getThirdName());
            currentStudent.setAttribute(XMLTag.DAY,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.DAY_OF_MONTH)));
            currentStudent.setAttribute(XMLTag.MONTH,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.MONTH)));
            currentStudent.setAttribute(XMLTag.YEAR,
                    String.valueOf(studentModel.getDateBirth().get(GregorianCalendar.YEAR)));
            currentStudent.setAttribute(XMLTag.FOOTBALL_TEAM, studentModel.getFootballTeamName());
            currentStudent.setAttribute(XMLTag.FACULTY, studentModel.getFacultyName());
            currentStudent.setAttribute(XMLTag.SQUAD,
                    String.valueOf(studentModel.getSquad()));
            currentStudent.setAttribute(XMLTag.POSITION,
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

    public void readFile(String nameHeader){
        try {
            File xmlFile = new File(nameFile);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();

            StudentTableModel tableModel = new StudentTableModel(new ArrayList<StudentModel>());
            StudentTableView tableView = new StudentTableView(tableModel,new Socket());

            ChangeTablePanel changeTablePanel = new ChangeTablePanel(tableView);
            TablePanel mainPage = new TablePanel(tableView, changeTablePanel);
            mainPage.setLayout(new BorderLayout());
            JScrollPane scrollpane = new JScrollPane(tableView);
            mainPage.add(scrollpane, BorderLayout.CENTER);
            mainPage.add(changeTablePanel, BorderLayout.SOUTH);

            tableTab.add(nameHeader, mainPage);
            tableTab.setSelectedComponent(mainPage);

            NodeList studentList = document.getElementsByTagName(XMLTag.STUDENT);
            for (int temp = 0; temp < studentList.getLength(); temp++) {
                Node nNode = studentList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    StudentModel currentStudent = new StudentModel();
                    currentStudent.setFirstName(eElement.getAttribute(XMLTag.FIRST_NAME));
                    currentStudent.setSecondName(eElement.getAttribute(XMLTag.SECOND_NAME));
                    currentStudent.setThirdName(eElement.getAttribute(XMLTag.THIRD_NAME));
                    currentStudent.setDateBirth(new GregorianCalendar(
                            Integer.parseInt(eElement.getAttribute(XMLTag.YEAR)),
                            Integer.parseInt(eElement.getAttribute(XMLTag.MONTH)),
                            Integer.parseInt(eElement.getAttribute(XMLTag.DAY))));
                    currentStudent.setFootballTeamName(eElement.getAttribute(XMLTag.FOOTBALL_TEAM));
                    currentStudent.setFacultyName(eElement.getAttribute(XMLTag.FACULTY));
                    currentStudent.setSquad(Integer.parseInt(eElement.getAttribute(XMLTag.SQUAD)));
                    currentStudent.setPosition(Integer.parseInt(eElement.getAttribute(XMLTag.POSITION)));
                    tableView.addStudent(currentStudent);
                }
            }
            mainPage.getChangeTablePanel().getAllRecord().setText(
                    String.valueOf(mainPage.getTableView().getCountRecord()));
            mainPage.getChangeTablePanel().getAllPage().setText(
                    String.valueOf(mainPage.getTableView().getNumberPage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


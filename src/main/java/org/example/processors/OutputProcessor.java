package org.example.processors;

import org.example.model.Group;
import org.example.model.Student;
import org.example.model.Subject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class OutputProcessor {
    private String outputFile;
    private Group group;

    public OutputProcessor(String outputFile, Group group) {
        this.outputFile = outputFile;
        this.group = group;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void writeInXML()  {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            ArrayList<Student> studentsList = group.getStudentList();
            Element groupElement = document.createElement("group");
            document.appendChild(groupElement);
            for (int i = 0; i < studentsList.size(); i++) {
                Element student = document.createElement("student");
                student.setAttribute("firstname", studentsList.get(i).getFirstName());
                student.setAttribute("lastname", studentsList.get(i).getLastName());
                student.setAttribute("groupnumber", studentsList.get(i).getGroupNumber());
                groupElement.appendChild(student);
                ArrayList<Subject> subjectsList = studentsList.get(i).getSubjects();
                for (int j = 0; j < subjectsList.size(); j++) {
                    Element subject = document.createElement("subject");
                    subject.setAttribute("title", subjectsList.get(j).getTitle());
                    subject.setAttribute("mark", Integer.toString(subjectsList.get(j).getMark()));
                    student.appendChild(subject);
                }
                Element average = document.createElement("average");
                average.setTextContent(Float.toString(studentsList.get(i).getAvg()));
                student.appendChild(average);
            }

            //SAVING
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(document), new StreamResult(new FileOutputStream(outputFile)));
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Enter the correct title!");
        }

    }
}

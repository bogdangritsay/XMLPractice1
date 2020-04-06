package org.example.processors;

import org.example.model.Group;
import org.example.model.Student;
import org.example.model.Subject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class InputProcessor {
    private String inputFile;

    public InputProcessor(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public Group parseXML() {

        try {
            File inputFile = new File(getInputFile());

            //---------------DOM---------------
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);

            Group group = new Group();

            //PARSING STUDENTS
            ArrayList<Student> students = new ArrayList<>();

            NodeList studentsList = document.getElementsByTagName("student");
            for (int i = 0; i < studentsList.getLength(); i++) {
                Node currentStudent = studentsList.item(i);

                if (currentStudent.getNodeType() == Node.ELEMENT_NODE) {
                    Element studentElement = (Element) currentStudent;
                    Student student = new Student();
                    student.setFirstName(studentElement.getAttribute("firstname"));
                    student.setLastName(studentElement.getAttribute("lastname"));
                    student.setGroupNumber(studentElement.getAttribute("groupnumber"));

                    //PARSING SUBJECTS
                    NodeList subjectElements = studentElement.getElementsByTagName("subject");
                    ArrayList<Subject> subjects = new ArrayList<>();

                    for (int j = 0; j < subjectElements.getLength(); j++) {
                        Node currentSubject = subjectElements.item(j);

                        if (currentSubject.getNodeType() == Node.ELEMENT_NODE) {
                            Element subjectElement = (Element) currentSubject;
                            Subject subject = new Subject(subjectElement.getAttribute("title"), Integer.parseInt(subjectElement.getAttribute("mark")));
                            subjects.add(subject);
                        }
                    }
                    student.setSubjects(subjects);
                    NodeList average = studentElement.getElementsByTagName("average");
                    Node currentAVG = average.item(0);
                    Element avgElement = (Element) currentAVG;
                    float avg = Float.parseFloat(avgElement.getTextContent());
                    System.out.println("AVG FROM THE FILE: " + avg + " for: " + student.getFirstName() + " " + student.getLastName());
                    if(!Student.compareAVG(avg, student.calculateAverage())) {
                        avg = new BigDecimal(student.calculateAverage()).setScale(2, RoundingMode.DOWN).floatValue();
                        System.out.println("AVG value is incorrect! The correct value (" + avg + ") is set." +
                                "\n----------------------------------");
                    } else {
                        System.out.println("AVG value is correct!\n----------------------------------");
                    }

                    student.setAvg(avg);
                    students.add(student);
                }

            }
            group.setStudentList(students);
            return group;


        } catch (ParserConfigurationException  | SAXException | IOException e) {
            System.out.println("Enter the correct title!");
        }
        return null;
    }
}

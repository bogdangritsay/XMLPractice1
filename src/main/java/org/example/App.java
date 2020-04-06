package org.example;

import org.example.model.Group;
import org.example.processors.InputProcessor;
import org.example.processors.OutputProcessor;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, TransformerException, ParserConfigurationException {
        Scanner sc = new Scanner(System.in);
        String inFile;
        String outFile;
        System.out.println( "Hello! \n Enter the title of input file: " );
        inFile = sc.nextLine();
        System.out.println( "Enter the title of output file: " );
        outFile = sc.nextLine();


        try {
            InputProcessor inputXML = new InputProcessor(inFile);

            Group group = inputXML.parseXML();

            System.out.println("XML was been read!");

            OutputProcessor outputXML = new OutputProcessor(outFile, group);
            outputXML.writeInXML();
            System.out.println("XML was been written!");

        } catch (NullPointerException e) {
            System.out.println("Enter the correct title!");
        }
    }
}

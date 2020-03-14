package com.malli.nonweb.labs;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PDFMerger {
    private static Object InputStream;
    private static Object List;

    public static void main(String[] args) {

        String folderPath = "C:\\Malli\\temp";

        //Instantiating PDFMergerUtility class
        PDFMergerUtility merger = new PDFMergerUtility();

        //Setting the destination file
        merger.setDestinationFileName("C:\\Malli\\temp\\merged-"+System.currentTimeMillis()+".pdf");


        try {
            //adding the source files
            //Single file can also be added with addSource() method
            List<InputStream> pdfFiles = getFiles(folderPath);
            merger.addSources(pdfFiles);
            //Merging the two documents
            merger.mergeDocuments();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (COSVisitorException e) {
            e.printStackTrace();
        }
        System.out.println("Documents merged");
    }

    private static List<InputStream> getFiles(String folderPath) throws IOException {

        Stream<Path> files = Files.walk(Paths.get(folderPath));
        Stream<Path> temp = Files.walk(Paths.get(folderPath));
        System.out.println(temp.count());


        List<InputStream> allPDFFiles = files.filter(p -> p.toString().endsWith(".pdf")).map(p -> {
            try {
                return Files.newInputStream(p, StandardOpenOption.READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return allPDFFiles;
    }
}
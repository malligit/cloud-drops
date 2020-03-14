package com.malli.nonweb.labs;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
        import java.io.File;
        import java.io.IOException;
public class PDFMerger {
    public static void main(String[] args) throws IOException {
        File file1 = new File("C:\\EXAMPLES\\Demo1.pdf");
        File file2 = new File("C:\\EXAMPLES\\Demo2.pdf");

        //Instantiating PDFMergerUtility class
        PDFMergerUtility PDFmerger = new PDFMergerUtility();

        //Setting the destination file
        PDFmerger.setDestinationFileName("C:\\Examples\\merged.pdf");

        //adding the source files
        PDFmerger.addSource(file1);
        PDFmerger.addSource(file2);

        //Merging the two documents
        PDFmerger.mergeDocuments();
        System.out.println("Documents merged");
    }
}
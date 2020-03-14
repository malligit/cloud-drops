package com.malli.nonweb.labs;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.util.PDFMergerUtility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
        covertToPDF();
        //mergePDFs(folderPath);

    }

    private static void mergePDFs(String folderPath) {
        PDFMergerUtility merger = new PDFMergerUtility();
        //Setting the destination file
        merger.setDestinationFileName("C:\\Malli\\temp\\merged-" + System.currentTimeMillis() + ".pdf");


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

    private static void covertToPDF() {
        try {
            PDDocument document = new PDDocument();
            InputStream in = new FileInputStream(new File("C:\\Malli\\temp\\H1B-Ext-2020\\Malli\\IMG-2456.JPG"));
            BufferedImage bimg = ImageIO.read(in);
            float width = bimg.getWidth();
            float height = bimg.getHeight();
            PDPage page = new PDPage(new PDRectangle(width, height));
            document.addPage(page);
            PDXObjectImage img = new PDJpeg(document, new FileInputStream(new File("C:\\Malli\\temp\\H1B-Ext-2020\\Malli\\IMG-2456.JPG")));
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.drawImage(img, 0, 0);
            contentStream.close();
            in.close();

            document.save("C:\\Malli\\temp\\test1.pdf");
            document.close();
        } catch (Exception e) {

        }
        System.out.println("Converted to PDF");
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
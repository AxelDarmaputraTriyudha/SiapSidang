package com.RPL.SiapSidang.BAP;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class PDFController {

    @GetMapping("/generate-pdf")
    public void generatePdfWithImage(jakarta.servlet.http.HttpServletResponse response) throws IOException {
        // Set the response headers for PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=example_with_image.pdf");

        // Create a new PDF document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Load the image from resources
        File imageFile = ResourceUtils.getFile("classpath:static/images/header.png");
        PDImageXObject image = PDImageXObject.createFromFile(imageFile.getAbsolutePath(), document);

        // Start writing content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        
        // Set Image Header
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();
        // Image dimensions (scale to fit page width if necessary)
        float imageWidth = pageWidth; // Stretch the image to page width
        float imageHeight = image.getHeight() * (imageWidth / image.getWidth()); // Maintain aspect ratio
        // Position the image at the top of the page
        float x = 0; // Align to the left
        float y = pageHeight - imageHeight; // Position at the top
        // Add the image
        contentStream.drawImage(image, x, y, imageWidth, imageHeight);

        // Start
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, y - 20);
        contentStream.showText("Telah disediakan Review untuk Sidang Skripsi");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("NPM: 6182201001");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Nama: Testing");
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Topik: Tingkat Kewarasan Mahasiswa Semester 5");
        contentStream.endText();

        // Close the content stream
        contentStream.close();
        // Write the document to the response output stream
        document.save(response.getOutputStream());
        document.close();
    }
}
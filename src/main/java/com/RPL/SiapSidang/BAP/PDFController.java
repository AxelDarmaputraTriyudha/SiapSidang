package com.RPL.SiapSidang.BAP;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
public class PDFController {
    @Autowired
    private JDBCBAP jdbcbap;

    @GetMapping("/generatePDF/{npm}")
    public void generatePdf(@PathVariable String npm, jakarta.servlet.http.HttpServletResponse response, Model model) throws IOException {
        // Make the data of the pdf
        List<BAP> data = jdbcbap.findData(npm);
        String penguji1 = "", penguji2 = "", pembimbing1 = "", pembimbing2 = "", koord = "";
        Double nilaiPU1 = 0.0, nilaiPU2 = 0.0, nilaiPB = 0.0, nilaiKoord = 0.0;

        for (int i = 0; i<data.size(); i++){
            BAP dataNow = data.get(i);
            switch (dataNow.getPeran()) {
                case "PB1":
                    pembimbing1 = dataNow.getNama_dosen();
                    nilaiPB = dataNow.getNilai_pb();
                    break;
                case "PB2":
                    pembimbing2 = dataNow.getNama_dosen();
                    break;
                case "PU1":
                    penguji1 = dataNow.getNama_dosen();
                    nilaiPU1 = dataNow.getNilai_pu1();
                    break;
                case "PU2":
                    penguji2 = dataNow.getNama_dosen();
                    nilaiPU2 = dataNow.getNilai_pu2();
                    break;
                case "Koordinator":
                    koord = dataNow.getNama_dosen();
                    nilaiKoord = dataNow.getNilai_koord();
                    break;
                default:
                    break;
            }
        }
        NilaiBAP nilaiBAP = new NilaiBAP(penguji1, nilaiPU1, (nilaiPU1 * 35/100), penguji2, nilaiPU2, (nilaiPU2 * 30/100), pembimbing1, nilaiPB, (nilaiPB * 20/100), koord, nilaiKoord, (nilaiKoord * 10/100), pembimbing2, 0.0);
        nilaiBAP.setNilaiAkhir((nilaiPU1 * 35/100) + (nilaiPU2 * 30/100) + (nilaiPB * 20/100) + (nilaiKoord * 10/100));

        // set status dari bap nya
        jdbcbap.setStatusBAP(npm);
        model.addAttribute("statusBAP", data.get(0).getStatus_bap());

        // Set the response headers for PDF
        String fileName = "BAP_" + npm + ".pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.getOutputStream().flush();

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
        float imageWidth = pageWidth; // Stretch the image to page width
        float imageHeight = image.getHeight() * (imageWidth / image.getWidth()); // Maintain aspect ratio
        float x = 0; // Align to the left
        float y = pageHeight - imageHeight; // Position at the top
        contentStream.drawImage(image, x, y, imageWidth, imageHeight);

        // Add Text Below Image
        String text = "";
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, y - 20);
        contentStream.showText("Telah disediakan Review untuk Sidang Skripsi");
        contentStream.newLineAtOffset(0, -15);
        text = "NPM: " + data.get(0).getNpm();
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -15);
        text = "Nama: " + data.get(0).getNama();
        contentStream.showText(text);
        contentStream.newLineAtOffset(0, -15);
        text = "Judul: " + data.get(0).getJudul();
        contentStream.showText(text);
        contentStream.endText();

        // Draw Table
        float tableStartY = y - 80; // Adjusted Y position for table
        float tableWidth = 450; // Total width of the table
        float rowHeight = 20; // Height of each row
        float[] columnWidths = {225, 225}; // Widths for each column

        drawRow(contentStream, 70, tableStartY, tableWidth, columnWidths, "Pembimbing Utama/Tunggal", pembimbing1, rowHeight);
        drawRow(contentStream, 70, tableStartY - rowHeight, tableWidth, columnWidths, "Pembimbing Pendamping", pembimbing2, rowHeight);
        drawRow(contentStream, 70, tableStartY - 2 * rowHeight, tableWidth, columnWidths, "Ketua Tim Penguji", penguji1, rowHeight);
        drawRow(contentStream, 70, tableStartY - 3 * rowHeight, tableWidth, columnWidths, "Anggota Tim Penguji", penguji2, rowHeight);

        y = tableStartY - 4 * rowHeight;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(50, y - 30);
        contentStream.showText("Rekapitulasi Nilai Sidang Skripsi 2 sebagai berikut:");
        contentStream.endText();

        // Draw Table
        tableStartY = y - 50; // Adjusted Y position for table
        tableWidth = 450; // Total width of the table
        rowHeight = 20; // Height of each row
        float[] columnWidths2 = {30, 150, 90, 90, 90}; // Widths for each column

        drawRow2(contentStream, 70, tableStartY, tableWidth, columnWidths2, "No", "Peran", "Nilai", "Bobot", "Nilai Akhir", rowHeight);
        drawRow2(contentStream, 70, tableStartY - rowHeight, tableWidth, columnWidths2, "1", "Ketua Tim Penguji", String.valueOf(nilaiPU1), "35.00%", String.valueOf(nilaiBAP.getNaPenguji1()), rowHeight);
        drawRow2(contentStream, 70, tableStartY - 2 * rowHeight, tableWidth, columnWidths2, "2", "Anggota Tim Penguji", String.valueOf(nilaiPU2), "30.00%", String.valueOf(nilaiBAP.getNaPenguji2()), rowHeight);
        drawRow2(contentStream, 70, tableStartY - 3 * rowHeight, tableWidth, columnWidths2, "3", "Pembimbing", String.valueOf(nilaiPB), "20.00%", String.valueOf(nilaiBAP.getNaPembimbing1()), rowHeight);
        drawRow2(contentStream, 70, tableStartY - 4 * rowHeight, tableWidth, columnWidths2, "4", "Koordinatir Skripsi", String.valueOf(nilaiKoord), "10.00%", String.valueOf(nilaiBAP.getNaKoord()), rowHeight);
        drawRow2(contentStream, 70, tableStartY - 5 * rowHeight, tableWidth, columnWidths2, "", "Total", "", "100.00%", String.valueOf(nilaiBAP.getNilaiAkhir()), rowHeight);

        y = tableStartY - 5 * rowHeight;
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        // Posisi X untuk menempatkan teks di tengah
        double xNew = (630.00 - 242.736) / 2.00;
        x = (float) xNew;
        contentStream.newLineAtOffset(x, y - 50);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
        String formattedDate = today.format(formatter);
        text = "Ditetapkan di Bandung: " + formattedDate;
        contentStream.showText(text);
        contentStream.endText();

        // Draw Table
        tableStartY = y - 80; // Adjusted Y position for table
        tableWidth = 450; // Total width of the table
        rowHeight = 60; // Height of each row
        float[] columnWidths3 = {90, 90, 90, 80, 100}; // Widths for each column

        drawRow2(contentStream, 70, tableStartY, tableWidth, columnWidths3, "", "", "", "", "", rowHeight);
        drawRow2(contentStream, 70, tableStartY - rowHeight, tableWidth, columnWidths3, "Ketua Penguji", "Anggota Penguji", "Pembimbing", "Mahasiswa", "Koordinator Skripsi", 20);

        contentStream.close();
        document.save(response.getOutputStream());
        document.close();
    }

    private static void drawRow(PDPageContentStream contentStream, float startX, float startY, float tableWidth, float[] columnWidths, String col1Text, String col2Text, float rowHeight) throws IOException {
        // Draw Row Borders
        contentStream.setLineWidth(1);
        contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
        contentStream.stroke();

        // Draw Column Lines
        float x = startX;
        for (float columnWidth : columnWidths) {
            x += columnWidth;
            contentStream.moveTo(x, startY);
            contentStream.lineTo(x, startY - rowHeight);
            contentStream.stroke();
        }

        // Add Text to Columns
        float textY = startY - rowHeight + 5; // Adjust Y position for text padding
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 12);

        // Column 1 Text
        contentStream.newLineAtOffset(startX + 5, textY);
        contentStream.showText(col1Text);

        // Column 2 Text
        contentStream.newLineAtOffset(columnWidths[0], 0); // Shift to column 2
        contentStream.showText(col2Text);

        contentStream.endText();
    }

    private static void drawRow2(PDPageContentStream contentStream, float startX, float startY, float tableWidth, float[] columnWidths, String col1Text, String col2Text, String col3Text, String col4Text, String col5Text, float rowHeight) throws IOException {
        // Draw Row Borders
        contentStream.setLineWidth(1);
        contentStream.addRect(startX, startY - rowHeight, tableWidth, rowHeight);
        contentStream.stroke();

        // Draw Column Lines
        float x = startX;
        for (float columnWidth : columnWidths) {
            x += columnWidth;
            contentStream.moveTo(x, startY);
            contentStream.lineTo(x, startY - rowHeight);
            contentStream.stroke();
        }

        // Add Text to Columns
        float textY = startY - rowHeight + 5; // Adjust Y position for text padding
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 10);

        // Column 1 Text
        contentStream.newLineAtOffset(startX + 5, textY);
        contentStream.showText(col1Text);

        // Column 2 Text
        contentStream.newLineAtOffset(columnWidths[0], 0);
        contentStream.showText(col2Text);

        // Column 3 Text
        contentStream.newLineAtOffset(columnWidths[1], 0);
        contentStream.showText(col3Text);

        // Column 4 Text
        contentStream.newLineAtOffset(columnWidths[2], 0);
        contentStream.showText(col4Text);

        // Column 5 Text
        contentStream.newLineAtOffset(columnWidths[3], 0);
        contentStream.showText(col5Text);

        contentStream.endText();
    }


}
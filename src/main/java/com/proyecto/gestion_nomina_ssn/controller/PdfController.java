package com.proyecto.gestion_nomina_ssn.controller;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    @GetMapping("/generatepdf")
    public ResponseEntity<byte[]> generatePdf() {
        try {
            // Crear un documento PDF
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            Document document = new Document(pdfDocument);

            // Agregar contenido al documento PDF
            pdfDocument.addNewPage();

            Connection connection = getDatabaseConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT u.username, u.firstname, u.lastname, n.salary, n.payment_date FROM user u INNER JOIN nomina n ON u.id=n.id_user;");

            Table table = new Table(5);
            table.addHeaderCell("username");
            table.addHeaderCell("firstname");
            table.addHeaderCell("lastname");
            table.addHeaderCell("salary");
            table.addHeaderCell("payment_date");

            while (resultSet.next()) {
                table.addCell(resultSet.getString(1)); // username -> firstname
                table.addCell(resultSet.getString(2)); // firstname
                table.addCell(resultSet.getString(3)); // lastname
                table.addCell(resultSet.getString(4)); // salary
                table.addCell(resultSet.getString(5)); // payment_date
            }

            table.setTextAlignment(TextAlignment.CENTER);
            document.add(table);

            pdfDocument.close();

            // Configurar la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.inline().name("filename").build());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el PDF como una respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Connection getDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gestion_nomina_ssn?serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);

    }
}
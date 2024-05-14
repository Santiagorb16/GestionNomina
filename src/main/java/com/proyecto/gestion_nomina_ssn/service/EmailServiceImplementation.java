package com.proyecto.gestion_nomina_ssn.service;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplementation implements IEmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImplementation.class);
    private static final String SENDGRID_API_KEY = "SG.yw4XC9hmRUCwVlrlBDreGw.go3486TLReOLlpCcyVcCswNCH88MkGnUOHAo9A4TTLA";
    private static final String FROM_EMAIL = "joanbarraza_2015@hotmail.com";

    @Override
    @Scheduled(cron = "0 00 18 * * Mon-Sun", zone = "America/Bogota")
    public void sendPagoEmail() {

        Email from = new Email(FROM_EMAIL);
        String subject = "Pago de nomina - enviado desde Twilio";

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(outputStream);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            pdfDocument.addNewPage();

            Connection connection = getDatabaseConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT u.username, u.firstname, u.lastname, n.salary, n.payment_date FROM user u INNER JOIN nomina n ON u.id=n.id_user;");

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                Email to = new Email(username);
                Content content = new Content("text/plain", "Se envía el pago de la nomina. Saludos.");
                Mail mail = new Mail(from, subject, to, content);

                Table table = new Table(5);
                table.addHeaderCell("firstname");
                table.addHeaderCell("lastname");
                table.addHeaderCell("salary");
                table.addHeaderCell("payment_date");

                table.addCell(resultSet.getString(2));
                table.addCell(resultSet.getString(3));
                table.addCell(resultSet.getString(4));
                table.addCell(resultSet.getString(5));

                table.setTextAlignment(TextAlignment.CENTER);
                document.add(table);

                pdfDocument.close();

                ByteArrayOutputStream attachmentOutputStream = new ByteArrayOutputStream();
                attachmentOutputStream.write(outputStream.toByteArray());
                attachmentOutputStream.flush();

                String contentBase64 = java.util.Base64.getEncoder()
                        .encodeToString(attachmentOutputStream.toByteArray());

                Attachments attachment = new Attachments();
                attachment.setContent(contentBase64);
                attachment.setType("application/pdf");
                attachment.setFilename("pago.pdf");
                attachment.setDisposition("attachment");
                mail.addAttachments(attachment);

                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                LOGGER.info("Email sent successfully to {}. Status code: {}", username, response.getStatusCode());
                LOGGER.debug("Response body: {}", response.getBody());
                LOGGER.debug("Response headers: {}", response.getHeaders());
            }
        } catch (Exception e) {
            LOGGER.error("Error sending email", e);
        }
    }

    private Connection getDatabaseConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/gestion_nomina_ssn?serverTimezone=UTC";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
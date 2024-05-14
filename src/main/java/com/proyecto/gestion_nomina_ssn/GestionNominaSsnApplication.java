package com.proyecto.gestion_nomina_ssn;

import com.proyecto.gestion_nomina_ssn.service.EmailServiceImplementation;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionNominaSsnApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionNominaSsnApplication.class, args);
	}
}

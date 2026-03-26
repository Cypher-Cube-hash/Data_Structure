package com.example.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class Emailer {
    private Emailer(){}

    public static boolean sendEmail(String personsEmail, String otp){
        try{
            Dotenv dotenv = Dotenv.configure().load();


            String companyEmail = dotenv.get("COMPANY_EMAIL");
            String emailKey = dotenv.get("COMPANY_EMAIL_KEY");

            Email email = EmailBuilder.startingBlank()
                    .from(companyEmail)
                    .to(personsEmail)
                    .withSubject("Your OTP Code")
                    .withPlainText("Your OTP is: " + otp)
                    .buildEmail();

            MailerBuilder.withSMTPServer("smtp.gmail.com", 587, companyEmail, emailKey)
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .buildMailer()
                    .sendMail(email);

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

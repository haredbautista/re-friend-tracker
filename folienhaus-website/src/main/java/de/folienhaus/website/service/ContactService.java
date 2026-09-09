package de.folienhaus.website.service;

import de.folienhaus.website.config.CompanyProperties;
import de.folienhaus.website.domain.ContactRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Nimmt Kontaktanfragen entgegen.
 * <p>
 * Ist ein SMTP-Server konfiguriert ({@code spring.mail.host}), wird die Anfrage per E-Mail
 * an die Firmenadresse weitergeleitet. Ohne Mail-Konfiguration wird die Anfrage protokolliert
 * und im Speicher gehalten – so lässt sich die Website auch ohne Mailserver betreiben und testen.
 */
@Service
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ObjectProvider<JavaMailSender> mailSender;
    private final CompanyProperties company;
    private final List<ContactRequest> received = new CopyOnWriteArrayList<>();

    public ContactService(ObjectProvider<JavaMailSender> mailSender, CompanyProperties company) {
        this.mailSender = mailSender;
        this.company = company;
    }

    public ContactRequest submit(String name, String email, String phone, String vehicle, String service, String message) {
        ContactRequest request = new ContactRequest(LocalDateTime.now(), name, email, phone, vehicle, service, message);
        received.add(request);
        log.info("Neue Kontaktanfrage von {} <{}> zu '{}'", name, email, service);

        JavaMailSender sender = mailSender.getIfAvailable();
        if (sender != null) {
            sendMail(sender, request);
        } else {
            log.info("Kein Mailserver konfiguriert – Anfrage wurde nur protokolliert.");
        }
        return request;
    }

    private void sendMail(JavaMailSender sender, ContactRequest r) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(company.contact().email());
        mail.setReplyTo(r.email());
        mail.setSubject("Website-Anfrage: " + r.service() + " – " + r.name());
        mail.setText("""
                Neue Anfrage über die Website

                Name:      %s
                E-Mail:    %s
                Telefon:   %s
                Fahrzeug:  %s
                Leistung:  %s

                Nachricht:
                %s
                """.formatted(r.name(), r.email(), blankToDash(r.phone()), blankToDash(r.vehicle()), r.service(), r.message()));
        try {
            sender.send(mail);
        } catch (RuntimeException ex) {
            log.error("E-Mail-Versand der Kontaktanfrage fehlgeschlagen", ex);
        }
    }

    private static String blankToDash(String value) {
        return value == null || value.isBlank() ? "–" : value;
    }

    /** Für Tests und Diagnose: alle im Speicher gehaltenen Anfragen. */
    public List<ContactRequest> received() {
        return Collections.unmodifiableList(received);
    }
}

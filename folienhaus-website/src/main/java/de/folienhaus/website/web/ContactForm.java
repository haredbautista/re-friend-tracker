package de.folienhaus.website.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Formulardaten des Kontaktformulars mit Validierung.
 * {@code website} ist ein Honeypot-Feld gegen Spam-Bots – Menschen lassen es leer.
 */
public class ContactForm {

    @NotBlank(message = "Bitte geben Sie Ihren Namen an.")
    @Size(max = 100, message = "Der Name darf höchstens 100 Zeichen lang sein.")
    private String name;

    @NotBlank(message = "Bitte geben Sie Ihre E-Mail-Adresse an.")
    @Email(message = "Bitte geben Sie eine gültige E-Mail-Adresse an.")
    @Size(max = 200)
    private String email;

    @Size(max = 50, message = "Die Telefonnummer darf höchstens 50 Zeichen lang sein.")
    private String phone;

    @Size(max = 100, message = "Die Fahrzeugangabe darf höchstens 100 Zeichen lang sein.")
    private String vehicle;

    @NotBlank(message = "Bitte wählen Sie eine Leistung aus.")
    private String service;

    @NotBlank(message = "Bitte beschreiben Sie kurz Ihr Anliegen.")
    @Size(min = 10, max = 2000, message = "Die Nachricht muss zwischen 10 und 2000 Zeichen lang sein.")
    private String message;

    private boolean privacyAccepted;

    private String website;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isPrivacyAccepted() { return privacyAccepted; }
    public void setPrivacyAccepted(boolean privacyAccepted) { this.privacyAccepted = privacyAccepted; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}

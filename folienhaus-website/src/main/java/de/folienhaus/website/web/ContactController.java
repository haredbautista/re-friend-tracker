package de.folienhaus.website.web;

import de.folienhaus.website.service.ContactService;
import de.folienhaus.website.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Kontaktseite mit Anfrageformular (Post/Redirect/Get).
 */
@Controller
public class ContactController {

    private final ContactService contactService;
    private final ContentService content;

    public ContactController(ContactService contactService, ContentService content) {
        this.contactService = contactService;
        this.content = content;
    }

    @GetMapping("/kontakt")
    public String show(@RequestParam(name = "leistung", required = false) String preselected, Model model) {
        if (!model.containsAttribute("contactForm")) {
            ContactForm form = new ContactForm();
            content.serviceBySlug(preselected == null ? "" : preselected)
                    .ifPresent(s -> form.setService(s.title()));
            model.addAttribute("contactForm", form);
        }
        model.addAttribute("services", content.services());
        return "kontakt";
    }

    @PostMapping("/kontakt")
    public String submit(@Valid @ModelAttribute("contactForm") ContactForm form,
                         BindingResult binding,
                         RedirectAttributes redirect,
                         Model model) {
        if (form.getWebsite() != null && !form.getWebsite().isBlank()) {
            // Honeypot ausgefüllt: sehr wahrscheinlich ein Bot. Still verwerfen.
            return "redirect:/kontakt?gesendet";
        }
        if (!form.isPrivacyAccepted()) {
            binding.rejectValue("privacyAccepted", "privacy.required",
                    "Bitte stimmen Sie der Verarbeitung Ihrer Daten zu.");
        }
        if (binding.hasErrors()) {
            model.addAttribute("services", content.services());
            return "kontakt";
        }
        contactService.submit(form.getName(), form.getEmail(), form.getPhone(),
                form.getVehicle(), form.getService(), form.getMessage());
        redirect.addFlashAttribute("sent", true);
        return "redirect:/kontakt?gesendet";
    }
}

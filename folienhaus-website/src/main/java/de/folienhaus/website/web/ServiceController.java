package de.folienhaus.website.web;

import de.folienhaus.website.domain.ServiceOffering;
import de.folienhaus.website.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Leistungsübersicht und Detailseiten unter /leistungen.
 */
@Controller
public class ServiceController {

    private final ContentService content;

    public ServiceController(ContentService content) {
        this.content = content;
    }

    @GetMapping("/leistungen")
    public String overview(Model model) {
        model.addAttribute("services", content.services());
        return "leistungen/uebersicht";
    }

    @GetMapping("/leistungen/{slug}")
    public String detail(@PathVariable String slug, Model model) {
        ServiceOffering service = content.serviceBySlug(slug)
                .orElseThrow(() -> new PageNotFoundException("Leistung nicht gefunden: " + slug));
        model.addAttribute("service", service);
        model.addAttribute("otherServices",
                content.services().stream().filter(s -> !s.slug().equals(slug)).toList());
        return "leistungen/detail";
    }
}

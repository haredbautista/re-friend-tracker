package de.folienhaus.website.web;

import de.folienhaus.website.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Statische Seiten: Start, Preise, Über uns, Impressum, Datenschutz.
 */
@Controller
public class PageController {

    private final ContentService content;

    public PageController(ContentService content) {
        this.content = content;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("services", content.services());
        model.addAttribute("processSteps", content.processSteps());
        model.addAttribute("faq", content.faq());
        model.addAttribute("regions", content.regions());
        return "index";
    }

    @GetMapping("/preise")
    public String prices(Model model) {
        model.addAttribute("prices", content.prices());
        model.addAttribute("faq", content.faq());
        return "preise";
    }

    @GetMapping("/ueber-uns")
    public String about(Model model) {
        model.addAttribute("processSteps", content.processSteps());
        return "ueber-uns";
    }

    @GetMapping("/impressum")
    public String imprint() {
        return "impressum";
    }

    @GetMapping("/datenschutz")
    public String privacy() {
        return "datenschutz";
    }
}

package de.folienhaus.website.web;

import de.folienhaus.website.service.ContentService;
import de.folienhaus.website.service.JsonContentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Statische Seiten: Start, Preise, Über uns, Impressum, Datenschutz.
 */
@Controller
public class PageController {

    private final ContentService content;
    private final JsonContentRepository json;

    public PageController(ContentService content, JsonContentRepository json) {
        this.content = content;
        this.json = json;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("services", content.services());
        model.addAttribute("processSteps", content.processSteps());
        model.addAttribute("faq", content.faq());
        model.addAttribute("regions", content.regions());
        model.addAttribute("partners", json.partners());
        model.addAttribute("reviews", json.reviews());
        model.addAttribute("galleryTeaser", json.gallery().stream().limit(6).toList());
        return "index";
    }

    @GetMapping("/preise")
    public String prices(Model model) {
        model.addAttribute("prices", content.prices());
        model.addAttribute("faq", content.faq());
        model.addAttribute("tintPackages", content.tintPackages());
        model.addAttribute("ppfPackages", content.ppfPackages());
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

package de.folienhaus.website.web;

import de.folienhaus.website.domain.Region;
import de.folienhaus.website.service.ContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Regionale Landingpages, z. B. /autofolierung/singen.
 */
@Controller
public class RegionController {

    private final ContentService content;

    public RegionController(ContentService content) {
        this.content = content;
    }

    @GetMapping("/autofolierung/{slug}")
    public String region(@PathVariable String slug, Model model) {
        Region region = content.regionBySlug(slug)
                .orElseThrow(() -> new PageNotFoundException("Region nicht gefunden: " + slug));
        model.addAttribute("region", region);
        model.addAttribute("services", content.services());
        model.addAttribute("faq", content.faq());
        return "region";
    }
}

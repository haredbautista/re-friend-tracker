package de.folienhaus.website.web;

import de.folienhaus.website.domain.GalleryCategory;
import de.folienhaus.website.domain.GalleryItem;
import de.folienhaus.website.service.JsonContentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Referenz-Galerie mit Kategoriefilter: /galerie?kategorie=steinschlagschutz
 */
@Controller
public class GalleryController {

    static final List<GalleryCategory> CATEGORIES = List.of(
            new GalleryCategory("alle", "Alle"),
            new GalleryCategory("autofolierung", "Autofolierung"),
            new GalleryCategory("steinschlagschutz", "Steinschlagschutz"),
            new GalleryCategory("scheibentoenung", "Scheibentönung"),
            new GalleryCategory("design", "Design & Werbung"),
            new GalleryCategory("werkstatt", "Werkstatt")
    );

    private final JsonContentRepository content;

    public GalleryController(JsonContentRepository content) {
        this.content = content;
    }

    @GetMapping("/galerie")
    public String gallery(@RequestParam(name = "kategorie", defaultValue = "alle") String category, Model model) {
        String selected = CATEGORIES.stream().map(GalleryCategory::key).filter(category::equals).findFirst().orElse("alle");
        List<GalleryItem> items = content.gallery().stream()
                .filter(i -> selected.equals("alle") || i.category().equals(selected))
                .toList();
        model.addAttribute("categories", CATEGORIES);
        model.addAttribute("selected", selected);
        model.addAttribute("items", items);
        return "galerie";
    }
}

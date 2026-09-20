package de.folienhaus.website.web;

import de.folienhaus.website.domain.BlogPost;
import de.folienhaus.website.service.JsonContentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Ratgeber/Blog: Übersicht und Einzelbeiträge.
 */
@Controller
public class BlogController {

    private final JsonContentRepository content;

    public BlogController(JsonContentRepository content) {
        this.content = content;
    }

    @GetMapping("/blog")
    public String overview(Model model) {
        model.addAttribute("posts", content.posts());
        return "blog/uebersicht";
    }

    @GetMapping("/blog/{slug}")
    public String post(@PathVariable String slug, Model model) {
        BlogPost post = content.posts().stream().filter(p -> p.slug().equals(slug)).findFirst()
                .orElseThrow(() -> new PageNotFoundException("Beitrag nicht gefunden: " + slug));
        model.addAttribute("post", post);
        model.addAttribute("morePosts", content.posts().stream().filter(p -> !p.slug().equals(slug)).limit(3).toList());
        return "blog/beitrag";
    }
}

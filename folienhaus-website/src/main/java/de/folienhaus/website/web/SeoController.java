package de.folienhaus.website.web;

import de.folienhaus.website.config.CompanyProperties;
import de.folienhaus.website.domain.Region;
import de.folienhaus.website.domain.ServiceOffering;
import de.folienhaus.website.domain.BlogPost;
import de.folienhaus.website.service.ContentService;
import de.folienhaus.website.service.JsonContentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Liefert robots.txt und sitemap.xml für Suchmaschinen.
 */
@RestController
public class SeoController {

    private final ContentService content;
    private final CompanyProperties company;
    private final JsonContentRepository json;

    public SeoController(ContentService content, CompanyProperties company, JsonContentRepository json) {
        this.content = content;
        this.company = company;
        this.json = json;
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public String robots() {
        return "User-agent: *\nAllow: /\nSitemap: " + company.baseUrl() + "/sitemap.xml\n";
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String sitemap() {
        List<String> paths = new ArrayList<>(List.of("/", "/leistungen", "/preise", "/galerie", "/ueber-uns", "/blog", "/kontakt"));
        content.services().stream().map(ServiceOffering::path).forEach(paths::add);
        content.regions().stream().map(Region::path).forEach(paths::add);
        json.posts().stream().map(BlogPost::path).forEach(paths::add);

        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n");
        for (String path : paths) {
            xml.append("  <url><loc>").append(company.baseUrl()).append(path).append("</loc></url>\n");
        }
        return xml.append("</urlset>\n").toString();
    }
}

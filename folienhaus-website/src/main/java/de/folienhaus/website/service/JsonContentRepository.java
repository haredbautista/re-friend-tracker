package de.folienhaus.website.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.folienhaus.website.domain.BlogPost;
import de.folienhaus.website.domain.GalleryItem;
import de.folienhaus.website.domain.Partner;
import de.folienhaus.website.domain.Review;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Comparator;
import java.util.List;

/**
 * Lädt redaktionelle Inhalte aus JSON-Dateien unter {@code classpath:content/}.
 * Die Dateien lassen sich ohne Java-Kenntnisse pflegen.
 */
@Repository
public class JsonContentRepository {

    private final List<GalleryItem> gallery;
    private final List<BlogPost> posts;
    private final List<Partner> partners;
    private final List<Review> reviews;

    public JsonContentRepository(ObjectMapper mapper) {
        this.gallery = load(mapper, "content/galerie.json", new TypeReference<>() { });
        this.posts = load(mapper, "content/blog.json", new TypeReference<List<BlogPost>>() { }).stream()
                .sorted(Comparator.comparing(BlogPost::date).reversed())
                .toList();
        this.partners = load(mapper, "content/partner.json", new TypeReference<>() { });
        this.reviews = load(mapper, "content/bewertungen.json", new TypeReference<>() { });
    }

    private static <T> List<T> load(ObjectMapper mapper, String path, TypeReference<List<T>> type) {
        try (InputStream in = new ClassPathResource(path).getInputStream()) {
            return List.copyOf(mapper.readValue(in, type));
        } catch (IOException e) {
            throw new UncheckedIOException("Inhalte konnten nicht geladen werden: " + path, e);
        }
    }

    public List<GalleryItem> gallery() {
        return gallery;
    }

    public List<BlogPost> posts() {
        return posts;
    }

    public List<Partner> partners() {
        return partners;
    }

    public List<Review> reviews() {
        return reviews;
    }
}

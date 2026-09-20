package de.folienhaus.website.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WebsitePagesTest {

    @Autowired
    MockMvc mvc;

    @ParameterizedTest
    @ValueSource(strings = {
            "/", "/leistungen", "/leistungen/autofolierung", "/leistungen/steinschlagschutzfolierung",
            "/leistungen/lackschutzfolierung", "/leistungen/scheibentoenung", "/leistungen/designerstellung",
            "/leistungen/werbefolierung", "/preise", "/galerie", "/galerie?kategorie=steinschlagschutz",
            "/ueber-uns", "/blog", "/kontakt", "/impressum", "/datenschutz",
            "/autofolierung/singen", "/autofolierung/bodensee", "/autofolierung/villingen-schwenningen"
    })
    void pagesRenderWithCompanyData(String path) throws Exception {
        mvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Das Folienhaus")))
                .andExpect(content().string(containsString("Hohenkräher-Brühl 15a")))
                .andExpect(content().string(containsString("info@das-folienhaus.de")));
    }

    @Test
    void homePageShowsServicesAndOpeningHours() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Autofolierung")))
                .andExpect(content().string(containsString("Lackschutzfolierung")))
                .andExpect(content().string(containsString("Scheibentönung")))
                .andExpect(content().string(containsString("09:00 – 12:00 Uhr")))
                .andExpect(content().string(containsString("application/ld+json")));
    }

    @Test
    void pricesPageShowsVerifiedPackages() throws Exception {
        mvc.perform(get("/preise"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ab 2.200 €")))
                .andExpect(content().string(containsString("Front-Paket")))
                .andExpect(content().string(containsString("ab 2.450 €")))
                .andExpect(content().string(containsString("7 Scheiben")));
    }

    @Test
    void galleryFiltersByCategory() throws Exception {
        mvc.perform(get("/galerie").param("kategorie", "scheibentoenung"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("class=\"gallery-item")))
                .andExpect(content().string(not(containsString("steinschlagschutz-"))));
    }

    @Test
    void blogPostRendersAndUnknownSlugIs404() throws Exception {
        String overview = mvc.perform(get("/blog")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("href=\"/blog/([a-z0-9-]+)\"").matcher(overview);
        org.junit.jupiter.api.Assertions.assertTrue(m.find(), "Blog-Übersicht enthält keinen Beitrag");
        mvc.perform(get("/blog/" + m.group(1))).andExpect(status().isOk());
        mvc.perform(get("/blog/gibt-es-nicht")).andExpect(status().isNotFound());
    }

    @Test
    void unknownServiceReturns404Page() throws Exception {
        mvc.perform(get("/leistungen/gibt-es-nicht"))
                .andExpect(status().isNotFound());
    }

    @Test
    void unknownRegionReturns404() throws Exception {
        mvc.perform(get("/autofolierung/atlantis"))
                .andExpect(status().isNotFound());
    }

    @Test
    void sitemapListsServicesAndRegions() throws Exception {
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://www.das-folienhaus.de/leistungen/autofolierung")))
                .andExpect(content().string(containsString("https://www.das-folienhaus.de/autofolierung/singen")));
    }

    @Test
    void robotsPointsToSitemap() throws Exception {
        mvc.perform(get("/robots.txt"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sitemap: https://www.das-folienhaus.de/sitemap.xml")));
    }

    @Test
    void staticAssetsAreServed() throws Exception {
        mvc.perform(get("/css/style.css")).andExpect(status().isOk());
        mvc.perform(get("/js/main.js")).andExpect(status().isOk());
        mvc.perform(get("/img/logo.png")).andExpect(status().isOk());
        mvc.perform(get("/img/logo-dark.png")).andExpect(status().isOk());
        mvc.perform(get("/img/favicon.png")).andExpect(status().isOk());
    }
}

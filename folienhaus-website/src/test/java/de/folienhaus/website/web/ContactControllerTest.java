package de.folienhaus.website.web;

import de.folienhaus.website.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ContactService contactService;

    @Test
    void validSubmissionIsStoredAndRedirects() throws Exception {
        int before = contactService.received().size();

        mvc.perform(post("/kontakt")
                        .param("name", "Max Mustermann")
                        .param("email", "max@example.com")
                        .param("phone", "0151 0000000")
                        .param("vehicle", "VW Golf 8")
                        .param("service", "Autofolierung")
                        .param("message", "Ich möchte eine Vollfolierung in Mattschwarz.")
                        .param("privacyAccepted", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/kontakt?gesendet"));

        assertThat(contactService.received()).hasSize(before + 1);
        assertThat(contactService.received().get(before).name()).isEqualTo("Max Mustermann");
    }

    @Test
    void missingFieldsShowValidationErrors() throws Exception {
        mvc.perform(post("/kontakt")
                        .param("name", "")
                        .param("email", "keine-mail")
                        .param("service", "")
                        .param("message", "kurz"))
                .andExpect(status().isOk())
                .andExpect(view().name("kontakt"))
                .andExpect(model().attributeHasFieldErrors("contactForm",
                        "name", "email", "service", "message", "privacyAccepted"))
                .andExpect(content().string(containsString("Bitte geben Sie Ihren Namen an.")));
    }

    @Test
    void honeypotSubmissionIsDiscarded() throws Exception {
        int before = contactService.received().size();

        mvc.perform(post("/kontakt")
                        .param("name", "Bot")
                        .param("email", "bot@example.com")
                        .param("service", "Autofolierung")
                        .param("message", "Lorem ipsum dolor sit amet.")
                        .param("privacyAccepted", "true")
                        .param("website", "http://spam.example"))
                .andExpect(status().is3xxRedirection());

        assertThat(contactService.received()).hasSize(before);
    }

    @Test
    void preselectedServiceViaQueryParam() throws Exception {
        mvc.perform(get("/kontakt").param("leistung", "scheibentoenung"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("value=\"Scheibentönung\" selected")));
    }

    @Test
    void preselectedPpfServiceViaQueryParam() throws Exception {
        mvc.perform(get("/kontakt").param("leistung", "steinschlagschutzfolierung"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("value=\"Steinschlagschutzfolierung\" selected")));
    }
}

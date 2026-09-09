package de.folienhaus.website.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StructuredDataTest {

    @Test
    void escapesCharactersThatCouldBreakOutOfScriptTag() {
        assertThat(StructuredData.json("a\"b\\c</script>&"))
                .isEqualTo("\"a\\\"b\\\\c\\u003c/script\\u003e\\u0026\"");
    }

    @Test
    void nullBecomesEmptyString() {
        assertThat(StructuredData.json(null)).isEqualTo("\"\"");
    }
}

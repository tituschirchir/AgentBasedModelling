import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import models.Saying;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SayingTest
{
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Saying person = new Saying(1, "Titus Chirchir");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/saying.json"), Saying.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Saying person = new Saying(1, "Titus Chirchir");
        final Saying actual = MAPPER.readValue(fixture("fixtures/saying.json"), Saying.class);
        assertThat(actual.getContent()).isEqualTo(person.getContent());
    }
}

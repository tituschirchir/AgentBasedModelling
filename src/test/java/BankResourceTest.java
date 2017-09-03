import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import models.Bank;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BankResourceTest
{
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Bank person = new Bank(1, "Titus Chirchir");

        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/bank.json"), Bank.class));

        assertThat(MAPPER.writeValueAsString(person)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Bank bank = new Bank(1, "Titus Chirchir");
        final Bank actual = MAPPER.readValue(fixture("fixtures/bank.json"), Bank.class);
        assertThat(actual.getName()).isEqualTo(bank.getName());
    }
}

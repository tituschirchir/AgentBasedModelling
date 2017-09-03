package resources;

import com.codahale.metrics.annotation.Timed;
import models.Bank;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/banks")
@Produces(MediaType.APPLICATION_JSON)

public class BankResource
{
    private final String defaultName;
    private final AtomicLong counter;

    public BankResource(String defaultName) {
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    @Path("/by-name")
    public Bank sayHello(@QueryParam("name") Optional<String> name) {
        final String value = name.orElse(defaultName);
        return new Bank(counter.incrementAndGet(), value);
    }
}

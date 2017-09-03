package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Bank
{
    private long id;

    @Length(max = 3)
    private String name;

    public Bank() {
        // Jackson deserialization
    }

    public Bank(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}

package endtoend.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
{
  "firstName" : "Pooja"
  "lastName" : "Gulshetty"
}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {
  private final String name;
  private final String secondName;

  @JsonCreator
  public PersonDto(@JsonProperty("firstName") final String name,
                   @JsonProperty("lastName") final String secondName) {
    this.name = name;
    this.secondName = secondName;
  }

  public String getName() {
    return name;
  }

  public String getSecondName() {
    return secondName;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PersonDto personDto = (PersonDto) o;
    return name.equals(personDto.name) && secondName.equals(personDto.secondName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, secondName);
  }
}

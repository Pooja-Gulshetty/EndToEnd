package endtoend.controller;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endtoend.dto.PersonDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  Map<String, PersonDto> persons = new HashMap<>();

  ObjectMapper objectMapper = new ObjectMapper();

  @RequestMapping(path = "endtoend/v1/persons", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> createPerson(@RequestBody final String personJson) throws JsonProcessingException {
    final PersonDto personDto = objectMapper.readValue(personJson, PersonDto.class);
    final boolean exist = persons.containsKey(personDto.getName());
    if (exist) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    persons.put(personDto.getName(), personDto);
    return new ResponseEntity<>(personJson, HttpStatus.CREATED);
  }

  @RequestMapping(path = "endtoend/v1/persons/{personId}", method = RequestMethod.PUT)
  public ResponseEntity<String> updatePerson(final String personId, final String personJson) {
    return new ResponseEntity<>("Will be implemented later.", HttpStatus.NOT_IMPLEMENTED);
  }

  @RequestMapping(path = "endtoend/v1/persons/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getPerson(@PathVariable("personId") final String personName) throws JsonProcessingException {
    if (persons.containsKey(personName)) {
      final PersonDto personDto = persons.get(personName);
      final String json = objectMapper.writeValueAsString(personDto);
      return new ResponseEntity<>(json, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping(path = "endtoend/v2/persons/{personId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getPerson(@PathVariable("personId") final String personName, final String firstName) throws JsonProcessingException {
    if (persons.containsKey(personName)) {
      final PersonDto personDto = persons.get(personName);
      final String json = objectMapper.writeValueAsString(personDto);
      return new ResponseEntity<>(json, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  public ResponseEntity<String> deletePerson(final String personName) {
    return new ResponseEntity<>("Will be implemented later.", HttpStatus.NOT_IMPLEMENTED);
  }
}

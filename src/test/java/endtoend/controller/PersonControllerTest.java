package endtoend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endtoend.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PersonControllerTest {

  private final String requestBody = "{\n"
      + "    \"name\": \"pooja\",\n"
      + "    \"secondName\": \"gulshetty\"\n"
      + "}";

  @Test
  public void createPerson() throws JsonProcessingException {

    final PersonController personController = new PersonController();
    final ResponseEntity<String> response = personController.createPerson(requestBody);

    int statusCode = response.getStatusCodeValue();
    assertEquals(statusCode, 201, "We are expecting the person to be created successfully, with status code 201");
  }

  @Test
  public void createExistingPerson() throws JsonProcessingException {

    final PersonController personController = new PersonController();
    final ResponseEntity<String> response = personController.createPerson(requestBody);
    assertEquals(response.getStatusCodeValue(), 201, "Created first time");
    final ResponseEntity<String> responseEntity = personController.createPerson(requestBody);
    assertEquals(responseEntity.getStatusCodeValue(), 409, "Should not be allowed to create already existing person.");
  }

  @Test
  public void getNonExistingPerson() throws JsonProcessingException {
    final PersonController controller = new PersonController();
    final ResponseEntity<String> responseEntity = controller.getPerson("pooja");

    assertEquals(responseEntity.getStatusCodeValue(), 404, "Non existing should return found");
  }

  @Test
  public void getPerson() throws JsonProcessingException {
    final PersonController personController = new PersonController();
    final ResponseEntity<String> responseEntity = personController.getPerson("pooja");
    assertEquals(responseEntity.getStatusCodeValue(), 404, "Non existing person should return 404");
    personController.createPerson(requestBody);

    final ResponseEntity<String> response = personController.getPerson("pooja");

    assertEquals(response.getStatusCodeValue(), 200, "Get should return above created person");
    ObjectMapper objectMapper = new ObjectMapper();
    final PersonDto expected = objectMapper.readValue(requestBody, PersonDto.class);
    final PersonDto actual = objectMapper.readValue(response.getBody(), PersonDto.class);
    assertEquals(actual, expected, "Created and response object should be same.");
  }

}
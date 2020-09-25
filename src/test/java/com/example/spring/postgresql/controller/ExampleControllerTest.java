package com.example.spring.postgresql.controller;

import com.example.spring.postgresql.model.ExampleModel;
import com.example.spring.postgresql.service.ExampleService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ExampleService exampleService;

    ExampleModel model;
    Iterable<ExampleModel> listModel;

    @Before
    public void setup() {
        model = new ExampleModel();
        model.setId(1);
        model.setName("TEST");
        model.setAge(30);

        listModel = Collections.singletonList(model);
    }

    @Test
    public void testGetAllInfo() {
        Mockito
                .when(exampleService.getAll())
                .thenReturn(listModel);

        ResponseEntity<Object> response = restTemplate.getForEntity("/people", Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetAllInfoException() {
        Mockito
                .doThrow(new RuntimeException("TEST"))
                .when(exampleService)
                .getAll();

        ResponseEntity<Object> response = restTemplate.getForEntity("/people", Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testGetOneInfo() {
        Mockito
                .when(exampleService.getById(Mockito.anyInt()))
                .thenReturn(model);

        ResponseEntity<Object> response = restTemplate.getForEntity("/people/id/1", Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetOneInfoException() {
        Mockito
                .doThrow(new RuntimeException("TEST"))
                .when(exampleService)
                .getById(Mockito.anyInt());

        ResponseEntity<Object> response = restTemplate.getForEntity("/people/id/1", Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testIncludeInfo() {
        Mockito
                .when(exampleService.include(Mockito.any(ExampleModel.class)))
                .thenReturn(model);

        ResponseEntity<Object> response = restTemplate.postForEntity("/people", model, Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testIncludeInfoException() {
        Mockito
                .doThrow(new RuntimeException("TEST"))
                .when(exampleService)
                .include(Mockito.any(ExampleModel.class));

        ResponseEntity<Object> response = restTemplate.postForEntity("/people", model, Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testUpdateAllInfo() {
        Mockito
                .when(exampleService.update(Mockito.any(ExampleModel.class)))
                .thenReturn(model);

        HttpEntity<ExampleModel> entity = new HttpEntity<>(model);

        ResponseEntity<Object> response = restTemplate.exchange ("/people", HttpMethod.PUT, entity, Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testUpdateAllInfoException() {
        Mockito
                .doThrow(new RuntimeException("TEST"))
                .when(exampleService)
                .update(Mockito.any(ExampleModel.class));

        HttpEntity<ExampleModel> entity = new HttpEntity<>(model);
        ResponseEntity<Object> response = restTemplate.exchange("/people", HttpMethod.PUT, entity, Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testAddOneYearOldInfo() {
        Mockito
                .when(exampleService.increaseAge(Mockito.anyInt()))
                .thenReturn(model);

        ResponseEntity<Object> response = restTemplate.exchange("/people/id/1/add-year-old", HttpMethod.PATCH, null, Object.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}

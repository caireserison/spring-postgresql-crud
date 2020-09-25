package com.example.spring.postgresql.service;

import com.example.spring.postgresql.model.ExampleModel;
import com.example.spring.postgresql.model.ExampleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ExampleServiceTest {

    @Mock
    ExampleRepository exampleRepository;

    @InjectMocks
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
    public void testGetAll() {
        Mockito
                .when(exampleRepository.findAll())
                .thenReturn(listModel);

        Iterable<ExampleModel> response = exampleService.getAll();
        Assertions.assertThat(response.iterator().next().getId()).isEqualTo(1);
    }

    @Test
    public void testGetById() {
        Mockito
                .when(exampleRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(model));

        ExampleModel response = exampleService.getById(1);
        Assertions.assertThat(response.getId()).isEqualTo(1);
    }

    @Test
    public void testGetByIdNullReturn() {
        Mockito
                .when(exampleRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        ExampleModel response = exampleService.getById(1);
        Assertions.assertThat(response).isNull();
    }

    @Test
    public void testInclude() {
        Mockito
                .when(exampleRepository.save(Mockito.any()))
                .thenReturn(model);

        ExampleModel response = exampleService.include(model);
        Assertions.assertThat(response.getId()).isEqualTo(1);
    }

    @Test
    public void testUpdate() {
        Mockito
                .when(exampleRepository.save(Mockito.any()))
                .thenReturn(model);

        ExampleModel response = exampleService.update(model);
        Assertions.assertThat(response.getId()).isEqualTo(1);
    }

    @Test
    public void testIncreaseAge() {
        Mockito
                .when(exampleRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(model));

        Mockito
                .when(exampleRepository.save(Mockito.any(ExampleModel.class)))
                .thenReturn(model);

        ExampleModel response = exampleService.increaseAge(1);
        Assertions.assertThat(response.getAge()).isEqualTo(31);
    }

    @Test
    public void testIncreaseAgeNullReturn() {
        Mockito
                .when(exampleRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.empty());

        ExampleModel response = exampleService.increaseAge(1);
        Assertions.assertThat(response).isNull();
    }
}

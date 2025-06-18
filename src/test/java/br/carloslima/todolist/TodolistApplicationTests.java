package br.carloslima.todolist;

import br.carloslima.todolist.dto.TodoRequest;
import br.carloslima.todolist.dto.TodoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TodolistApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    private String basicAuth() {
        String auth = "root:Hmk221628!";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }

    @Test
    void testCreateTodoSuccess() {
        TodoRequest request = new TodoRequest();
        request.setName("Estudar");
        request.setDescription("Estudar para a prova");
        request.setTaskDone(false);
        request.setPriority(1);
        request.setStartTodo("10:30");
        request.setFinishTodo("12:30");

        webTestClient.post()
                .uri("/todos")
                .header("Authorization", basicAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(TodoResponse.class)
                .value(todo -> {
                    assert todo.getName().equals("Estudar");
                    assert todo.getStartTodo().equals("10:30");
                    assert todo.getFinishTodo().equals("12:30");
                });
    }

    @Test
    void testCreateTodoFailureInvalidTimeFormat() {
        TodoRequest request = new TodoRequest();
        request.setName("Estudo Errado");
        request.setDescription("Formato errado");
        request.setTaskDone(false);
        request.setPriority(1);
        request.setStartTodo("10-30"); // formato errado
        request.setFinishTodo("12:30");

        webTestClient.post()
                .uri("/todos")
                .header("Authorization", basicAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(msg -> org.junit.jupiter.api.Assertions.assertTrue(
                        msg.contains("Formato de hora inválido")
                ));
    }

    @Test
    void testCreateTodoFailureFinishBeforeStart() {
        TodoRequest request = new TodoRequest();
        request.setName("Estudo reverso");
        request.setDescription("Hora fim antes do inicio");
        request.setTaskDone(false);
        request.setPriority(1);
        request.setStartTodo("14:00");
        request.setFinishTodo("13:00"); // hora inválida

        webTestClient.post()
                .uri("/todos")
                .header("Authorization", basicAuth())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(msg -> org.junit.jupiter.api.Assertions.assertTrue(
                        msg.contains("Formato de hora inválido")
                ));
    }
}

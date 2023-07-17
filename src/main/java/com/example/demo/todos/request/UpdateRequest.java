package com.example.demo.todos.request;

import lombok.Getter;

@Getter
public class UpdateRequest {

    String title;
    String content;
    Boolean isDone;
}

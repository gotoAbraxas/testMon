package com.example.demo.todos.request;

import lombok.Getter;

@Getter
public class WriteRequest {

    String title;
    String content;
    Boolean isDone;
    Long memberId;


}

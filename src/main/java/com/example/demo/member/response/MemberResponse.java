package com.example.demo.member.response;

import com.example.demo.member.entity.Member;
import com.example.demo.todos.entity.Todos;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberResponse {

    Long id;
    String email;
    String password;
    String name;
    Integer age;
    List<TodosDto> todosDtoList;
    public MemberResponse(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.todosDtoList = member.getTodosList() !=null ?
                member.getTodosList().stream()
                        .map(TodosDto::new)
                        .toList() : new ArrayList<>();
    }
    @Getter
    @AllArgsConstructor
    class TodosDto {
        Long id;
        String title;
        String content;
        Boolean isDone;
        Integer likeCount;
       public TodosDto(Todos todos){
           this.id = todos.getId();;
           this.content = todos.getContent();
           this.isDone = todos.getIsDone();
           this.title = todos.getTitle();
           this.likeCount = todos.getLikeCount();

       }

    }
}

package com.example.demo.todos.response;

import com.example.demo.member.entity.Member;
import com.example.demo.todos.entity.Todos;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TodosResponse {

    Long id;

    String title;
    String content;
    Boolean isDone;
    Integer likeCount;
    MemberDTO memberDTO;
    public TodosResponse(Todos todos){
        this.id = todos.getId();
        this.title = todos.getTitle();
        this.content = todos.getContent();
        this.isDone = todos.getIsDone();
        this.likeCount = todos.getLikeCount();
        this.memberDTO = new MemberDTO(todos.getMember());
    }

    @Getter
    @AllArgsConstructor
    class MemberDTO {
        Long id;
        String email;
        String password;
        String name;
        Integer age;
        public MemberDTO(Member member){
            this.id = member.getId();
            this.email = member.getEmail();
            this.password = member.getPassword();
            this.name = member.getName();
            this.age = member.getAge();

        }

    }
}

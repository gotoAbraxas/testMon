package com.example.demo.member.entity;

import com.example.demo.like.entity.Like;
import com.example.demo.member.request.SignUpRequest;
import com.example.demo.todos.entity.Todos;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "members")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String email;
    String password;
    String name;
    Integer age;

    @OneToMany(mappedBy = "member")
    List<Todos> todosList;

    @OneToMany(mappedBy = "memberLike")
    List<Like> likeList;

    public Member(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Member(SignUpRequest signUpRequest) {
        this.id = null;
        this.email = signUpRequest.getEmail();
        this.password = signUpRequest.getPassword();
        this.name = signUpRequest.getName();
        this.age = signUpRequest.getAge();
    }
}

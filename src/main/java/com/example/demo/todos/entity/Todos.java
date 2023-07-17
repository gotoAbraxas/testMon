package com.example.demo.todos.entity;

import com.example.demo.like.entity.Like;
import com.example.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "todos")
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;
    String content;
    Boolean isDone;
    Integer likeCount;



    @ManyToOne
    Member member;

    @OneToMany(mappedBy = "todosLike")
    List<Like> likeTable;
}

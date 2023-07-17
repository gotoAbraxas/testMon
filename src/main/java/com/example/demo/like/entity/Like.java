package com.example.demo.like.entity;

import com.example.demo.member.entity.Member;
import com.example.demo.todos.entity.Todos;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Table(name = "likes")
public class Like {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Member memberLike;

    @ManyToOne
    Todos todosLike;
}

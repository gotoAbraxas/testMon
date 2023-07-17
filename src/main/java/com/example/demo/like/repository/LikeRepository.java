package com.example.demo.like.repository;

import com.example.demo.like.entity.Like;
import com.example.demo.member.entity.Member;
import com.example.demo.todos.entity.Todos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

    @Query("select l from Like l where l.memberLike = :member and l.todosLike = :todo")
    Like findByMemberIdAndTodoId(@Param("member") Member member,@Param("todo") Todos todo);
}

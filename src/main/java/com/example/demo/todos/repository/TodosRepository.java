package com.example.demo.todos.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.todos.entity.Todos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface TodosRepository  extends JpaRepository<Todos,Long> {

    @Query("select t from Todos t " +
            "where t.id = :id and t.member = :member")
    Todos findByTodoIdANDMember(@Param("id") Long id, @Param("member")Member member);

    //            "left join fetch m.members h " +
//            "join fetch h.member " +
    @Query("select t from Todos t " +
            "left join fetch t.member m " +
            "where t.content like :content ")
    Page<Todos> findByContentWith(@Param("content") String content, PageRequest pageRequest);
}



package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.member.request.LoginRequest;
import com.example.demo.member.response.MemberResult;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByEmail(String email);

    @Query("select m from Member m " +
            "where m.email = :email and password = :password")
    Member findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

    @Query( "select m from Member m " +
            "left join fetch m.todosList h " )
    Page<Member> findAllWithTodos(PageRequest pageRequest);
    // 이건 더 연구
}

package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface LoginRepository extends JpaRepository<MemberLogin,Long> {

    MemberLogin findByMemberId(Long id);

    Boolean existsByMemberId(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update MemberLogin m set m.createAt = :createAt, m.endAt = :endAt where m.memberId = :memberId")
    Integer update(@Param("createAt")Date createAt,@Param("endAt")Date endAt,@Param("memberId")Long memberId);
}

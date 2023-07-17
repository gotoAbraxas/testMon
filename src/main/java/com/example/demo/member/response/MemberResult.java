package com.example.demo.member.response;

import com.example.demo.member.entity.Member;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MemberResult {
    Long id;
    String name;
    Integer age;

    public MemberResult(Member member){
        this.id =member.getId();
        this.name = member.getName();
        this.age = member.getAge();
    }
}

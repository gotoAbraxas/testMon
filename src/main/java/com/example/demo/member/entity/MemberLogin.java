package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Builder
@Setter
@Table(name = "member_login")
public class MemberLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long memberId;
    Date createAt;
    Date endAt;


    public MemberLogin(Long id, Long memberId, Date createAt, Date endAt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(createAt);
        cal.add(Calendar.MINUTE, 10);
        //long 으로 바꾸고 숫자 더하고 다시 바꾸기

        this.id = id;
        this.memberId = memberId;
        this.createAt = createAt;
        this.endAt = cal.getTime();


    }
}

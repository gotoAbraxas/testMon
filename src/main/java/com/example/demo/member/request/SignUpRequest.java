package com.example.demo.member.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    String email;
    String password;
    String name;
    Integer age;

}

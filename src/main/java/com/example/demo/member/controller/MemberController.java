package com.example.demo.member.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.request.LoginRequest;
import com.example.demo.member.request.SignUpRequest;
import com.example.demo.member.response.MemberResponse;
import com.example.demo.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {



    private final MemberService memberService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignUpRequest signupRequest){

        memberService.signup(signupRequest);

    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest){

        memberService.login(loginRequest);

    }

    @GetMapping
    public Page<MemberResponse> search(
            @RequestParam (name = "size",required = false,defaultValue = "10") Integer size,
            @RequestParam (name = "page",required = false,defaultValue = "0") Integer page){

        PageRequest  pageRequest = PageRequest.of(page,size);
        return memberService.search(pageRequest);
    }


}

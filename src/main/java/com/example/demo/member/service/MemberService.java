package com.example.demo.member.service;

import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberLogin;
import com.example.demo.member.repository.LoginRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.request.LoginRequest;
import com.example.demo.member.request.SignUpRequest;
import com.example.demo.member.response.MemberResponse;
import com.example.demo.member.response.MemberResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final LoginRepository loginRepository;

    public void signup(SignUpRequest signUpRequest) {
        Member byEmail = memberRepository.findByEmail(signUpRequest.getEmail());

        if(byEmail == null) {
            Member member = new Member(signUpRequest);
            memberRepository.save(member);
        }else {
            //에러 발생
            throw new RuntimeException("EXISTS EMAIL");
        }
    }

    public void login(LoginRequest loginRequest){
        Member result = memberRepository.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

        MemberResult memberResult = new MemberResult(result);

        if(result != null){ // 아이디 찾을 수 있으면 이쪽
            Boolean exists = loginRepository.existsByMemberId(result.getId());

            if(exists){
                MemberLogin memberLogin = new MemberLogin(null, result.getId(), new Date(), null);
                loginRepository.update(memberLogin.getCreateAt(),memberLogin.getEndAt(),memberLogin.getMemberId());
            }else {
                // 존재하지 않으면 이쪽으로
                loginRepository.save(new MemberLogin(null, result.getId(), new Date(), null));
            }
        }else { // 없으면 이쪽
            new RuntimeException();
            System.out.println(0);
        }
    }

    public Page<MemberResponse> search(PageRequest pageRequest){
        Page<Member> members = memberRepository.findAllWithTodos(pageRequest);

        return members.map(MemberResponse::new);
    }

}

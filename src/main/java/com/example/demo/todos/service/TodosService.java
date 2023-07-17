package com.example.demo.todos.service;

import com.example.demo.like.entity.Like;
import com.example.demo.like.repository.LikeRepository;
import com.example.demo.member.entity.Member;
import com.example.demo.member.entity.MemberLogin;
import com.example.demo.member.repository.LoginRepository;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.todos.entity.Todos;
import com.example.demo.todos.repository.TodosRepository;
import com.example.demo.todos.request.UpdateRequest;
import com.example.demo.todos.request.WriteRequest;
import com.example.demo.todos.response.TodosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodosService {
    private final TodosRepository todosRepository;
    private final MemberRepository memberRepository;
    private final LoginRepository loginRepository;
    private final LikeRepository likeRepository;

    public Page<TodosResponse> searchContent(String content, PageRequest pageRequest){

        Page<Todos> byContentWith = todosRepository.findByContentWith("%" +content+"%", pageRequest);
        return byContentWith.map(TodosResponse::new);
    }
    public void write(WriteRequest writeRequest){

        MemberLogin loginStatus = loginRepository.findByMemberId(writeRequest.getMemberId());
        System.out.println(loginStatus.getEndAt());

        if(loginStatus.getEndAt().after(new Date())){
            Optional<Member> byId = memberRepository.findById(writeRequest.getMemberId());
            Member member = byId.orElseThrow(() -> new RuntimeException());

            Todos todos = new Todos(null, writeRequest.getTitle(), writeRequest.getContent(), writeRequest.getIsDone(), 0, member, null);

            todosRepository.save(todos);
        }else {
            // 에러 설정 해야함.
            System.out.println(1);
        }

    }

    public void update(Long id, UpdateRequest updateRequest){
        Optional<Todos> byId = todosRepository.findById(id);

        if(byId.isEmpty()){

        }
        Todos todos = byId.orElse(null);

        todos.setContent(updateRequest.getContent());
        todos.setIsDone(updateRequest.getIsDone());
        todos.setTitle(updateRequest.getTitle());
    }

    public  void delete(Long id){
        todosRepository.deleteById(id);
    }

    public void like(Long todoId,Long memberId){
        Optional<Member> byId = memberRepository.findById(memberId);
        Optional<Todos> byId1 = todosRepository.findById(todoId);


        Member member = byId.orElse(null);
        Todos todos = byId1.orElse(null);

        Like result = likeRepository.findByMemberIdAndTodoId(member, todos);

        if(result == null) {
            Like like = new Like(null, member, todos);
            likeRepository.save(like);
            todos.setLikeCount(todos.getLikeCount() + 1);
        }else {
            likeRepository.deleteById(result.getId());
            todos.setLikeCount(todos.getLikeCount() - 1);
        }
    }

    public void isDone(Long todoId,Long memberId){
        Optional<Member> byId = memberRepository.findById(memberId);
        Member member = byId.orElse(null);
        Todos todos = todosRepository.findByTodoIdANDMember(todoId, member);

        todos.setIsDone(true);
    }
}

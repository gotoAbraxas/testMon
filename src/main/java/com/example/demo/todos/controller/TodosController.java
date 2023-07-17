package com.example.demo.todos.controller;

import com.example.demo.todos.request.UpdateRequest;
import com.example.demo.todos.request.WriteRequest;
import com.example.demo.todos.response.TodosResponse;
import com.example.demo.todos.service.TodosService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodosController {
    private final TodosService todosService;

    @GetMapping()
    public Page<TodosResponse> searchContent(@RequestParam(name = "content",required = false,defaultValue = "")String content,
                                            @RequestParam (name = "size",required = false,defaultValue = "20") Integer size,
                                            @RequestParam (name = "page",required = false,defaultValue = "0") Integer page){
        PageRequest pageRequest = PageRequest.of(page,size);

        return todosService.searchContent(content,pageRequest);
    }

    @PostMapping
    public void write(@RequestBody WriteRequest writeRequest){

        todosService.write(writeRequest);
    }

    @PutMapping("{id}")
    public void update(@PathVariable(name = "id")Long id,@RequestBody UpdateRequest updateRequest){

        todosService.update(id,updateRequest);

    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id")Long id){

        todosService.delete(id);

    }


    @PostMapping("/{todoId}/like/{memberId}")
    public void like(@PathVariable(name = "todoId")Long todoId,@PathVariable(name = "memberId")Long memberId){


    todosService.like(todoId,memberId);
    }


    @PutMapping("{todoId}/check/{memberId}")
    public void isDone(@PathVariable(name = "todoId")Long todoId,@PathVariable(name = "memberId")Long memberId){
        todosService.isDone(todoId,memberId);
    }

}

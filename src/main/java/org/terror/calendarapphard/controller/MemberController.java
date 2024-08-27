package org.terror.calendarapphard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.terror.calendarapphard.model.BaseResponseDto;
import org.terror.calendarapphard.model.memberDto.RequestMemberDto;
import org.terror.calendarapphard.model.memberDto.ResponseMemberDto;
import org.terror.calendarapphard.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    // 유저 저장
    @PostMapping
    public BaseResponseDto createMember(@RequestBody RequestMemberDto reqDto){
        return memberService.createMember(reqDto);
    }

    // 유저 단건 조회
    @GetMapping("/{id}")
    public ResponseMemberDto getMember(@PathVariable Long id){
        return memberService.getMember(id);
    }

    // 유저 전체 조회
    @GetMapping
    public List<ResponseMemberDto> getAllMember(){
        return memberService.getAllMember();
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public BaseResponseDto deleteMember(@PathVariable Long id){
        return memberService.deleteMember(id);
    }

    // 특정 일정의 담당자 배치
    @PutMapping("{memberId}/{todoId}/{workerId}")
    // calendar 라는 중간테이블에서 담당자를 세팅해주기 위해서는
    // 어떤 사용자의, 어떤 게시글 인지 번호를 알아야하기때문에 memberId,todoId 둘다 받아옴
    public BaseResponseDto setWorker(@PathVariable Long memberId,@PathVariable Long todoId, @PathVariable Long workerId){
        return memberService.setWorker(memberId,todoId, workerId);
    }
}

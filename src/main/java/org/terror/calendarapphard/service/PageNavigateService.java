package org.terror.calendarapphard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terror.calendarapphard.entity.Todo;
import org.terror.calendarapphard.model.pageNavigateDto.ResponsePageNavigateDto;
import org.terror.calendarapphard.repository.PageableRepository;

@Service
@RequiredArgsConstructor
public class PageNavigateService {
    private final PageableRepository pageableRepository;

    @Transactional(readOnly = true)
    public Page<ResponsePageNavigateDto> getPage(int page, int size) {
        // 디폴트 페이지 크기는 10
        // 일정의 수정일 기준으로 내림차순 정렬
        Sort.Direction dir = Sort.Direction.DESC;
        Sort sort = Sort.by(dir,"updatedAt");
        Pageable pageable = PageRequest.of(page,size,sort);
        // 요청으로 들어온 page 의 todo를 전부 size갯수만큼 가져옴
        // 만약 2페이지에 size가 10이라면
        // id가 90~80 에서 각각 댓글이 10개씩 있는걸 가져오겠지
        Page<Todo> commentList = pageableRepository.findAll(pageable);
        return commentList.map(ResponsePageNavigateDto::new);
    }
}

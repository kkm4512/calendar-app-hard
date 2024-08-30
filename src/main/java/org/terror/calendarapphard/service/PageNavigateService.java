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

    /**
     * 페이지 조회 비즈니스 로직
     *
     * @param page / 조회 하고자하는 페이지
     * @param size / 페이지에 담겨야할 데이터의 갯수, 값이 들어오지않는다면 10이 기본값
     * @return Page<ResponsePageNavigateDto> / 특정 페이지의 데이터들을 수정일 기준으로 반환
     *
     * 페이지 조회에 성공하면, 데이터들을 반환합니다
     * 데이터가 없다면 빈리스트를 반환합니다
     */
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

package org.terror.calendarapphard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.terror.calendarapphard.model.pageNavigateDto.ResponsePageNavigateDto;
import org.terror.calendarapphard.service.PageNavigateService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PageNavigateController {
    private final PageNavigateService pageNavigateService;

    // 페이지 조회
    @GetMapping("/query")
    public Page<ResponsePageNavigateDto> getPage(@RequestParam int page, @RequestParam(defaultValue = "10") int size) {
        return pageNavigateService.getPage(page - 1,size);
    }
}

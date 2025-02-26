package com.example.rd.semiprojectv1.controller;

import com.example.rd.semiprojectv1.repository.BoardRepository;
import com.example.rd.semiprojectv1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model m, int cpg) {
        log.info("/board/list 호출!! ");
        m.addAttribute("bds", boardService.readBoard(cpg));
        return "views/board/list";
    }
}

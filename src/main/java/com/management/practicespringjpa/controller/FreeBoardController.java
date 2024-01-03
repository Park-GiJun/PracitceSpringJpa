package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.domain.FreeBoard;
import com.management.practicespringjpa.service.FreeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class FreeBoardController {

    private static final String FREE_BOARD_PAGE = "FreeBoardPage/FreeBoardPage";
    private static final String WRITE_FREE_BOARD_PAGE = "FreeBoardPage/FreeBoardWritePage";
    private static final String DETAIL_FREE_BOARD_PAGE = "FreeBoardPage/FreeBoardDetailPage";
    private final FreeBoardService freeBoardService;

    public FreeBoardController(FreeBoardService freeBoardService) {
        this.freeBoardService = freeBoardService;
    }

    @GetMapping("/FreeBoard")
    public ModelAndView FreeBoard() {
        System.out.println("Get FreeBoard Page");
        List<FreeBoard> freeBoardList = freeBoardService.getFreeBoardList();
        ModelAndView modelAndView = new ModelAndView(FREE_BOARD_PAGE);
        modelAndView.addObject("freeBoardList", freeBoardList);
        return modelAndView;
    }

    @GetMapping("/FreeBoard/WritePage")
    public String FreeBoardWrite(Model model) {
        System.out.println("WritePage FreeBoard");
        model.addAttribute("freeBoard", new FreeBoard());
        return WRITE_FREE_BOARD_PAGE;
    }

    @PostMapping("FreeBoard/WritePage/Write")
    public String WriteContent(@ModelAttribute FreeBoard freeBoard){
        System.out.println("WriteContent FreeBoard");
        freeBoard.setDate(LocalDate.now());
        freeBoardService.save(freeBoard);
        return FREE_BOARD_PAGE;
    }

    @GetMapping("/FreeBoard/WritePage/Detail/{idx}")
    public String FreeBoardDetail(Model model, @PathVariable int idx) {
        System.out.println("DetailPage FreeBoard");

        FreeBoard freeBoard = freeBoardService.getFreeBoard(idx);
        long daysAgo = ChronoUnit.DAYS.between(freeBoard.getDate(), LocalDate.now());
        model.addAttribute("detail", freeBoard);
        model.addAttribute("daysAgo", daysAgo);
        return DETAIL_FREE_BOARD_PAGE;
    }
}

package com.management.practicespringjpa.controller;

import com.management.practicespringjpa.domain.ShareBoard;
import com.management.practicespringjpa.service.ShareBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ShareBoardController {

    private static final String SHARE_BOARD_PAGE = "ShareBoardPage/ShareBoardPage";
    private final ShareBoardService shareBoardService;

    public ShareBoardController(ShareBoardService shareBoardService) {
        this.shareBoardService = shareBoardService;
    }

    @GetMapping("/ShareBoard")
    public ModelAndView shareBoard() {
        System.out.println("Get ShareBoard Page");

        ModelAndView modelAndView = new ModelAndView(SHARE_BOARD_PAGE);
        List<ShareBoard> list = shareBoardService.getShareBoardList();
        System.out.println(list);
        modelAndView.addObject("shareBoards", list);
        return modelAndView;
    }
}

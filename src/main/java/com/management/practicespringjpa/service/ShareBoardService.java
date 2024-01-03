package com.management.practicespringjpa.service;


import com.management.practicespringjpa.domain.ShareBoard;
import com.management.practicespringjpa.repository.ShareBoardRepository;

import java.util.List;

public class ShareBoardService {

    private final ShareBoardRepository shareBoardRepository;

    public ShareBoardService(ShareBoardRepository shareBoardRepository) {
        this.shareBoardRepository = shareBoardRepository;
    }

    public List<ShareBoard> getShareBoardList() {
        return shareBoardRepository.findAll();
    }
}

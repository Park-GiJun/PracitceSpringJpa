package com.management.practicespringjpa.service;

import com.management.practicespringjpa.domain.FreeBoard;
import com.management.practicespringjpa.repository.FreeBoardRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public class FreeBoardService {
    private final FreeBoardRepository freeBoardRepository;

    public FreeBoardService(FreeBoardRepository freeBoardRepository) {
        this.freeBoardRepository = freeBoardRepository;
    }

    public List<FreeBoard> getFreeBoardList() {
        return freeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "idx"));
    }

    public String save(FreeBoard freeBoard) {
        freeBoardRepository.save(freeBoard);
        return "저장성공";
    }

    public FreeBoard getFreeBoard(int idx) {
        return freeBoardRepository.findById(idx).get();
    }
}

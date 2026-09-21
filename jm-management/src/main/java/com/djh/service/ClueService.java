package com.djh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djh.PageResult;
import com.djh.dtp.ClueQueryDto;
import com.djh.entity.Clue;

public interface ClueService extends IService<Clue> {
    /**
     * 线索列表
     */
    PageResult<Clue> listClues(ClueQueryDto clueQueryDto);

    Clue getClueById(Integer id);

    void trackClue(Clue clue);

    void convertToBusiness(Integer id);
}

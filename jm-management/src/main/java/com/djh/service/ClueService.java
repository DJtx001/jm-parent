package com.djh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.djh.PageResult;
import com.djh.dtp.ClueFalseDto;
import com.djh.dtp.ClueQueryDto;
import com.djh.entity.Clue;

public interface ClueService extends IService<Clue> {
    /**
     * 线索列表
     */
    PageResult<Clue> listClues(ClueQueryDto clueQueryDto);

    /**
     * 线索池列表
     */
    PageResult<Clue> listCluePool(ClueQueryDto clueQueryDto);

    Clue getClueById(Integer id);

    void trackClue(Clue clue);

    /**
     * 伪线索处理
     */
    void markFalseClue(Integer id, ClueFalseDto clueFalseDto);

    void convertToBusiness(Integer id);

    /**
     * 回收超时未跟进的线索：状态改为待分配、释放归属人，回到线索池
     *
     * @param thresholdDays 超过多少天未跟进
     * @return 实际回收的条数
     */
    int recycleTimeoutClues(int thresholdDays);
}

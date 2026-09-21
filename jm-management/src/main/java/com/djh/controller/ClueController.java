package com.djh.controller;

import com.djh.PageResult;
import com.djh.Result;
import com.djh.dtp.ClueFalseDto;
import com.djh.dtp.ClueQueryDto;
import com.djh.entity.Clue;
import com.djh.service.ClueService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/clues")
public class ClueController{
    @Autowired
    ClueService clueService;
//    新增方法
    @PostMapping()
    public Result insertClue(@RequestBody Clue clue){
        log.info("新增线索: {}", clue);
        clue.setStatus(1);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        clueService.save(clue);
        return Result.success();
    }


    /**
     * 列表查询
     */
    @GetMapping
    public Result listClues(ClueQueryDto clueQueryDto) {
        log.info("查询参数: {}", clueQueryDto);
        PageResult<Clue> pageResult = clueService.listClues(clueQueryDto);
        return Result.success(pageResult);
    }

    @PutMapping("/assign/{clueId}/{userId}")
    public Result assignClue(@PathVariable Integer clueId, @PathVariable Integer userId) {
        log.info("分配线索: 线索ID={}, 用户ID={}", clueId, userId);

        // 查询线索是否存在
        Clue clue = new Clue();
        clue.setId(clueId);
        clue.setStatus(2); // 待跟进
        clue.setUserId(userId); // 设置归属人ID
        clue.setUpdateTime(java.time.LocalDateTime.now()); // 更新时间

        // 保存更新后的线索信息
        clueService.updateById(clue);
        return Result.success();
    }
//根据线索查询
    @GetMapping("/{id}")
    public Result getClueById(@PathVariable Integer id) {
        log.info("根据ID查询线索详细信息, id: {}", id);
        Clue clue = clueService.getClueById(id);
        return Result.success(clue);
    }

    @PutMapping
    public Result trackClue(@RequestBody Clue clue) {
        log.info("跟进线索: {}", clue);
        clueService.trackClue(clue);
        return Result.success();
    }

    @PutMapping("/toBusiness/{id}")
    public Result convertToBusiness(@PathVariable Integer id) {
        log.info("将线索转为商机, id: {}", id);
        clueService.convertToBusiness(id);
        return Result.success();
    }

    /**
     * 伪线索处理
     */
    @PutMapping("/false/{id}")
    public Result markFalseClue(@PathVariable Integer id, @RequestBody ClueFalseDto clueFalseDto) {
        log.info("伪线索处理: 线索ID={}, 参数: {}", id, clueFalseDto);
        clueService.markFalseClue(id, clueFalseDto);
        return Result.success();
    }

    /**
     * 线索池列表查询
     */
    @GetMapping("/pool")
    public Result listCluePool(ClueQueryDto clueQueryDto) {
        log.info("线索池查询参数: {}", clueQueryDto);
        PageResult<Clue> pageResult = clueService.listCluePool(clueQueryDto);
        return Result.success(pageResult);
    }
}


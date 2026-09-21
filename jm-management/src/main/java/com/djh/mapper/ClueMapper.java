package com.djh.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.djh.dtp.ClueQueryDto;
import com.djh.entity.Clue;
import com.djh.vo.OverviewVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueMapper extends BaseMapper<Clue> {
    /**
     * 线索列表分页查询
     */
    Page<Clue> listClues(Page<Clue> cluePage, ClueQueryDto clueQueryDto);
//    根据Id查询线索跟进
    Clue getClueById(Integer id);

    OverviewVO getClueOverviewData();
}

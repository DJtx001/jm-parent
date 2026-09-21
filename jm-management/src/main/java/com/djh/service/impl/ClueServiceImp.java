package com.djh.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.djh.PageResult;
import com.djh.dtp.ClueQueryDto;
import com.djh.entity.Business;
import com.djh.entity.Clue;
import com.djh.entity.ClueTrackRecord;
import com.djh.mapper.BusinessMapper;
import com.djh.mapper.ClueMapper;
import com.djh.mapper.ClueTrackRecordMapper;
import com.djh.service.ClueService;
import com.djh.utils.CurrentUserHoler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ClueServiceImp extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Autowired
    private ClueTrackRecordMapper clueTrackRecordMapper;

    //    分页查询
    @Override
    public PageResult<Clue> listClues(ClueQueryDto clueQueryDto) {
        Page<Clue> page = this.baseMapper.listClues(new Page<Clue>(clueQueryDto.getPage(), clueQueryDto.getPageSize()), clueQueryDto);
        return new PageResult<Clue>(page.getTotal(), page.getRecords());
    }
//    根据Id查询
    @Override
    public Clue getClueById(Integer id) {
        return this.baseMapper.getClueById(id);
    }

    // 跟进线索：更新线索状态 + 新增跟进记录
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trackClue(Clue clue) {
        // 1. 参数校验
        if (clue == null || clue.getId() == null) {
            throw new IllegalArgumentException("线索ID不能为空");
        }

        // 2. 查询原线索，确认存在
        Clue existClue = this.getById(clue.getId());
        if (existClue == null) {
            throw new IllegalArgumentException("线索不存在, id=" + clue.getId());
        }

        LocalDateTime now = LocalDateTime.now();

        // 3. 更新线索（参考 Clue.java 状态约定：1待分配, 2待跟进, 3跟进中, 4伪线索, 5转为商机）
        //    用新对象只更新必要字段，避免前端误传字段覆盖原值
        Clue updateClue = new Clue();
        updateClue.setId(clue.getId());
        updateClue.setStatus(3); // 跟进中
        updateClue.setSubject(clue.getSubject());
        updateClue.setLevel(clue.getLevel());
        updateClue.setNextTime(clue.getNextTime());
        updateClue.setUpdateTime(now);
        this.updateById(updateClue);

        // 4. 新增跟进记录
        ClueTrackRecord trackRecord = new ClueTrackRecord();
        trackRecord.setClueId(clue.getId());
        trackRecord.setUserId(CurrentUserHoler.getCurrentUser()); //当前登录用户ID
        trackRecord.setSubject(clue.getSubject());
        trackRecord.setLevel(clue.getLevel());
        trackRecord.setRecord(clue.getRecord());
        trackRecord.setNextTime(clue.getNextTime());
        trackRecord.setType(1); // 1:正常跟进
        trackRecord.setCreateTime(now);
        clueTrackRecordMapper.insert(trackRecord);
    }

    @Autowired
    private BusinessMapper businessMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void convertToBusiness(Integer id) {
        // 1. 修改线索信息
        Clue clue = this.getById(id);
        clue.setStatus(5); //转为商机
        clue.setUpdateTime(LocalDateTime.now());
        this.updateById(clue);

        // 2. 创建商机信息
        Business business = new Business();
        BeanUtils.copyProperties(clue, business);
        business.setId(null); //  设置商机ID为空, 表示为新增商机, 主键自动增长
        business.setUserId(null); //  设置商机归属人为空
        business.setNextTime(null); //  设置下次联系时间为空
        business.setStatus(1);
        business.setClueId(clue.getId());
        business.setCreateTime(LocalDateTime.now());
        business.setUpdateTime(LocalDateTime.now());

        // 4. 插入商机记录
        businessMapper.insert(business);
    }
}

package com.djh.dtp;

import lombok.Data;

/**
 * 伪线索处理参数封装类
 */
@Data
public class ClueFalseDto {

    /**
     * 伪线索原因, 1:空号、2:停机、3:竞品、4:无法联系、5:其他
     */
    private Integer reason;

    /**
     * 备注说明
     */
    private String remark;
}

package org.cmms.modules.kmtj.cdkmyecx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.kmtj.cdkmyecx.entity.Cdkmyecx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存贷科目余额查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface CdkmyecxMapper extends BaseMapper<Cdkmyecx> {
    void pYgmrscs(@Param("tjyf")String tjyf);
}

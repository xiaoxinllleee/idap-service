package org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity.Ckkhcdknrptj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款客户存贷款年日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
public interface CkkhcdknrptjMapper extends BaseMapper<Ckkhcdknrptj> {
    void pCkkhcdknrptj(@Param("tjyf")String tjyf);
}

package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity.RepYwbbZhckjgqkb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface RepYwbbZhckjgqkbMapper extends BaseMapper<RepYwbbZhckjgqkb> {

    public void extract(String tjyf);
}

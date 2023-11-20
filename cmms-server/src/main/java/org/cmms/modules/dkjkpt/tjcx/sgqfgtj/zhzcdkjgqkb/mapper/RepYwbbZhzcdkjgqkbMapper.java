package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity.RepYwbbZhzcdkjgqkb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface RepYwbbZhzcdkjgqkbMapper extends BaseMapper<RepYwbbZhzcdkjgqkb> {

    public void extract(String tjyf);
}

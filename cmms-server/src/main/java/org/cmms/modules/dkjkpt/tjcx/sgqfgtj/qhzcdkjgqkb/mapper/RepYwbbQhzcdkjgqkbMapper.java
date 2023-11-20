package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkb;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface RepYwbbQhzcdkjgqkbMapper extends BaseMapper<RepYwbbQhzcdkjgqkb> {

    public void extract(String tjyf);
}

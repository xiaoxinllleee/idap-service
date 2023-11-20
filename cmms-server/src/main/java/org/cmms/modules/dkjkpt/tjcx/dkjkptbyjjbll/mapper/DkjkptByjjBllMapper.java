package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.entity.DkjkptByjjBll;

/**
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
public interface DkjkptByjjBllMapper extends BaseMapper<DkjkptByjjBll> {
    public void extract(String tjyf);
}

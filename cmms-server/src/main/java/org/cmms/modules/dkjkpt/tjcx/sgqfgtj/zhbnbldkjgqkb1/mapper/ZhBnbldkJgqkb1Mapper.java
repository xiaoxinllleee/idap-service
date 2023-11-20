package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.entity.ZhBnbldkJgqkb1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 支行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface ZhBnbldkJgqkb1Mapper extends BaseMapper<ZhBnbldkJgqkb1> {

    public void extract(String tjyf);
}

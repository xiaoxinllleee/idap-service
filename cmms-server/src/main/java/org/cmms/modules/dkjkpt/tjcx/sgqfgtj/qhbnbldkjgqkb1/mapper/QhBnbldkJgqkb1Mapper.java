package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface QhBnbldkJgqkb1Mapper extends BaseMapper<QhBnbldkJgqkb1> {

    public void extract(String tjyf);
}

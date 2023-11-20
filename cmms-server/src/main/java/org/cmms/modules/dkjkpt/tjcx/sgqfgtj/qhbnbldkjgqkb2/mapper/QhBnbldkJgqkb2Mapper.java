package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.entity.QhBnbldkJgqkb2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface QhBnbldkJgqkb2Mapper extends BaseMapper<QhBnbldkJgqkb2> {

    public void extract(String tjyf);
}

package org.cmms.modules.ywgl.ckyw.ygckcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.ywgl.ckyw.ygckcx.entity.Ygckcx;

/**
 * @Description: 员工存款查询
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
public interface YgckcxMapper extends BaseMapper<Ygckcx> {
    void pYgckcx(@Param("tjyf")String tjyf);
}

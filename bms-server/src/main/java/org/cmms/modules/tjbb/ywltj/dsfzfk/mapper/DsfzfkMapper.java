package org.cmms.modules.tjbb.ywltj.dsfzfk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.dsfzfk.entity.Dsfzfk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 第三方支付卡
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
public interface DsfzfkMapper extends BaseMapper<Dsfzfk> {
    void pDsfzfk(@Param("tjyf")String tjyf);
}

package org.cmms.modules.khlc.jczbgl.mapper;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标库管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
public interface ErpBasZbkMapper extends BaseMapper<ErpBasZbk> {

    public void deleteByZbid(@Param("zbid") String zbid);
}

package org.cmms.modules.xyjlcx.jkyjgl.wglrmx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xyjlcx.jkyjgl.wglrmx.entity.Wglrmx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 无关联人明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
public interface WglrmxMapper extends BaseMapper<Wglrmx> {
    void pWglrmx();
}

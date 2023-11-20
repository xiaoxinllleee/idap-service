package org.cmms.modules.dklldj.csszgl.csgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
public interface CsszxxMapper extends BaseMapper<Csszxx> {

    void deleteByCsid(@Param("csid") String csid);

}

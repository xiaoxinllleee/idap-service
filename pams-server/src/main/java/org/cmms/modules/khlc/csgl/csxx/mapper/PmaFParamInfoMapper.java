package org.cmms.modules.khlc.csgl.csxx.mapper;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.csgl.csxx.entity.PmaFParamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import org.springframework.stereotype.Component;

/**
 * @Description: 参数信息
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Component
public interface PmaFParamInfoMapper extends BaseMapper<PmaFParamInfo> {
    IPage<PmaFParamInfo> getParamInfoByJdId(Page page, @Param("jdid") String jdid, @Param("csmc") String csmc, @Param("csbh") String csbh);

}

package org.cmms.modules.khlc.csgl.csxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khlc.csgl.csxx.entity.PmaFParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;

/**
 * @Description: 参数信息
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
public interface IPmaFParamInfoService extends IService<PmaFParamInfo> {
    public IPage<PmaFParamInfo> getParamInfoByJdId(Page<PmaFParamInfo> page, String jdid, String csmc,String csbh);
    public int deleteByParamIdAndArea(String paramId,String area);
}

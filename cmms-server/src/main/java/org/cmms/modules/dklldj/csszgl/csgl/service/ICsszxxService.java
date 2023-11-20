package org.cmms.modules.dklldj.csszgl.csgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@DS("rate") // rate
public interface ICsszxxService extends IService<Csszxx> {

    void deleteByCsid(@Param("csid") String csid);

}

package org.cmms.modules.khlc.jczbgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 指标库管理
 * @Author: jeecg-boot
 * @Date:   2023-02-22
 * @Version: V1.0
 */
public interface IErpBasZbkService extends IService<ErpBasZbk> {

    public void deleteByZbid(@Param("zbid") String zbid);
}

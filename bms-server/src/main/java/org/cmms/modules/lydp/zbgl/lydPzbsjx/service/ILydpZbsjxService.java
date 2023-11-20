package org.cmms.modules.lydp.zbgl.lydPzbsjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;

import java.util.List;

/**
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
public interface ILydpZbsjxService extends IService<LydpZbsjx> {
    List<LydpZbsjx> getListByQydm(String qydm, String zblx, String zbwd, String zbid);
    List<LydpZbsjx> getListByQydm(String qydm, String zblx);

}

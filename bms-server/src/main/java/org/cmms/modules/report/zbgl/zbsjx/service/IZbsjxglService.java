package org.cmms.modules.report.zbgl.zbsjx.service;

import org.cmms.modules.khgl.cqjm.entity.CqjmZcfzqk;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 指标数据项管理
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
public interface IZbsjxglService extends IService<Zbsjxgl> {
    List<Zbsjxgl> getListByQydm(String qydm,String zblx, String zbwd, String zbid);
}

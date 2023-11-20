package org.cmms.modules.khlc.bmgpgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khlc.bmgpgl.entity.Bmgpgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.bmgpgl.entity.HrPostBmxx;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;

import java.util.List;

/**
 * @Description: 部门挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@DS("eweb")
public interface IBmgpglService extends IService<Bmgpgl> {
    List<HrPostBmxx> bmxx();
}

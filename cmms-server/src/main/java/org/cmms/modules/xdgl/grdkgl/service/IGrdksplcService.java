package org.cmms.modules.xdgl.grdkgl.service;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.entity.Grdkcjxx;
import org.cmms.modules.xdgl.grdkgl.entity.Grdksplc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xdgl.grdkgl.vo.GrdkglPage;

import java.util.List;

/**
 * @Description: 个人贷款审批流程
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
public interface IGrdksplcService extends IService<Grdksplc> {
    public void updateMain(GrdkglPage grdkglPage, List<GrdkBzxx> bzxxList, List<GrdkDbxx> dbxxList);

}

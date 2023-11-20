package org.cmms.modules.xdgl.grdkgl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkJtsp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 集体审批书
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface IGrdkJtspService extends IService<GrdkJtsp> {

    int isComplete(String id);
    public Integer updById(String id);

    public List<GrdkDbxx> getDbxx(String zjhm);

}

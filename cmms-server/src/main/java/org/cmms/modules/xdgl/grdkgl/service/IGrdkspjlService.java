package org.cmms.modules.xdgl.grdkgl.service;

import org.cmms.modules.xdgl.grdkgl.entity.Grdkspjl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 个人贷款审批结果
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
public interface IGrdkspjlService extends IService<Grdkspjl> {

    public void extractJtspxx(String id,String zjhm);

}

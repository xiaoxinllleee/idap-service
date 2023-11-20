package org.cmms.modules.tjfx.grcdksjmx.service;

import org.cmms.modules.tjfx.grcdksjmx.entity.KhglGrcdsjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 个人存贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2020-11-28
 * @Version: V1.0
 */
public interface IKhglGrcdsjmxService extends IService<KhglGrcdsjmx> {

    public void init(Map<String,Object> sql);

    public List<Map> getgrjynck(String zjhm);

    public String getCsz(String csbm);

}

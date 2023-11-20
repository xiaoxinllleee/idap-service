package org.cmms.modules.khlc.jczbgl.service;

import com.alibaba.fastjson.JSONArray;
import org.cmms.modules.khlc.jczbgl.entity.VpmaFBaseIndexType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 基础指标树包含指标
 * @Author: jeecg-boot
 * @Date:   2021-01-26
 * @Version: V1.0
 */
public interface IVpmaFBaseIndexTypeService extends IService<VpmaFBaseIndexType> {
    List<VpmaFBaseIndexType> listTree();

    JSONArray queryTreeList(Integer khfs,Integer zblx);
}

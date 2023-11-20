package org.cmms.modules.dklldj.tjfxgl.zhsftj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.entity.Zhsftj;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 支行上浮统计
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IZhsftjService extends IService<Zhsftj> {

    @DS("eweb")
    void init(Map<String, String> sql);

}

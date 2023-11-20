package org.cmms.modules.yxgl.khhfjh.service;

import org.cmms.modules.yxgl.khhfjh.entity.YxglKhhfjh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 客户回访计划
 * @Author: cmms
 * @Date:   2019-12-18
 * @Version: V1.0
 */
public interface IYxglKhhfjhService extends IService<YxglKhhfjh> {

    public void extract(Map<String,String> sql);

}

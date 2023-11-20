package org.cmms.modules.pad.nhxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.nhxxgl.entity.YxglKhhfxxb;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface IYxglKhhfxxbService extends IService<YxglKhhfxxb> {

    List<YxglKhhfxxb> queryHfxxByZjhm(String zjhm);

}

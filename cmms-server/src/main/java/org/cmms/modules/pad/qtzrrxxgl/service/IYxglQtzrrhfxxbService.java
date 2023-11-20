package org.cmms.modules.pad.qtzrrxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.qtzrrxxgl.entity.YxglQtzrrhfxxb;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
public interface IYxglQtzrrhfxxbService extends IService<YxglQtzrrhfxxb> {

    List<YxglQtzrrhfxxb> queryHfxxByZjhm(String zjhm);

}

package org.cmms.modules.dklldj.csszgl.xmgzsz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.xmgzsz.entity.Xmgzsz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 项目规则设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IXmgzszService extends IService<Xmgzsz> {

}

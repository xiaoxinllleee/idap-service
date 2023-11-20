package org.cmms.modules.dklldj.csszgl.gzbdssz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.gzbdssz.entity.Gzbdssz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 规则表达式设置
 * @Author: jeecg-boot
 * @Date:   2020-03-05
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IGzbdsszService extends IService<Gzbdssz> {

}

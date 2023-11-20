package org.cmms.modules.dklldj.csszgl.khxmsz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 考核项目设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IKhxmszService extends IService<Khxmsz> {

    Khxmsz queryByQydmAndZbid(Map<String, String> sql);

}

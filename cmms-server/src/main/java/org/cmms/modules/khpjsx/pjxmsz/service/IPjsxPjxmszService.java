package org.cmms.modules.khpjsx.pjxmsz.service;

import org.cmms.modules.khpjsx. pjxmsz.entity.PjsxPjxmsz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 评级项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IPjsxPjxmszService extends IService<PjsxPjxmsz> {

    public  PjsxPjxmsz queryxmbh (Map<String,String> sql);

}

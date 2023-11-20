package org.cmms.modules.khpjsx.pjsxzbk.service;

import org.cmms.modules.khpjsx.pjsxzbk.entity.PjsxZbkxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 评级授信指标库
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IPjsxZbkxxService extends IService<PjsxZbkxx> {

    public PjsxZbkxx queryZbid(Map<String,String>sql);

}

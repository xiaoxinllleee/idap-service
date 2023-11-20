package org.cmms.modules.khpjsx.pjsxsjx.service;

import org.cmms.modules.khpjsx.pjsxsjx.entity.PjsxSjxxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 评级授信数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IPjsxSjxxxService extends IService<PjsxSjxxx> {

    PjsxSjxxx querySjxid(Map<String,String> sql);

}

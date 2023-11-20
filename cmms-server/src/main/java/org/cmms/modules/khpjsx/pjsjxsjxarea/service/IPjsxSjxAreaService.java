package org.cmms.modules.khpjsx.pjsjxsjxarea.service;

import org.cmms.modules.khpjsx.pjsjxsjxarea.entity.PjsxSjxArea;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 区域数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-17
 * @Version: V1.0
 */
public interface IPjsxSjxAreaService extends IService<PjsxSjxArea> {

    PjsxSjxArea querySjxid(Map<String,String> sql);

}

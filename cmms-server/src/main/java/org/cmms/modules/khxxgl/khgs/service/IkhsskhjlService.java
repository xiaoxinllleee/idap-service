package org.cmms.modules.khxxgl.khgs.service;

import org.cmms.modules.khxxgl.khgs.entity.khsskhjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 客户归属客户经理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IkhsskhjlService extends IService<khsskhjl> {
    public List<String> getKhjbzlZjhmKhjlgh(String yggh);

}

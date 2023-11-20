package org.cmms.modules.khxxgl.khflgl.qyxx.service;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsJbxxQyzllb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.CamsJbxxShzllb;

import java.util.List;

/**
 * @Description: 企业附件资料列表
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface ICamsJbxxQyzllbService extends IService<CamsJbxxQyzllb> {

    public List<CamsJbxxQyzllb> getByQyid(String qyid);
}

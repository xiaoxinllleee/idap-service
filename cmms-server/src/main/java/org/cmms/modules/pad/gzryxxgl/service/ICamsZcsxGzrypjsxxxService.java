package org.cmms.modules.pad.gzryxxgl.service;

import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzrypjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;

/**
 * @Description: 公职人员评级授信表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
public interface ICamsZcsxGzrypjsxxxService extends IService<CamsZcsxGzrypjsxxx> {

    public CamsZcsxGzrypjsxxx getByGzryid(String gzryid);
}

package org.cmms.modules.pad.nhczfp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.entity.NhczfpVo;

import java.util.List;

/**
 * @Description: 农户村组复评
 * @Author: jeecg-boot
 * @Date:   2023-03-27
 * @Version: V1.0
 */
public interface INhczfpService extends IService<Nhczfp> {
    public List<NhczfpVo> getYwcbkbpyList(String wgbh);
}

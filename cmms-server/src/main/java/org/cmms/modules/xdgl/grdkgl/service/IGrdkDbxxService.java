package org.cmms.modules.xdgl.grdkgl.service;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 个人贷款担保信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
public interface IGrdkDbxxService extends IService<GrdkDbxx> {
    public List<GrdkDbxx> selectByMainId(String mainId);
}

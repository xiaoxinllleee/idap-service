package org.cmms.modules.xdgl.grdkgl.service;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 个人贷款保证信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
public interface IGrdkBzxxService extends IService<GrdkBzxx> {
    public List<GrdkBzxx> selectByMainId(String mainId);
}

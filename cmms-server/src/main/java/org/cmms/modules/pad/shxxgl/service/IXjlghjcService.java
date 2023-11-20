package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.Xjlghjc;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 现金流归行检测
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
public interface IXjlghjcService extends IService<Xjlghjc> {

    public List<Xjlghjc> queryXjlGhjc(String hhbm);

}

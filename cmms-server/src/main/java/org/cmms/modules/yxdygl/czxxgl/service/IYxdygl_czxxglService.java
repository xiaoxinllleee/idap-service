package org.cmms.modules.yxdygl.czxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;

import java.util.List;

/**
 * @Description: 村信息管理
 * @Author: cmms
 * @Date:   2019-11-05
 * @Version: V1.0
 */
public interface IYxdygl_czxxglService extends IService<Yxdygl_czxxgl> {
    public Yxdygl_czxxgl queryByQybm(String qybm);

    public List<Yxdygl_czxxgl> queryByCmc(String cmc);
}

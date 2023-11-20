package org.cmms.modules.khgl.wyxkhxx.service;

import org.cmms.modules.khgl.wyxkhxx.entity.Wyxkhxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 未用信客户信息
 * @Author: jeecg-boot
 * @Date:   2019-09-29
 * @Version: V1.0
 */
public interface IWyxkhxxService extends IService<Wyxkhxx> {
    public void initWyxkhxx();

    public List<Wyxkhxx> queryByHzcustid(String hzcustid);
}

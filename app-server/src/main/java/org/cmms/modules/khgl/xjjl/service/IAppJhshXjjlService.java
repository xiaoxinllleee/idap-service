package org.cmms.modules.khgl.xjjl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 聚合商户巡检记录
 * @Author: jeecg-boot
 * @Date:   2022-03-14
 * @Version: V1.0
 */

public interface IAppJhshXjjlService extends IService<AppJhshXjjl> {
    List<TgacsTpsMchntInfo> getAllList(int start, int end, String namecn);

    List<AppJhshXjjl> getXjsj(String mchntId);

    List<TgacsTpsMchntInfo> showXJ(int start, int end, String namecn);
}

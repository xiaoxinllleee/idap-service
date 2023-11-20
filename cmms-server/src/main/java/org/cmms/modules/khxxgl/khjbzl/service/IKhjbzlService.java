package org.cmms.modules.khxxgl.khjbzl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 客户画像
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IKhjbzlService extends IService<Khjbzl> {
    public void extract(String  tjrq);
    public Integer getTodayBirthDayMans(String wgbh);
    int syncYesKhjbzl();
}

package org.cmms.modules.appbase.datatime.service;

import org.cmms.modules.appbase.datatime.entity.BasDataJobDays;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 数据入库
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
public interface IBasDataJobDaysService extends IService<BasDataJobDays> {


    //获取最新入库时间
    Date getMaxExtDay();
    //获取最新入库时间
    Date    getMaxEendDay();
}

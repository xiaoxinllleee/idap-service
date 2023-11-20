package org.cmms.modules.khxxgl.khflgl.nhxq.service;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VKhxxglKhxqXxnyzt;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2023-10-13
 * @Version: V1.0
 */
public interface IVKhxxglKhxqXxnyztService extends IService<VKhxxglKhxqXxnyzt> {
    void initData(String sjrq);
    Date getMaxDate();
}

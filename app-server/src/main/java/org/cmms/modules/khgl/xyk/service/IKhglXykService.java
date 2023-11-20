package org.cmms.modules.khgl.xyk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.xyk.entity.KhglXyk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 信用卡_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@DS("eweb")
public interface IKhglXykService extends IService<KhglXyk> {

   String getYcshj(String jgdm, Date tjrq);

   String getDate(String jgdm,String yggh);
}

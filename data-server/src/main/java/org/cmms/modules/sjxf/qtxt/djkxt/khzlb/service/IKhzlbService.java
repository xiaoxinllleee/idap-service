package org.cmms.modules.sjxf.qtxt.djkxt.khzlb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sjxf.qtxt.djkxt.khzlb.entity.Khzlb;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;

import java.util.List;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@DS("sjxf")
public interface IKhzlbService extends IService<Khzlb> {

    List<Khzlb> getXykListByKhmc(String khmc,String jgdm);
}

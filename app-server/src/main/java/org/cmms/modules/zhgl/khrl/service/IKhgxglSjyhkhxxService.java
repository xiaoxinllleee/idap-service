package org.cmms.modules.zhgl.khrl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.khrl.entity.KhgxglSjyhkhxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 手机银行客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
public interface IKhgxglSjyhkhxxService extends IService<KhgxglSjyhkhxx> {

    List<KhgxglSjyhkhxx> getListBykhmc(String khmc,String jgdm);
}

package org.cmms.modules.zhgl.khrl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.khrl.entity.CcdCustr;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.zhgl.khrl.entity.CcdCustrVO;

import java.util.List;

/**
 * @Description: 客户资料表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
public interface ICcdCustrService extends IService<CcdCustr> {

    List<CcdCustrVO> getXykListByKhmc(String khmc, String jgdm);
}

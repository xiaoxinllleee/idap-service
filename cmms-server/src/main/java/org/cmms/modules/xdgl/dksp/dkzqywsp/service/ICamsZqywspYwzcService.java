package org.cmms.modules.xdgl.dksp.dkzqywsp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xdgl.dksp.dkzqywsp.entity.CamsZqywspYwzc;
import org.cmms.modules.xdgl.dksp.dkzqywsp.vo.DkxxVo;

import java.util.List;

/**
 * @Description: 贷款展期业务审批注册表
 * @Author: jeecg-boot
 * @Date:   2023-10-10
 * @Version: V1.0
 */
public interface ICamsZqywspYwzcService extends IService<CamsZqywspYwzc> {
    List<DkxxVo> getDkxx(String khmc, String dkzh,String zjhm);
}

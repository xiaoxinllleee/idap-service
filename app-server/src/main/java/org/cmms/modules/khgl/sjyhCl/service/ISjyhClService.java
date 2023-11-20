package org.cmms.modules.khgl.sjyhCl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.sjyhCl.entity.SjyhCl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 手机银行_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@DS("eweb")
public interface ISjyhClService extends IService<SjyhCl> {

    String getDate();

}

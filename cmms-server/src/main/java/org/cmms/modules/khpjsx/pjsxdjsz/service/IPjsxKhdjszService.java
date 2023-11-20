package org.cmms.modules.khpjsx.pjsxdjsz.service;

import org.cmms.modules.khpjsx.pjsxdjsz.entity.PjsxKhdjsz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 评级授信等级设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
public interface IPjsxKhdjszService extends IService<PjsxKhdjsz> {

    public PjsxKhdjsz queryDjbh(Map<String,String>sql);

}

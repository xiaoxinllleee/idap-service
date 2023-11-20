package org.cmms.modules.khpjsx.khpjsxb.service;

import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxKhpjsxb;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.transaction.Transactional;

/**
 * @Description: 客户评级授信表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
public interface IPjsxKhpjsxbService extends IService<PjsxKhpjsxb> {
    public void extractPjsx(String khlx,String tjyf);
}

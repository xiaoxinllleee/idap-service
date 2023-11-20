package org.cmms.modules.khgl.qtzrr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khgl.qtzrr.entity.QtzrrPjsxxx;

import java.util.List;

/**
 * @Description: 其他自然人评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
public interface IQtzrrPjsxxxService extends IService<QtzrrPjsxxx> {

    public List<QtzrrPjsxxx> selectByMainId(String zjhm);


}

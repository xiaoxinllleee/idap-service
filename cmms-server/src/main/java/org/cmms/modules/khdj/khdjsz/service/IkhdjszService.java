package org.cmms.modules.khdj.khdjsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khdj.khdjsz.entity.Khdjsz;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface IkhdjszService extends IService<Khdjsz> {

    Khdjsz queryByDjbh(Map<String, String> sql);

}

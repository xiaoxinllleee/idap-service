package org.cmms.modules.khgl.sh.service;

import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-10-24
 * @Version: V1.0
 */
public interface IShglYwhywwlxxService extends IService<ShglYwhywwlxx> {

    public List<ShglYwhywwlxx> selectByMainId (String zjhm);

}

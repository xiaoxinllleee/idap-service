package org.cmms.modules.khgl.nh.service;

import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
public interface IYwhywwlxxService extends IService<Ywhywwlxx> {

    public List<Ywhywwlxx> selectByMainId (String zkhm);

    public List<Ywhywwlxx> selectByHhbm (String hhbm);

    public  boolean  deleteByMainId( String zjhm);

    public String endDate(String zjhm);
    public String appMaturityDate(String zjhm);
}

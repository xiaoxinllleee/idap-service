package org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;

import java.util.List;

/**
 * @Description: 整村授信行政村进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface IZcsxxzcjdbService extends IService<Zcsxxzcjdb> {

    List<Zcsxxzcjdb> getCunList();
}

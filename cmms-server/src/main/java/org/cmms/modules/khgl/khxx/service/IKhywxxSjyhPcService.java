package org.cmms.modules.khgl.khxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;

import java.util.List;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
public interface IKhywxxSjyhPcService extends IService<KhywxxSjyhPc> {

   IPage<KhywxxSjyhPc> getByWgbh(Page page,List<String> wgbhList);
   IPage<Nhxq> getWktByWgbh(Page page, List<String> wgbhList);
   List<KhywxxSjyhPc> getByWgbhList(List<String> wgbhList);
   List<Nhxq> getWktByWgbhList(List<String> wgbhList);
}

package org.cmms.modules.pad.nhxxgl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.nhxxgl.entity.CamsJbxxNhzllbPad;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSbk;
import org.cmms.modules.pad.nhxxgl.service.IKhywxxSbkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Date 2022/9/27
 * @Created by eran
 */
@RestController
@RequestMapping("/nhxxgl/sbk")
public class KhywxxSbkController extends JeecgController<KhywxxSbk, IKhywxxSbkService> {

    @RequestMapping(value = "/getWhsbkByWgbh",method = RequestMethod.GET)
    public Result<?> getWhsbkByWgbh(String wgbh,
                                    @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                    @RequestParam(name="pageSize", defaultValue="10") Integer pageSize){
      if (StringUtils.isBlank(wgbh))
          return Result.error("所属网格不能为空！");
      Page<KhywxxSbk> page = new Page<KhywxxSbk>(pageNo, pageSize);
      IPage<KhywxxSbk> byWgbh = service.getByWgbh(page, Arrays.asList(wgbh.split(",")));
      return Result.ok(byWgbh);
    }
}

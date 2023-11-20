package org.cmms.modules.jx.common.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.appbase.tbtjfxcssz.service.ITbTjfxCsszService;
import org.cmms.modules.jx.common.entity.TbTjfxDkghhzxx;
import org.cmms.modules.jx.common.service.ITbTjfxDkghhzxxService;
import org.cmms.modules.jx.dkkh.service.ITbTjfxDkghmxxxxService;
import org.cmms.modules.utils.BigDecimalRoundUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款管户汇总信息
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款管户汇总信息")
@RestController
@RequestMapping("/mobile")
public class TbTjfxDkghhzxxController extends JeecgController<TbTjfxDkghhzxx, ITbTjfxDkghhzxxService> {

  @Autowired
  ITbTjfxCsszService tbTjfxCsszService;
  @Autowired
  ITbTjfxDkghmxxxxService tbTjfxDkghmxxxxService;
  @Autowired
  ITbTjfxDkghhzxxService tbTjfxDkghhzxxService;

  @RequestMapping("/tbTjfxBnblmxBankPmRest/page")
  public Result<?> page(@RequestParam(value="yggh",required=false) String yggh,Page page)  {
  LoginUser loginUser = getLoginUser();
   if (StringUtils.isBlank(yggh))
     yggh = loginUser.getWorkNo();
   return Result.ok(tbTjfxDkghmxxxxService.getPageByYggh(page, yggh));
  }

     @RequestMapping("/tbTjfxDkghhzxxBankPmRest/page")
     public Result<?> page2(@RequestParam(value="yggh",required=false) String yggh)  {
         LoginUser loginUser = getLoginUser();
         if (StringUtils.isBlank(yggh))
             yggh = loginUser.getWorkNo();
         Date theMaxDate = tbTjfxCsszService.getTheMaxDate();
         QueryWrapper queryWrapper = new QueryWrapper();
         queryWrapper.eq("yggh",yggh);
         if (theMaxDate != null) {
             queryWrapper.eq("tjrq",theMaxDate);
         }
         List<TbTjfxDkghhzxx> list = tbTjfxDkghhzxxService.list(queryWrapper);
         if (CollUtil.isNotEmpty(list)){
             TbTjfxDkghhzxx tbTjfxDkghhzxx = list.get(0);
             tbTjfxDkghhzxx.setGhye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getGhye()));
             tbTjfxDkghhzxx.setYcghye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getYcghye()));
             tbTjfxDkghhzxx.setNcghye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getNcghye()));
             tbTjfxDkghhzxx.setGhblye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getGhblye()));
             tbTjfxDkghhzxx.setYcghblye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getGhblye()));
             tbTjfxDkghhzxx.setNcghblye(BigDecimalRoundUtil.round(tbTjfxDkghhzxx.getNcghblye()));
             return Result.ok(tbTjfxDkghhzxx);
         }
         return Result.error(null);
     }

}

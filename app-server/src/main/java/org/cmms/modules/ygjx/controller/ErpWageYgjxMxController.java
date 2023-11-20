package org.cmms.modules.ygjx.controller;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.ListToDictUtil;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMx;
import org.cmms.modules.ygjx.entity.ErpWageYgjxMxVO;
import org.cmms.modules.ygjx.service.IErpWageYgjxMxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.ygjx.service.IErpWageYgjxService;
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
 * @Description: 员工绩效明细
 * @Author: jeecg-boot
 * @Date: 2022-02-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工绩效明细")
@RestController
@RequestMapping("/ygjx/erpWageYgjxMx")
public class ErpWageYgjxMxController extends JeecgController<ErpWageYgjxMx, IErpWageYgjxMxService> {
    @Autowired
    private IErpWageYgjxMxService erpWageYgjxMxService;
    @Autowired
    ListToDictUtil listToDictUtil;
    @Autowired
    IErpWageYgjxService erpWageYgjxService;

    /**
     * 分页列表查询
     *
     * @param erpWageYgjxMx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工绩效明细-分页列表查询")
    @ApiOperation(value = "员工绩效明细-分页列表查询", notes = "员工绩效明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpWageYgjxMx erpWageYgjxMx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String maxDate = erpWageYgjxService.getMaxDate(getWorkNo());
        if (StringUtils.isNotBlank(maxDate)) {
            DateTime parse = DateUtil.parse(maxDate);
            log.info("===当前最大工资查询日期{}===", parse);
            erpWageYgjxMx.setGzrq(parse);
        }
        QueryWrapper<ErpWageYgjxMx> queryWrapper = QueryGenerator.initQueryWrapper(erpWageYgjxMx, req.getParameterMap());
        Page<ErpWageYgjxMx> page = new Page<ErpWageYgjxMx>(pageNo, pageSize);
        IPage<ErpWageYgjxMx> pageList = erpWageYgjxMxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * @param date 控制日期
     * @param zblb 指标分类 0查所有 1存款 2贷款 4业务量 7除（0,1,2,4）的所有
     */

    @RequestMapping("/listAll")
    public Result<?> listAll(String date, String zblb, String yggh, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<ErpWageYgjxMx> page = new Page<ErpWageYgjxMx>(pageNo, pageSize);
        String username = getLoginUser().getWorkNo();
        if (StringUtils.isNotBlank(yggh)) {
            username = yggh;
        }


        if (StringUtils.isNotBlank(date)) {
            //date = date.replaceAll("/","");
            if (date.length() > 7) {
                date = date.replace("/", "-");
                DateTime parse = DateUtil.parse(date, "yyyy-MM-dd");
                date = DateUtil.format(parse, DatePattern.PURE_DATE_PATTERN);

            } else {
                DateTime parse = DateUtil.parse(date, "yyyy/MM");
                DateTime dateTime = DateUtil.endOfMonth(parse);
                date = DateUtil.format(dateTime, DatePattern.PURE_DATE_PATTERN);
            }
        } else {
            String maxDate = erpWageYgjxService.getMaxDate(username);
            if (StringUtils.isNotBlank(maxDate)) {
                String substring = maxDate.substring(0, 11);
                date = substring.replaceAll("-", "");
            } else {
                date = DateUtil.format(new Date(), "yyyyMMdd");
            }
        }
        boolean flag = true;
        if (StringUtils.isNotBlank(date)) {
            String maxDate = erpWageYgjxService.getMaxDate(username);
            if (StringUtils.isNotBlank(maxDate)) {
                String substring = maxDate.substring(0, 7);
                String substring1 = date.substring(0, 6);
                String replace = substring.replace("-", "");
                log.info("===rep||{}===", replace);
                log.info("===date||{}===", substring1);
                Integer integer = Integer.valueOf(substring1);
                Integer integer1 = Integer.valueOf(replace);
                log.info("===integer||{}===", integer);
                log.info("===integer1||{}===", integer1);
                if (integer < integer1)
                    flag = false;
            }
        }

        log.info("===当前绩效查询员工工号{},工资日期{}===", username, date);
        log.info("===flag||{}===", flag);
//		LambdaQueryWrapper<ErpWageYgjxMx> erpWageYgjxMxLambdaQueryWrapper = new LambdaQueryWrapper<>();
//		erpWageYgjxMxLambdaQueryWrapper.eq(ErpWageYgjxMx::getYggh,username);
//		erpWageYgjxMxLambdaQueryWrapper.eq(ErpWageYgjxMx::getGzrq,parse);
//		if (StringUtils.isNotBlank(zblb) && !"0".equals(zblb)){
//			if ("7".equals(zblb)){
//				ArrayList<String> list = new ArrayList();
//				list.add("1");
//				list.add("2");
//				list.add("4");
//				erpWageYgjxMxLambdaQueryWrapper.notIn(ErpWageYgjxMx::getZblb,list);
//			}else {
//				erpWageYgjxMxLambdaQueryWrapper.eq(ErpWageYgjxMx::getZblb,zblb);
//			}
//		}
        IPage<ErpWageYgjxMxVO> list = null;
        if (QydmEnums.JIANGHUA.getQydmCode().equals(getRedisQydm()) && flag) {
            IPage<ErpWageYgjxMxVO> jhList = service.getJhList(page, username, zblb, date);
            //需要处理D90060
            List<ErpWageYgjxMxVO> records = jhList.getRecords();
            if (CollUtil.isNotEmpty(records)) {
                for (int i = 0; i < records.size(); i++) {
                    ErpWageYgjxMxVO erpWageYgjxMxVO = records.get(i);
                    if ("D90060".equals(erpWageYgjxMxVO.getZbid())) {
                        if (erpWageYgjxMxVO.getZbgz() != null) {
                            BigDecimal zbgz = erpWageYgjxMxVO.getZbgz();
                            if (zbgz.compareTo(new BigDecimal(0)) == 0) {
                                //就去找最近的不为0的数据
                                LambdaQueryWrapper<ErpWageYgjxMx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                                lambdaQueryWrapper.eq(ErpWageYgjxMx::getZbid, erpWageYgjxMxVO.getZbid());
                                lambdaQueryWrapper.eq(ErpWageYgjxMx::getYggh, username);
                                lambdaQueryWrapper.gt(ErpWageYgjxMx::getZbgz, 0);
                                lambdaQueryWrapper.orderByDesc(ErpWageYgjxMx::getGzrq);
                                List<ErpWageYgjxMx> list1 = service.list(lambdaQueryWrapper);
                                if (CollUtil.isNotEmpty(list1)) {
                                    erpWageYgjxMxVO.setSfyg("1");
                                    ErpWageYgjxMx erpWageYgjxMx = list1.get(0);
                                    if (erpWageYgjxMx.getZbgz() != null)
                                        erpWageYgjxMxVO.setZbgz(erpWageYgjxMx.getZbgz());
                                    if (erpWageYgjxMx.getZbjg() != null)
                                        erpWageYgjxMxVO.setZbjg(erpWageYgjxMx.getZbjg());
                                }
                            }
                        }
                    }
                }
            }

            list = jhList;
        }
		else if (QydmEnums.TIANYI.getQydmCode().equals(getRedisQydm()) && flag) {
            list = service.getListTy(page, username, zblb, date);
        } else if (QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())) {
		    //绩效3.0版本
            list = service.getListV3(page, username, zblb, date);
        } else {

            list = service.getList(page, username, zblb, date);
        }

        return Result.ok(list);
    }

}

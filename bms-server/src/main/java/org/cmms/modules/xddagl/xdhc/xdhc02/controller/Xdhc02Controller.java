package org.cmms.modules.xddagl.xdhc.xdhc02.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.CldkhtsjglFjxx;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglFjxxService;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service.ICldkhtsjglService;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity.XddaglXlh;
import org.cmms.modules.xddagl.dkdagl.xddaglxlh.service.IXddaglXlhService;
import org.cmms.modules.xddagl.dkdaglfjxx.entity.DkdaglFjxx;
import org.cmms.modules.xddagl.dkdaglfjxx.service.IDkdaglFjxxService;
import org.cmms.modules.xddagl.dqdagl.zlscspxx.entity.Zlscspxx;
import org.cmms.modules.xddagl.dqdagl.zlscspxx.service.IZlscspxxService;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01Fjsc;
import org.cmms.modules.xddagl.xdhc.xdhc01.entity.Xdhc01Vo;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02Fjsc;
import org.cmms.modules.xddagl.xdhc.xdhc02.entity.Xdhc02Vo;
import org.cmms.modules.xddagl.xdhc.xdhc02.service.IXdhc02Service;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xddagl.xdhc.xdhc02.verify.Xdhc02Verify;
import org.cmms.modules.xddagl.xtgl.xddaglcsgl.service.IXddaglcsglService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service.IDkhtsjglService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date: 2022-01-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "信贷T+1核查")
@RestController
@RequestMapping("/xdhc02/xdhc02")
public class Xdhc02Controller extends JeecgController<Xdhc02, IXdhc02Service> {
    @Autowired
    private IXdhc02Service xdhc02Service;
    @Autowired
    private Xdhc02Verify xdhc02Verify;
    @Autowired
    private ICldkhtsjglFjxxService cldkhtsjglFjxxService;
    @Autowired
    private ICldkhtsjglService cldkhtsjglService;
    @Autowired
    private IDkdaglFjxxService dkdaglFjxxService;
    @Autowired
    IDictValueQuery iDictValueQuery;
    @Autowired
    private IDkhtsjglService dkhtsjglService;
    @Autowired
    private IZlscspxxService zlscspxxService;
    @Autowired
    private IXddaglXlhService xddaglXlhService;
    @Autowired
    private IXddaglcsglService iXddaglcsglService;
    private static final String STR_FORMAT = "000000";
    /**
     * 分页列表查询
     *
     * @param xdhc02
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "信贷T+1核查-分页列表查询")
    @ApiOperation(value = "信贷T+1核查-分页列表查询", notes = "信贷T+1核查-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Xdhc02 xdhc02,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("csbm","00001");
        String qyrq = iXddaglcsglService.getOne(wrapper).getCsz();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date = sdf.parse(qyrq);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        QueryWrapper<Xdhc02> queryWrapper = QueryGenerator.initQueryWrapper(xdhc02, req.getParameterMap());
        queryWrapper.ge("qyrq",date);
        queryWrapper.ne("dkpz","13181000000000");
        queryWrapper.ne("dkpz","13181200000000");
        queryWrapper.eq("shzt","1");
        Page<Xdhc02> page = new Page<Xdhc02>(pageNo, pageSize);
        IPage<Xdhc02> pageList = xdhc02Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xdhc02
     * @return
     */
    @AutoLog(value = "信贷T+1核查-添加")
    @ApiOperation(value = "信贷T+1核查-添加", notes = "信贷T+1核查-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Xdhc02 xdhc02) {
        xdhc02Service.save(xdhc02);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param
     * @return
     */
    @AutoLog(value = "信贷T+1核查-编辑")
    @ApiOperation(value = "信贷T+1核查-编辑", notes = "信贷T+1核查-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Xdhc02Fjsc xdhc02Fjsc) {
        String xlhstr="";
        Xdhc02 xdhc02 = new Xdhc02();
        BeanUtils.copyProperties(xdhc02Fjsc, xdhc02);
        JSONArray fjxxs = xdhc02Fjsc.getImgdate();
        String dhglsj = xdhc02Fjsc.getDhglsj();
        CldkhtsjglFjxx fjxx = new CldkhtsjglFjxx();

        if (fjxxs != null && fjxxs.size() >0){
            for (int i = 0; i < fjxxs.size(); i++) {
                String fjname = (String) fjxxs.getJSONObject(i).get("name");
                String  fjlx = fjname.split("_")[0];
                if (fjlx.equals("1")) {
                    QueryWrapper<Xdhc02> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("hth",xdhc02.getHth());
                    queryWrapper.eq("fjlx","1");
                    List<Xdhc02> list = xdhc02Service.list(queryWrapper);
                    if (list.size()>0 && list == null){
                        return Result.error("已存在此合同的档案信息,请勿重复上传！");
                    }
                }
                String wllj = uploadpath + "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                String fwlj = "/" + fjxxs.getJSONObject(i).getJSONObject("response").getString("message");
                fjxx.setWjid(Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")));
                fjxx.setHth(xdhc02.getHth());
                fjxx.setDkzl(xdhc02.getDkpz());
                fjxx.setFjlx(fjlx);
                fjxx.setDhglsj(DateUtil.parse(dhglsj));
                fjxx.setWjlj(wllj);
                fjxx.setFwlj(fwlj);
                fjxx.setLrbz(1);
                fjxx.setLrr(getUsername());
                fjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                cldkhtsjglFjxxService.save(fjxx);
                if (fjlx.equals("1")){
                    xdhc02.setSfscda("是");
                    QueryWrapper<XddaglXlh> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.eq("jgdm",xdhc02.getJgdm());
                    XddaglXlh xlh = xddaglXlhService.getOne(queryWrapper1);
                    if (xlh == null){
                        xlhstr = haoAddOne("0");
                        XddaglXlh xlh1 = new XddaglXlh();
                        xlh1.setXlh(xlhstr);
                        xlh1.setJgdm(xdhc02.getJgdm());
                        xddaglXlhService.save(xlh1);
                    }else {
                        xlhstr = xlh.getXlh();
                        xlh.setXlh(xlhstr);
                        xlh.setJgdm(xdhc02.getJgdm());
                        xddaglXlhService.update(xlh,queryWrapper1);
                    }
                    if (xdhc02.getHth().lastIndexOf("-")>0){
                        xdhc02.setDabh(xdhc02.getJgdm()+"-"+xdhc02.getHth().split("-")[2]+"-"+xlhstr);
                    }else {
                        xdhc02.setDabh(xdhc02.getJgdm()+"-"+xdhc02.getHth().substring(10,14)+"-"+xlhstr);
                    }
                }
            }
        }


        QueryWrapper<Xdhc02> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("hth",xdhc02.getHth());
        Xdhc02 check1 = xdhc02Service.getOne(queryWrapper1);
        if (StringUtils.isNotEmpty(xdhc02.getSfscda())) {
            check1.setSfscda(xdhc02.getSfscda());
        }
        if(StringUtils.isNotEmpty(xdhc02.getDabh())) {
            check1.setDabh(xdhc02.getDabh());
        }
        check1.setDkzrr(xdhc02.getDkzrr());
        check1.setDkpz(xdhc02.getDkpz());
        check1.setDkpzbc(xdhc02.getDkpzbc());
        check1.setLxdz(xdhc02.getLxdz());
        check1.setLxdh(xdhc02.getLxdh());
        check1.setLrsj(new Date());
        check1.setLrbz(2);
        check1.setLrr(getUsername());
        xdhc02Service.update(check1,queryWrapper1);
        return Result.ok("编辑成功!");
    }
    public static String haoAddOne(String liuShuiHao){
        Integer intHao = Integer.parseInt(liuShuiHao);
        intHao++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intHao);
    }

    /**
     * 提取
     *
     */
    @RequestMapping("/init")
    public Result<?> init(){
        Result result = new Result<>();
        try {
            xdhc02Service.pXdhc02();
            result.setSuccess(true);
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败",e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 审核
     */
    @RequestMapping("/audit")
    public Result<?> audit(@RequestBody Xdhc02 xdhc02){
        QueryWrapper<Xdhc02> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hth",xdhc02.getHth());
        xdhc02.setShzt(2);
        xdhc02Service.update(xdhc02,queryWrapper);
        return Result.ok("审核成功");

    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "信贷T+1核查-通过id删除")
    @ApiOperation(value = "信贷T+1核查-通过id删除", notes = "信贷T+1核查-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        xdhc02Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "信贷T+1核查-批量删除")
    @ApiOperation(value = "信贷T+1核查-批量删除", notes = "信贷T+1核查-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.xdhc02Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "信贷T+1核查-通过id查询")
    @ApiOperation(value = "信贷T+1核查-通过id查询", notes = "信贷T+1核查-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Xdhc02 xdhc02 = xdhc02Service.getById(id);
        return Result.ok(xdhc02);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xdhc02
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Xdhc02 xdhc02) {
        return super.exportXls(request, xdhc02, Xdhc02.class, "信贷T+1核查");
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(Xdhc02Vo.class, "信贷T+1核查_02导入模板");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, Xdhc02.class,Xdhc02Vo.class, xdhc02Verify);
    }

}

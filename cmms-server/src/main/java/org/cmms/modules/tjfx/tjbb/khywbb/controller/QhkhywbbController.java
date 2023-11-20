package org.cmms.modules.tjfx.tjbb.khywbb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.tjfx.tjbb.khywbb.entity.Qhkhywbb;
import org.cmms.modules.tjfx.tjbb.khywbb.service.IQhkhywbbService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户业务报表(全行)
 * @Author: jeecg-boot
 * @Date: 2020-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户业务报表(全行)")
@RestController
@RequestMapping("/tjfx.tjbb.khywbb/qhkhywbb")
public class QhkhywbbController extends JeecgController<Qhkhywbb, IQhkhywbbService> {
    @Autowired
    private IQhkhywbbService qhkhywbbService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IEtlSgtzSjtbService etlSgtzSjtbService;

    @Value("${com.etl.etlName:数据下发ETL任务}")
    private String etlName;

    @Value("${com.etl.dagName:etl_day调度}")
    private String dagName;

    /**
     * 分页列表查询
     *
     * @param qhkhywbb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-分页列表查询")
    @ApiOperation(value = "客户业务报表(全行)-分页列表查询", notes = "客户业务报表(全行)-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qhkhywbb qhkhywbb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qhkhywbb> queryWrapper = QueryGenerator.initQueryWrapper(qhkhywbb, req.getParameterMap());
        Page<Qhkhywbb> page = new Page<Qhkhywbb>(pageNo, pageSize);
        IPage<Qhkhywbb> pageList = qhkhywbbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qhkhywbb
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-添加")
    @ApiOperation(value = "客户业务报表(全行)-添加", notes = "客户业务报表(全行)-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Qhkhywbb qhkhywbb) {
        qhkhywbbService.save(qhkhywbb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param qhkhywbb
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-编辑")
    @ApiOperation(value = "客户业务报表(全行)-编辑", notes = "客户业务报表(全行)-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Qhkhywbb qhkhywbb) {
        qhkhywbbService.updateById(qhkhywbb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-通过id删除")
    @ApiOperation(value = "客户业务报表(全行)-通过id删除", notes = "客户业务报表(全行)-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        qhkhywbbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-批量删除")
    @ApiOperation(value = "客户业务报表(全行)-批量删除", notes = "客户业务报表(全行)-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.qhkhywbbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户业务报表(全行)-通过id查询")
    @ApiOperation(value = "客户业务报表(全行)-通过id查询", notes = "客户业务报表(全行)-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Qhkhywbb qhkhywbb = qhkhywbbService.getById(id);
        return Result.ok(qhkhywbb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qhkhywbb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qhkhywbb qhkhywbb) {
        return super.exportXls(request, qhkhywbb, Qhkhywbb.class, "客户业务报表(全行)");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Qhkhywbb.class);
    }

    /**
     * 通过tjyf提取
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract() {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                String zxrkrq = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName);
                qhkhywbbService.extractRC(zxrkrq);
            } else {
                qhkhywbbService.extract();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");
    }
}

package org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.*;
import org.cmms.modules.tjfx.xdgtzyb.grxdgtzyb2.entity.Grxdgtzyb2;
import org.cmms.modules.tjfx.xdgtzyb.grxdgtzyb2.service.IGrxdgtzyb2Service;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.entity.Qhxdgtzyb2;
import org.cmms.modules.tjfx.xdgtzyb.qhxtgtzyb2.service.IQhxdgtzyb2Service;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
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
 * @Description: 全行行动挂图作业表2
 * @Author: jeecg-boot
 * @Date: 2020-03-13
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "全行行动挂图作业表2")
@RestController
@RequestMapping("/tjfx.xdgtzyb.qhxtgtzyb2/qhxdgtzyb2")
public class Qhxdgtzyb2Controller extends JeecgController<Qhxdgtzyb2, IQhxdgtzyb2Service> {
    @Autowired
    private IQhxdgtzyb2Service qhxdgtzyb2Service;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IGrxdgtzyb2Service grxdgtzyb2Service;

    /**
     * 分页列表查询
     *
     * @param qhxdgtzyb2
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-分页列表查询")
    @ApiOperation(value = "全行行动挂图作业表2-分页列表查询", notes = "全行行动挂图作业表2-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qhxdgtzyb2 qhxdgtzyb2,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qhxdgtzyb2> queryWrapper = QueryGenerator.initQueryWrapper(qhxdgtzyb2, req.getParameterMap());
        Page<Qhxdgtzyb2> page = new Page<Qhxdgtzyb2>(pageNo, pageSize);
        IPage<Qhxdgtzyb2> pageList = qhxdgtzyb2Service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qhxdgtzyb2
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-添加")
    @ApiOperation(value = "全行行动挂图作业表2-添加", notes = "全行行动挂图作业表2-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Qhxdgtzyb2 qhxdgtzyb2) {
        qhxdgtzyb2Service.save(qhxdgtzyb2);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param qhxdgtzyb2
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-编辑")
    @ApiOperation(value = "全行行动挂图作业表2-编辑", notes = "全行行动挂图作业表2-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Qhxdgtzyb2 qhxdgtzyb2) {
        qhxdgtzyb2Service.updateById(qhxdgtzyb2);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-通过id删除")
    @ApiOperation(value = "全行行动挂图作业表2-通过id删除", notes = "全行行动挂图作业表2-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        qhxdgtzyb2Service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-批量删除")
    @ApiOperation(value = "全行行动挂图作业表2-批量删除", notes = "全行行动挂图作业表2-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.qhxdgtzyb2Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行行动挂图作业表2-通过id查询")
    @ApiOperation(value = "全行行动挂图作业表2-通过id查询", notes = "全行行动挂图作业表2-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Qhxdgtzyb2 qhxdgtzyb2 = qhxdgtzyb2Service.getById(id);
        return Result.ok(qhxdgtzyb2);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qhxdgtzyb2
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qhxdgtzyb2 qhxdgtzyb2) {
        return super.exportXls(request, qhxdgtzyb2, Qhxdgtzyb2.class, "全行行动挂图作业表2");
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
        return super.importExcel(request, response, Qhxdgtzyb2.class);
    }

    /**
     * 通过tjyf提取
     *
     * @param tjyf
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract(@RequestParam(name = "TJYF") String tjyf) {
        try {
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<Grxdgtzyb2> queryWrapper = new QueryWrapper<Grxdgtzyb2>();
                queryWrapper.eq("TJYF", DateUtil.string2Date(tjyf, "yyyy-MM-dd"));
                grxdgtzyb2Service.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khgl_khhmcxx", "yxdygl_czxxgl", "loan_tjcx_bwdkyeb", "tjfx_cssz").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
                });
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxxdgtzybe.py --fiscal_date " + tjyf + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_XDGTZYBE_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_XDGTZYBE_IMPORT.sh");
            }
            qhxdgtzyb2Service.extract(tjyf);
        } catch (
                Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");
    }


}

package org.cmms.modules.tjfx.tjbb.khxxbb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.tjfx.tjbb.khxxbb.entity.Grkhxxbb;
import org.cmms.modules.tjfx.tjbb.khxxbb.entity.Qhkhxxbb;
import org.cmms.modules.tjfx.tjbb.khxxbb.service.IGrkhxxbbService;
import org.cmms.modules.tjfx.tjbb.khxxbb.service.IQhkhxxbbService;

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
 * @Description: 客户信息报表(全行)
 * @Author: jeecg-boot
 * @Date: 2020-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户信息报表(全行)")
@RestController
@RequestMapping("/tjfx.tjbb.khxxbb/qhkhxxbb")
public class QhkhxxbbController extends JeecgController<Qhkhxxbb, IQhkhxxbbService> {
    @Autowired
    private IQhkhxxbbService qhkhxxbbService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IGrkhxxbbService grkhxxbbService;

    /**
     * 分页列表查询
     *
     * @param qhkhxxbb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-分页列表查询")
    @ApiOperation(value = "客户信息报表(全行)-分页列表查询", notes = "客户信息报表(全行)-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qhkhxxbb qhkhxxbb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qhkhxxbb> queryWrapper = QueryGenerator.initQueryWrapper(qhkhxxbb, req.getParameterMap());
        Page<Qhkhxxbb> page = new Page<Qhkhxxbb>(pageNo, pageSize);
        IPage<Qhkhxxbb> pageList = qhkhxxbbService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param qhkhxxbb
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-添加")
    @ApiOperation(value = "客户信息报表(全行)-添加", notes = "客户信息报表(全行)-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Qhkhxxbb qhkhxxbb) {
        qhkhxxbbService.save(qhkhxxbb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param qhkhxxbb
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-编辑")
    @ApiOperation(value = "客户信息报表(全行)-编辑", notes = "客户信息报表(全行)-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Qhkhxxbb qhkhxxbb) {
        qhkhxxbbService.updateById(qhkhxxbb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-通过id删除")
    @ApiOperation(value = "客户信息报表(全行)-通过id删除", notes = "客户信息报表(全行)-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        qhkhxxbbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-批量删除")
    @ApiOperation(value = "客户信息报表(全行)-批量删除", notes = "客户信息报表(全行)-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.qhkhxxbbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户信息报表(全行)-通过id查询")
    @ApiOperation(value = "客户信息报表(全行)-通过id查询", notes = "客户信息报表(全行)-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Qhkhxxbb qhkhxxbb = qhkhxxbbService.getById(id);
        return Result.ok(qhkhxxbb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qhkhxxbb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qhkhxxbb qhkhxxbb) {
        return super.exportXls(request, qhkhxxbb, Qhkhxxbb.class, "客户信息报表(全行)");
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
        return super.importExcel(request, response, Qhkhxxbb.class);
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
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<Grkhxxbb> queryWrapper = new QueryWrapper<Grkhxxbb>();
                queryWrapper.eq("tjyf", DateUtil.string2Date(tjyf, "yyyy-MM-dd"));
                grkhxxbbService.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khgl_khhmcxx", "yxdygl_czxxgl", "cams_zcsx_nhpjsxxx", "cams_zcsx_shpjsxxx").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
                });
                EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");

                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python tjfxkhxxbb.py --fiscal_date " + tjyf + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_KHXXBB_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_KHXXBB_IMPORT.sh");
            }
            qhkhxxbbService.extract(tjyf);
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");
    }

}

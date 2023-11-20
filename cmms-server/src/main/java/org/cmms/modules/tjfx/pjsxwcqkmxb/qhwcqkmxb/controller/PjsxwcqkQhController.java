package org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.tjfx.pjsxwcqkmxb.grwcqkmxb.entity.PjsxwcqkGr;
import org.cmms.modules.tjfx.pjsxwcqkmxb.grwcqkmxb.service.IPjsxwcqkGrService;
import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.entity.PjsxwcqkQh;
import org.cmms.modules.tjfx.pjsxwcqkmxb.qhwcqkmxb.service.IPjsxwcqkQhService;
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
 * @Description: 全行完成情况明细表
 * @Author: Penghr
 * @Date: 2020-03-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "全行完成情况明细表")
@RestController
@RequestMapping("/tjfx.pjsxwcqkmxb/qhwcqkmxb")
public class PjsxwcqkQhController extends JeecgController<PjsxwcqkQh, IPjsxwcqkQhService> {
    @Autowired
    private IPjsxwcqkQhService pjsxwcqkQhService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IPjsxwcqkGrService pjsxwcqkGrService;

    /**
     * 分页列表查询
     *
     * @param pjsxwcqkQh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-分页列表查询")
    @ApiOperation(value = "全行完成情况明细表-分页列表查询", notes = "全行完成情况明细表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(PjsxwcqkQh pjsxwcqkQh,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<PjsxwcqkQh> queryWrapper = QueryGenerator.initQueryWrapper(pjsxwcqkQh, req.getParameterMap());
        Page<PjsxwcqkQh> page = new Page<PjsxwcqkQh>(pageNo, pageSize);
        IPage<PjsxwcqkQh> pageList = pjsxwcqkQhService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param pjsxwcqkQh
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-添加")
    @ApiOperation(value = "全行完成情况明细表-添加", notes = "全行完成情况明细表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody PjsxwcqkQh pjsxwcqkQh) {
        pjsxwcqkQhService.save(pjsxwcqkQh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param pjsxwcqkQh
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-编辑")
    @ApiOperation(value = "全行完成情况明细表-编辑", notes = "全行完成情况明细表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody PjsxwcqkQh pjsxwcqkQh) {
        pjsxwcqkQhService.updateById(pjsxwcqkQh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-通过id删除")
    @ApiOperation(value = "全行完成情况明细表-通过id删除", notes = "全行完成情况明细表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        pjsxwcqkQhService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-批量删除")
    @ApiOperation(value = "全行完成情况明细表-批量删除", notes = "全行完成情况明细表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.pjsxwcqkQhService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "全行完成情况明细表-通过id查询")
    @ApiOperation(value = "全行完成情况明细表-通过id查询", notes = "全行完成情况明细表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        PjsxwcqkQh pjsxwcqkQh = pjsxwcqkQhService.getById(id);
        return Result.ok(pjsxwcqkQh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param pjsxwcqkQh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PjsxwcqkQh pjsxwcqkQh) {
        return super.exportXls(request, pjsxwcqkQh, PjsxwcqkQh.class, "全行完成情况明细表");
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
        return super.importExcel(request, response, PjsxwcqkQh.class);
    }

    /**
     * 全行完成情况明细表-提取
     *
     * @param object
     * @return
     */
    @PutMapping(value = "/init")
    public Result<?> InitDataToQh(@RequestBody JSONObject object) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<PjsxwcqkGr> queryWrapper=new QueryWrapper<PjsxwcqkGr>();
                Date ksrq1 = DateUtil.string2Date(object.getString("ksrq"), "yyyy-MM-dd");
                Date jsrq1 = DateUtil.string2Date(object.getString("jsrq"), "yyyy-MM-dd");
                queryWrapper.eq("ksrq",ksrq1).eq("jsrq",jsrq1);
                pjsxwcqkGrService.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khgl_khhmcxx", "yxdygl_czxxgl", "yxgl_khhfxxb", "cams_jbxx_nhzllb", "cams_zcsx_nhpjsxxx").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true,false,false, item, "idap");
                });
                //调用python脚本
                String ksrq = object.getString("ksrq");
                String jsrq = object.getString("jsrq");
                String jgdm = object.getString("jgdm");
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ &&source ./env_py2.7.sh && cd /root/trunk/src/tests/&& python exec_tjfxpjsxwcqkb.py --ld_ksrq " + ksrq + " --jsrq " + jsrq + " --jgdm "+jgdm+"'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_PJSXWCQKB_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_PJSXWCQKB_IMPORT.sh");
            }
            Map<String, String> param = new HashMap<>();
            param.put("ksrq", object.getString("ksrq"));
            param.put("jsrq", object.getString("jsrq"));
            param.put("jgdm", object.getString("jgdm"));
            pjsxwcqkQhService.InitDataToQh(param);
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}

package org.cmms.modules.khxxgl.clkhxx.controller;

import java.util.Arrays;
import java.util.HashMap;
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
import org.cmms.common.util.SshUtil;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjbxxService;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhxxglService;
import org.cmms.modules.khxxgl.clkhxx.entity.Clkhgl;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 存量客户管理
 * @Author: jeecg-boot
 * @Date: 2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存量客户管理")
@RestController
@RequestMapping("/clkhxx/clkhxxgl")
public class ClkhglController extends JeecgController<Clkhgl, IClkhglService> {
    @Autowired
    private IClkhglService clkhxxglService;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private ISysDicService sysDicService;
	@Autowired
	private IClgrkhjbxxService clgrkhjbxxService;
	@Autowired
	private IClkhxxglService clkhxxglService1;

    /**
     * 分页列表查询
     *
     * @param clkhxxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存量客户管理-分页列表查询")
    @ApiOperation(value = "存量客户管理-分页列表查询", notes = "存量客户管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Clkhgl clkhxxgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Clkhgl> queryWrapper = QueryGenerator.initQueryWrapper(clkhxxgl, req.getParameterMap());
        Page<Clkhgl> page = new Page<Clkhgl>(pageNo, pageSize);
        IPage<Clkhgl> pageList = clkhxxglService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/extract")
    public Result<?> extract() {
        Result result = new Result<>();
        try {
            String qybm = sysDicService.queryByCode("101001").getValue();
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                //同步数据到impala
                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_qykhjbxx", "idap");
                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_grkhjbxx", "idap");
                EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clgrkhxx.py'");
                sshUtil.execShell("sh /home/exportdata/P_CLGRKHXX_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_CLGRKHXX_IMPORT.sh");

                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clqykhxx.py'");
                sshUtil.execShell("sh /home/exportdata/P_CLQYKHXX_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_CLQYKHXX_IMPORT.sh");

				clkhxxglService1.initClkhxxRC();
                System.out.println("执行完成");
            } else {
                clkhxxglService.extract();
            }
            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 添加
     *
     * @param clkhxxgl
     * @return
     */
    @AutoLog(value = "存量客户管理-添加")
    @ApiOperation(value = "存量客户管理-添加", notes = "存量客户管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Clkhgl clkhxxgl) {
        clkhxxglService.save(clkhxxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param clkhxxgl
     * @return
     */
    @AutoLog(value = "存量客户管理-编辑")
    @ApiOperation(value = "存量客户管理-编辑", notes = "存量客户管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Clkhgl clkhxxgl) {
        clkhxxglService.updateById(clkhxxgl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量客户管理-通过id删除")
    @ApiOperation(value = "存量客户管理-通过id删除", notes = "存量客户管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        clkhxxglService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存量客户管理-批量删除")
    @ApiOperation(value = "存量客户管理-批量删除", notes = "存量客户管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.clkhxxglService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量客户管理-通过id查询")
    @ApiOperation(value = "存量客户管理-通过id查询", notes = "存量客户管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Clkhgl clkhxxgl = clkhxxglService.getById(id);
        return Result.ok(clkhxxgl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param clkhxxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Clkhgl clkhxxgl) {
        return super.exportXls(request, clkhxxgl, Clkhgl.class, "存量客户管理");
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
        return super.importExcel(request, response, Clkhgl.class);
    }

}

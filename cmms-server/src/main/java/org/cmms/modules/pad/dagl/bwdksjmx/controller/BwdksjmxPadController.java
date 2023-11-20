package org.cmms.modules.pad.dagl.bwdksjmx.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.pad.dagl.bwdkcsgl.entity.Bwdkcsgl;
import org.cmms.modules.pad.dagl.bwdkcsgl.service.IBwdkcsglService;
import org.cmms.modules.pad.dagl.bwdkrzjl.entity.Bwdkrzjl;
import org.cmms.modules.pad.dagl.bwdkrzjl.service.IBwdkrzjlService;
import org.cmms.modules.pad.dagl.bwdksjmx.entity.BwdksjmxPad;
import org.cmms.modules.pad.dagl.bwdksjmx.service.IBwdksjmxPadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 表外贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2023-07-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外贷款数据明细")
@RestController
@RequestMapping("/bwdkgl/bwdksjmx")
public class BwdksjmxPadController extends JeecgController<BwdksjmxPad, IBwdksjmxPadService> {
	@Autowired
	private IBwdksjmxPadService bwdksjmxService;
     @Autowired
     private IBwdkcsglService bwdkcsglService;
     @Autowired
     private IBwdkrzjlService bwdkrzjlService;
	/**
	 * 分页列表查询
	 *
	 * @param bwdksjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-分页列表查询")
	@ApiOperation(value="表外贷款数据明细-分页列表查询", notes="表外贷款数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(BwdksjmxPad bwdksjmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BwdksjmxPad> queryWrapper = QueryGenerator.initQueryWrapper(bwdksjmx, req.getParameterMap());
		Page<BwdksjmxPad> page = new Page<BwdksjmxPad>(pageNo, pageSize);
		IPage<BwdksjmxPad> pageList = bwdksjmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-添加")
	@ApiOperation(value="表外贷款数据明细-添加", notes="表外贷款数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody BwdksjmxPad bwdksjmx) {
		bwdksjmxService.save(bwdksjmx);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param bwdksjmx
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-编辑")
	@ApiOperation(value="表外贷款数据明细-编辑", notes="表外贷款数据明细-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody BwdksjmxPad bwdksjmx) {
		QueryWrapper<BwdksjmxPad> existQueryWrapper = new QueryWrapper<>();
		existQueryWrapper.eq("dkzh", bwdksjmx.getDkzh());
		BwdksjmxPad existBwdksjmx = bwdksjmxService.getOne(existQueryWrapper);
		if(existBwdksjmx == null) {
			return Result.error("不存在该账号信息！");
		}
        QueryWrapper<BwdksjmxPad> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh", bwdksjmx.getDkzh());
		if(StringUtils.isNotEmpty(bwdksjmx.getZjhm()) && bwdksjmx.getZjhm().contains("*")) {
			bwdksjmx.setZjhm(existBwdksjmx.getZjhm());
		}
        if (bwdksjmx.getZjcsrq() != null) {
            QueryWrapper<Bwdkcsgl> bwdkcsglQueryWrapper = new QueryWrapper<Bwdkcsgl>();
            bwdkcsglQueryWrapper.eq("csbm", "P00008");
            Bwdkcsgl bwdkcsgl = bwdkcsglService.getOne(bwdkcsglQueryWrapper);
            if (bwdkcsgl == null) {
                return Result.error("未找到诉讼时效周期参数！");
            }
            Integer csz = -Integer.parseInt(bwdkcsgl.getCsz());

            Date csrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.getFrontYearTime(bwdksjmx.getZjcsrq().getTime(), csz)), "yyyy-MM-dd");
            if(bwdksjmx.getDqrq() != null && bwdksjmx.getZjcsrq().compareTo(bwdksjmx.getDqrq()) < 0) {
                csrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.getFrontYearTime(bwdksjmx.getDqrq().getTime(), csz)), "yyyy-MM-dd");
            }

            String date = DateUtil.format(csrq, "yyyyMMdd");
            String now  = DateUtil.formatDateTime("yyyyMMdd");
            bwdksjmx.setSssxdqr(new Timestamp(csrq.getTime()));
            if (Integer.parseInt(date)>Integer.parseInt(now)){
                bwdksjmx.setSssx("1");
            }else{
                bwdksjmx.setSssx("0");
            }
        }
		bwdksjmxService.update(bwdksjmx, queryWrapper);
        //添加日志
        Bwdkrzjl bwdkrzjl = new Bwdkrzjl();
        bwdkrzjl.setDkzh(bwdksjmx.getDkzh());
        bwdkrzjl.setUsername(getUsername());
        bwdkrzjl.setCzgn("基本信息");
        bwdkrzjl.setCzsj(new Date());
        bwdkrzjl.setCznr("修改基本信息");
        bwdkrzjlService.save(bwdkrzjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-通过id删除")
	@ApiOperation(value="表外贷款数据明细-通过id删除", notes="表外贷款数据明细-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bwdksjmxService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-批量删除")
	@ApiOperation(value="表外贷款数据明细-批量删除", notes="表外贷款数据明细-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bwdksjmxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款数据明细-通过id查询")
	@ApiOperation(value="表外贷款数据明细-通过id查询", notes="表外贷款数据明细-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		BwdksjmxPad bwdksjmx = bwdksjmxService.getById(id);
		return Result.ok(bwdksjmx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bwdksjmx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, BwdksjmxPad bwdksjmx) {
      return super.exportXls(request, bwdksjmx, BwdksjmxPad.class, "表外贷款数据明细");
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
      return super.importExcel(request, response, BwdksjmxPad.class);
  }

}

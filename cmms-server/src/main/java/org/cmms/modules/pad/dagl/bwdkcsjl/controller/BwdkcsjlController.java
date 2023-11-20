package org.cmms.modules.pad.dagl.bwdkcsjl.controller;

import java.io.File;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.pad.dagl.bwdkcsgl.entity.Bwdkcsgl;
import org.cmms.modules.pad.dagl.bwdkcsgl.service.IBwdkcsglService;
import org.cmms.modules.pad.dagl.bwdkcsjl.entity.Bwdkcsjl;
import org.cmms.modules.pad.dagl.bwdkcsjl.service.IBwdkcsjlService;
import org.cmms.modules.pad.dagl.bwdkrzjl.entity.Bwdkrzjl;
import org.cmms.modules.pad.dagl.bwdkrzjl.service.IBwdkrzjlService;
import org.cmms.modules.pad.dagl.bwdksjmx.entity.BwdksjmxPad;
import org.cmms.modules.pad.dagl.bwdksjmx.service.IBwdksjmxPadService;
import org.cmms.modules.pad.dagl.wjxx.entity.ErpBasWjxx;
import org.cmms.modules.pad.dagl.wjxx.service.IErpBasWjxxService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 表外贷款催收记录
 * @Author: jeecg-boot
 * @Date:   2023-07-12
 * @Version: V1.0
 */
@Slf4j
@Api(tags="表外贷款催收记录")
@RestController
@RequestMapping("/bwdkgl/bwdkcsjl")
public class BwdkcsjlController extends JeecgController<Bwdkcsjl, IBwdkcsjlService> {
	@Autowired
	private IBwdkcsjlService bwdkcsjlService;
	@Autowired
	private IErpBasWjxxService erpBasWjxxService;
	@Autowired
	private IBwdkcsglService bwdkcsglService;
	@Autowired
	private IBwdksjmxPadService bwdksjmxService;
	@Autowired
    private IBwdkrzjlService bwdkrzjlService;
	 @Autowired
	 IDictValueQuery iDictValueQuery;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param bwdkcsjl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-分页列表查询")
	@ApiOperation(value="表外贷款催收记录-分页列表查询", notes="表外贷款催收记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Bwdkcsjl bwdkcsjl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   String csnf, //催收年份
								   HttpServletRequest req) {
		QueryWrapper<Bwdkcsjl> queryWrapper = QueryGenerator.initQueryWrapper(bwdkcsjl, req.getParameterMap());
		if (StringUtils.isNotEmpty(csnf)) {
			Date beginDate = DateUtil.parseDateFormat(csnf + "0101", "yyyyMMdd");
			Date endDate = DateUtil.parseDateFormat(csnf + "1231", "yyyyMMdd");
			queryWrapper.ge("csrq", beginDate);
			queryWrapper.le("csrq", endDate);
		}
		queryWrapper.orderByDesc("csrq");
		Page<Bwdkcsjl> page = new Page<Bwdkcsjl>(pageNo, pageSize);
		IPage<Bwdkcsjl> pageList = bwdkcsjlService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param bwdkcsjl
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-添加")
	@ApiOperation(value="表外贷款催收记录-添加", notes="表外贷款催收记录-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Bwdkcsjl bwdkcsjl) {
		QueryWrapper<BwdksjmxPad> bwdksjmxQueryWrapper = new QueryWrapper<BwdksjmxPad>();
		bwdksjmxQueryWrapper.eq("dkzh", bwdkcsjl.getDkzh());
		List<BwdksjmxPad> bwdkList = bwdksjmxService.list(bwdksjmxQueryWrapper);
		if (bwdkList.isEmpty()) {
			return Result.error("表外账号不存在！");
		}
		BwdksjmxPad bwdksjmx = bwdkList.get(0);
		QueryWrapper<Bwdkcsjl> existCheck = new QueryWrapper<>();
		existCheck.eq("dkzh", bwdkcsjl.getDkzh());
		existCheck.eq("csrgh", bwdkcsjl.getCsrgh());
		existCheck.eq("csrq", bwdkcsjl.getCsrq());
		List<Bwdkcsjl> existList = bwdkcsjlService.list(existCheck);
		if (!existList.isEmpty()) {
			return Result.error("已经存在当前催收人当前催收日期的催收记录！");
		}

		QueryWrapper<Bwdkcsgl> bwdkcsglQueryWrapper = new QueryWrapper<Bwdkcsgl>();
		bwdkcsglQueryWrapper.eq("csbm", "P00008");
		Bwdkcsgl bwdkcsgl = bwdkcsglService.getOne(bwdkcsglQueryWrapper);
		if (bwdkcsgl == null) {
			return Result.error("未找到诉讼时效周期参数！");
		}
		Integer csz = -Integer.parseInt(bwdkcsgl.getCsz());

		// 如果催收日期+2年小于系统日期，则提示无效催收
		// 催收日期跟到期日期比较，如果催收日期在到期日期之前，则使用到期日期
		Date csrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.getFrontYearTime(bwdkcsjl.getCsrq().getTime(), csz)), "yyyy-MM-dd");
		if(bwdksjmx.getDqrq() != null && bwdkcsjl.getCsrq().compareTo(bwdksjmx.getDqrq()) < 0) {
			csrq = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd", DateUtil.getFrontYearTime(bwdksjmx.getDqrq().getTime(), csz)), "yyyy-MM-dd");
		}
		Date nowDate = DateUtil.parseDateFormat(DateUtil.formatDateTime("yyyy-MM-dd"), "yyyy-MM-dd");
		if(nowDate.compareTo(csrq) > 0) {
			return Result.error("无效催收!");
		}

		// 更新表外贷款数据台账中的催收日期、诉讼时效到期日（催收日期+2年）
		if (bwdksjmx.getZjcsrq() == null ||  new Date(bwdksjmx.getZjcsrq().getTime()).compareTo(new Date(bwdkcsjl.getCsrq().getTime())) <= 0 ) {
			BwdksjmxPad update = new BwdksjmxPad();
			update.setSssxdqr(csrq);
			update.setZjcsrq(bwdkcsjl.getCsrq());
			update.setSssx("1");

			UpdateWrapper<BwdksjmxPad> updateWrapper = new UpdateWrapper<>();
			updateWrapper.eq("dkzh", bwdkcsjl.getDkzh());
			bwdksjmxService.update(update, updateWrapper);
		}
		bwdkcsjl.setJgdm(bwdksjmx.getJgdm());
		bwdkcsjl.setKhmc(bwdksjmx.getKhmc());
		bwdkcsjl.setZrbwlx(bwdksjmx.getZrbwlx());
		bwdkcsjl.setZjhm(bwdksjmx.getZjhm());
		bwdkcsjl.setJjmc(bwdksjmx.getJjmc());
		bwdkcsjl.setDkrq(bwdksjmx.getDkrq());
		bwdkcsjl.setDqrq(bwdksjmx.getDqrq());
		bwdkcsjl.setHxye(bwdksjmx.getHxye());
		bwdkcsjl.setLrbz(1);
		bwdkcsjl.setLrr(getUsername());
		bwdkcsjl.setLrsj(new Date());
		bwdkcsjlService.save(bwdkcsjl);
		//添加日志
        Bwdkrzjl bwdkrzjl = new Bwdkrzjl();
        bwdkrzjl.setDkzh(bwdkcsjl.getDkzh());
        bwdkrzjl.setUsername(getUsername());
        bwdkrzjl.setCzgn("催收管理");
        bwdkrzjl.setCzsj(new Date());
        bwdkrzjl.setCznr("新增催收信息");
        bwdkrzjlService.save(bwdkrzjl);
		return Result.ok("添加成功！");
	}

	 @PostMapping(value = "/addFjxx")
	 public Result<?> addFjxx(@RequestBody List<ErpBasWjxx> list) {
		 String ywid = iDictValueQuery.getSeqNextval("SEQ_PUBLIC_ID.nextval");
		 if (list != null && list.size() > 0) {
		 	 List<ErpBasWjxx> insertList = new ArrayList<>();
			 for (int i = 0; i < list.size(); i++) {
				 ErpBasWjxx wjxx = list.get(i);
			     ErpBasWjxx insert = new ErpBasWjxx();
				 String wjid = iDictValueQuery.getSeqNextval("SEQ_PUBLIC_ID.nextval");
				 insert.setWjid(Integer.parseInt(wjid));
				 insert.setYwid(Integer.parseInt(ywid));
				 insert.setYwlx(1);
				 insert.setWjlj(uploadpath + File.separator + wjxx.getFwlj());
				 insert.setFwlj(wjxx.getFwlj());
				 insert.setDkzh(wjxx.getDkzh());
				 insert.setFjlx(wjxx.getFjlx());
				 insert.setLrr(getUsername());
				 insert.setLrbz(1);
				 insertList.add(insert);
			 }
			 erpBasWjxxService.saveBatch(insertList);
		 }
		 return Result.ok(ywid);
	 }
	
	/**
	 * 编辑
	 *
	 * @param bwdkcsjl
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-编辑")
	@ApiOperation(value="表外贷款催收记录-编辑", notes="表外贷款催收记录-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Bwdkcsjl bwdkcsjl) {
		bwdkcsjlService.updateById(bwdkcsjl);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-通过id删除")
	@ApiOperation(value="表外贷款催收记录-通过id删除", notes="表外贷款催收记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		bwdkcsjlService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-批量删除")
	@ApiOperation(value="表外贷款催收记录-批量删除", notes="表外贷款催收记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bwdkcsjlService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "表外贷款催收记录-通过id查询")
	@ApiOperation(value="表外贷款催收记录-通过id查询", notes="表外贷款催收记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Bwdkcsjl bwdkcsjl = bwdkcsjlService.getById(id);
		return Result.ok(bwdkcsjl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param bwdkcsjl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Bwdkcsjl bwdkcsjl) {
      return super.exportXls(request, bwdkcsjl, Bwdkcsjl.class, "表外贷款催收记录");
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
      return super.importExcel(request, response, Bwdkcsjl.class);
  }

}

package org.cmms.modules.system.util;

import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.DpJdgl;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.entity.SysDepart;
import org.cmms.modules.system.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <P>
 * 对应部门的表,处理并查找树级数据
 * <P>
 * 
 * @Author: Steve
 * @Date: 2019-01-22
 */

public class FindsDpjdChildrenUtil {

	//部门树信息-树结构
	//private static List<SysDepartTreeModel> sysDepartTreeList = new ArrayList<SysDepartTreeModel>();
	
	//部门树id-树结构
    //private static List<DepartIdModel> idList = new ArrayList<>();


    /**
     * queryTreeList的子方法 ====1=====
     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<DpJdglTreeModel> wrapTreeDataToTreeList(List<DpJdgl> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	List<DpJdglModel> idList = new ArrayList<DpJdglModel>();
        List<DpJdglTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            DpJdgl depart = recordList.get(i);
            records.add(new DpJdglTreeModel(depart));
        }
        List<DpJdglTreeModel> tree = findChildren(records, idList);

        setEmptyChildrenAsNull(tree);
        return tree;
    }


    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<DpJdglTreeModel> findChildren(List<DpJdglTreeModel> recordList,
                                                         List<DpJdglModel> departIdList) {

        List<DpJdglTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            DpJdglTreeModel branch = recordList.get(i);
            if (branch.getSjid().equals("0")) {
                treeList.add(branch);
                DpJdglModel departIdModel = new DpJdglModel().convert(branch);
                departIdList.add(departIdModel);
            }
        }
        getGrandChildren(treeList,recordList,departIdList);
        
        //idList = departIdList;
        return treeList;
    }

    /**
     * queryTreeList的子方法====3====
     *该方法是找到顶级父类下的所有子节点集合并封装到TreeList集合
     */
    private static void getGrandChildren(List<DpJdglTreeModel> treeList,List<DpJdglTreeModel> recordList,List<DpJdglModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            DpJdglTreeModel model = treeList.get(i);
            DpJdglModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                DpJdglTreeModel m = recordList.get(i1);
                if (m.getSjid()!=null && m.getSjid().equals(model.getId())) {
                    model.getChildren().add(m);
                    DpJdglModel dim = new DpJdglModel().convert(m);
                    idModel.getChildren().add(dim);
                }
            }
            getGrandChildren(treeList.get(i).getChildren(), recordList, idList.get(i).getChildren());
        }

    }

    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNull(List<DpJdglTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            DpJdglTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNull(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }
    /**
     * queryTreeList的子方法 ====4====
     * 该方法是将子节点为空的List集合设置为Null值
     */
    private static void setEmptyChildrenAsNullHr(List<DpJdglTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            DpJdglTreeModel model = treeList.get(i);
            if (model.getChildren().size() == 0) {
                model.setChildren(null);
                model.setIsLeaf(true);
            }else{
                setEmptyChildrenAsNullHr(model.getChildren());
                model.setIsLeaf(false);
            }
        }
        // sysDepartTreeList = treeList;
    }
}

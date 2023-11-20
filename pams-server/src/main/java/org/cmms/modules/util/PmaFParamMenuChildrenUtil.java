package org.cmms.modules.util;

import org.cmms.modules.khlc.csgl.csml.entity.PmaFParamMenu;
import org.cmms.modules.khlc.csgl.csml.model.PmaFParamMenuModel;
import org.cmms.modules.khlc.csgl.csml.model.PmaFParamMenuTreeModel;

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

public class PmaFParamMenuChildrenUtil {

	//部门树信息-树结构
	//private static List<SysDepartTreeModel> sysDepartTreeList = new ArrayList<SysDepartTreeModel>();
	
	//部门树id-树结构
    //private static List<DepartIdModel> idList = new ArrayList<>();


    /**
     * queryTreeList的子方法 ====1=====

     * 该方法是s将SysDepart类型的list集合转换成SysDepartTreeModel类型的集合
     */
    public static List<PmaFParamMenuTreeModel> wrapTreeDataToTreeList(List<PmaFParamMenu> recordList) {
        // 在该方法每请求一次,都要对全局list集合进行一次清理
        //idList.clear();
    	List<PmaFParamMenuModel> idList = new ArrayList<PmaFParamMenuModel>();
        List<PmaFParamMenuTreeModel> records = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            PmaFParamMenu depart = recordList.get(i);
            records.add(new PmaFParamMenuTreeModel(depart));
        }
        List<PmaFParamMenuTreeModel> tree = findChildren(records, idList);

        setEmptyChildrenAsNull(tree);
        return tree;
    }


    /**
     * queryTreeList的子方法 ====2=====
     * 该方法是找到并封装顶级父类的节点到TreeList集合
     */
    private static List<PmaFParamMenuTreeModel> findChildren(List<PmaFParamMenuTreeModel> recordList,
                                                         List<PmaFParamMenuModel> departIdList) {

        List<PmaFParamMenuTreeModel> treeList = new ArrayList<>();
        for (int i = 0; i < recordList.size(); i++) {
            PmaFParamMenuTreeModel branch = recordList.get(i);
            if (branch.getParentDirId().equals("0")) {
                treeList.add(branch);
                PmaFParamMenuModel departIdModel = new PmaFParamMenuModel().convert(branch);
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
    private static void getGrandChildren(List<PmaFParamMenuTreeModel> treeList,List<PmaFParamMenuTreeModel> recordList,List<PmaFParamMenuModel> idList) {

        for (int i = 0; i < treeList.size(); i++) {
            PmaFParamMenuTreeModel model = treeList.get(i);
            PmaFParamMenuModel idModel = idList.get(i);
            for (int i1 = 0; i1 < recordList.size(); i1++) {
                PmaFParamMenuTreeModel m = recordList.get(i1);
                if (m.getParentDirId()!=null && m.getParentDirId().equals(model.getId())) {
                    model.getChildren().add(m);
                    PmaFParamMenuModel dim = new PmaFParamMenuModel().convert(m);
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
    private static void setEmptyChildrenAsNull(List<PmaFParamMenuTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            PmaFParamMenuTreeModel model = treeList.get(i);
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
    private static void setEmptyChildrenAsNullHr(List<PmaFParamMenuTreeModel> treeList) {

        for (int i = 0; i < treeList.size(); i++) {
            PmaFParamMenuTreeModel model = treeList.get(i);
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

package org.cmms.common.cal;

import java.text.NumberFormat;

/**
 * 表达式节点
 */
public class CalculationNode {
    /**
     * 上一节点对象
     * 为空时表示是首
     */
    private CalculationNode front;
    /**
     * 下一节点对象
     * 为空时表示为末尾
     */
    private CalculationNode next;
    /**
     * 节点表达式字符串
     */
    private String nodeStr;
    /**
     * 是否计算元结点(加、减、乘、除、左括号、右括号)
     * +、-、*、/、(、)
     */
    private boolean isPix;

    /**
     * 表达式节点对应的单元格
     */
    //private ExlsCell nodeCell1;
    private Double nodeValue;

    public static CalculationNode createRootNode() {
        return new CalculationNode(null, false, null);
    }

    public static CalculationNode createRootNode(String nodeStr, boolean isPix) {
        return new CalculationNode(nodeStr, isPix, null);
    }

    public String getNodeStr() {
        return nodeStr;
    }

    public boolean isPix() {
        return isPix;
    }

    public CalculationNode setNextNode(String nodeStr, boolean isPix) {
        if (this.nodeStr == null) {
            this.nodeStr = nodeStr;
            this.isPix = isPix;
            return this;
        } else {
            return new CalculationNode(nodeStr, isPix, this);
        }
    }

    public CalculationNode(String nodeStr, boolean isPix, CalculationNode front) {
        this.nodeStr = nodeStr;
        this.isPix = isPix;
        this.front = front;
        if (this.front != null) {
            this.next = this.front.next;
            this.front.next = this;
        }
    }

    public CalculationNode next() {
        return next;
    }

    public CalculationNode front() {
        return front;
    }

    public boolean isTop() {
        return front == null;
    }

    public CalculationNode top() {
        if (front != null) {
            return front.top();
        }
        return this;
    }

    public boolean isBottom() {
        return next == null;
    }

    public CalculationNode bottom() {
        if (next != null) {
            return next.bottom();
        }
        return this;
    }

//    public ExlsCell getNodeCell() {
//        return nodeCell;
//    }
//
//    public void setNodeCell(ExlsCell nodeCell) {
//        this.nodeCell = nodeCell;
//    }


    public Double getNodeValue() {
        return nodeValue;
    }

    public String getNodeValueStr() {
        if (nodeValue == null) {
            return "";
        }
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(6);
        return nf.format(nodeValue);
    }

    public void setNodeValue(double nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String toString() {
        if (isPix()) {
            return (nodeStr==null?"":nodeStr) + (next == null ? "" : next);
        } else {
            //return LangUtil.double2String(nodeValue.readValueDouble(),2) + (next == null ? "" : next);
            return getNodeValueStr() + "" + (next == null ? "" : next);
        }

    }
}

package org.cmms.common.cal;

/**
 * 公式表达式
 * 将：$[] + $[]
 * 解析为：
 * $[]
 * +
 * $[]
 */
public class CalculationFormula {
    private String calculation;
    private CalculationNode node;

    public CalculationFormula(String str) {
        this.calculation = str;
        parser();
    }

    public String getCalculation() {
        return calculation;
    }

    public CalculationNode getNode() {
        return node;
    }


    private void parser() {
        node = CalculationNode.createRootNode();
        char[] chars = calculation.toCharArray();
        char[] nChars = new char[chars.length];
        int nc = 0;
        boolean isM = false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '$' && (i + 1) < chars.length && chars[i+1] == '[') {//$[]中的内容不进行拆分
                isM = true;
            }

            if (isM) {
                nChars[nc++] = c;
                if (c == ']') {
                    isM = false;
                }
                continue;
            }


            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
                String tmp = new String(nChars, 0, nc);
                if (!"".equalsIgnoreCase(tmp)) {
                    node = node.setNextNode(tmp, false);
                }
                node = node.setNextNode(String.valueOf(c), true);
                nc = 0;
                continue;
            }
            nChars[nc++] = c;
        }
        if (nc > 0) {
            String tmp = new String(nChars, 0, nc);
            node = node.setNextNode(tmp, false);
        }
        node = node.top();
//        while (true) {
//            System.out.println(node);
//            node = node.next();
//            if (node == null)
//                break;
//        }
    }


}



package org.cmms.common.excel;

import org.cmms.common.util.StringUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/10/9.
 */
public class XlsDic {

    private static HashMap<String, Integer> COLNUM = new HashMap<String, Integer>();
    private static int NOWI = 0;

    static {
        NOWI = 0;
        COLNUM.put("A", NOWI++);
        COLNUM.put("B", NOWI++);
        COLNUM.put("C", NOWI++);
        COLNUM.put("D", NOWI++);
        COLNUM.put("E", NOWI++);
        COLNUM.put("F", NOWI++);
        COLNUM.put("G", NOWI++);
        COLNUM.put("H", NOWI++);
        COLNUM.put("I", NOWI++);
        COLNUM.put("J", NOWI++);
        COLNUM.put("K", NOWI++);
        COLNUM.put("L", NOWI++);
        COLNUM.put("M", NOWI++);
        COLNUM.put("N", NOWI++);
        COLNUM.put("O", NOWI++);
        COLNUM.put("P", NOWI++);
        COLNUM.put("Q", NOWI++);
        COLNUM.put("R", NOWI++);
        COLNUM.put("S", NOWI++);
        COLNUM.put("T", NOWI++);
        COLNUM.put("U", NOWI++);
        COLNUM.put("V", NOWI++);
        COLNUM.put("W", NOWI++);
        COLNUM.put("X", NOWI++);
        COLNUM.put("Y", NOWI++);
        COLNUM.put("Z", NOWI++);
        COLNUM.put("AA", NOWI++);
        COLNUM.put("AB", NOWI++);
        COLNUM.put("AC", NOWI++);
        COLNUM.put("AD", NOWI++);
        COLNUM.put("AE", NOWI++);
        COLNUM.put("AF", NOWI++);
        COLNUM.put("AG", NOWI++);
        COLNUM.put("AH", NOWI++);
        COLNUM.put("AI", NOWI++);
        COLNUM.put("AJ", NOWI++);
        COLNUM.put("AK", NOWI++);
        COLNUM.put("AL", NOWI++);
        COLNUM.put("AM", NOWI++);
        COLNUM.put("AN", NOWI++);
        COLNUM.put("AO", NOWI++);
        COLNUM.put("AP", NOWI++);
        COLNUM.put("AQ", NOWI++);
        COLNUM.put("AR", NOWI++);
        COLNUM.put("AS", NOWI++);
        COLNUM.put("AT", NOWI++);
        COLNUM.put("AU", NOWI++);
        COLNUM.put("AV", NOWI++);
        COLNUM.put("AW", NOWI++);
        COLNUM.put("AX", NOWI++);
        COLNUM.put("AY", NOWI++);
        COLNUM.put("AZ", NOWI++);

        COLNUM.put("BA", NOWI++);
        COLNUM.put("BB", NOWI++);
        COLNUM.put("BC", NOWI++);
        COLNUM.put("BD", NOWI++);
        COLNUM.put("BE", NOWI++);
        COLNUM.put("BF", NOWI++);
        COLNUM.put("BG", NOWI++);
        COLNUM.put("BH", NOWI++);
        COLNUM.put("BI", NOWI++);
        COLNUM.put("BJ", NOWI++);
        COLNUM.put("BK", NOWI++);
        COLNUM.put("BL", NOWI++);
        COLNUM.put("BM", NOWI++);
        COLNUM.put("BN", NOWI++);
        COLNUM.put("BO", NOWI++);
        COLNUM.put("BP", NOWI++);
        COLNUM.put("BQ", NOWI++);
        COLNUM.put("BR", NOWI++);
        COLNUM.put("BS", NOWI++);
        COLNUM.put("BT", NOWI++);
        COLNUM.put("BU", NOWI++);
        COLNUM.put("BV", NOWI++);
        COLNUM.put("BW", NOWI++);
        COLNUM.put("BX", NOWI++);
        COLNUM.put("BY", NOWI++);
        COLNUM.put("BZ", NOWI++);

        COLNUM.put("CA", NOWI++);
        COLNUM.put("CB", NOWI++);
        COLNUM.put("CC", NOWI++);
        COLNUM.put("CD", NOWI++);
        COLNUM.put("CE", NOWI++);
        COLNUM.put("CF", NOWI++);
        COLNUM.put("CG", NOWI++);
        COLNUM.put("CH", NOWI++);
        COLNUM.put("CI", NOWI++);
        COLNUM.put("CJ", NOWI++);
        COLNUM.put("CK", NOWI++);
        COLNUM.put("CL", NOWI++);
        COLNUM.put("CM", NOWI++);
        COLNUM.put("CN", NOWI++);
        COLNUM.put("CO", NOWI++);
        COLNUM.put("CP", NOWI++);
        COLNUM.put("CQ", NOWI++);
        COLNUM.put("CR", NOWI++);
        COLNUM.put("CS", NOWI++);
        COLNUM.put("CT", NOWI++);
        COLNUM.put("CU", NOWI++);
        COLNUM.put("CV", NOWI++);
        COLNUM.put("CW", NOWI++);
        COLNUM.put("CX", NOWI++);
        COLNUM.put("CY", NOWI++);
        COLNUM.put("CZ", NOWI++);

        COLNUM.put("DA", NOWI++);
        COLNUM.put("DB", NOWI++);
        COLNUM.put("DC", NOWI++);
        COLNUM.put("DD", NOWI++);
        COLNUM.put("DE", NOWI++);
        COLNUM.put("DF", NOWI++);
        COLNUM.put("DG", NOWI++);
        COLNUM.put("DH", NOWI++);
        COLNUM.put("DI", NOWI++);
        COLNUM.put("DJ", NOWI++);
        COLNUM.put("DK", NOWI++);
        COLNUM.put("DL", NOWI++);
        COLNUM.put("DM", NOWI++);
        COLNUM.put("DN", NOWI++);
        COLNUM.put("DO", NOWI++);
        COLNUM.put("DP", NOWI++);
        COLNUM.put("DQ", NOWI++);
        COLNUM.put("DR", NOWI++);
        COLNUM.put("DS", NOWI++);
        COLNUM.put("DT", NOWI++);
        COLNUM.put("DU", NOWI++);
        COLNUM.put("DV", NOWI++);
        COLNUM.put("DW", NOWI++);
        COLNUM.put("DX", NOWI++);
        COLNUM.put("DY", NOWI++);
        COLNUM.put("DZ", NOWI++);

        COLNUM.put("EA", NOWI++);
        COLNUM.put("EB", NOWI++);
        COLNUM.put("EC", NOWI++);
        COLNUM.put("ED", NOWI++);
        COLNUM.put("EE", NOWI++);
        COLNUM.put("EF", NOWI++);
        COLNUM.put("EG", NOWI++);
        COLNUM.put("EH", NOWI++);
        COLNUM.put("EI", NOWI++);
        COLNUM.put("EJ", NOWI++);
        COLNUM.put("EK", NOWI++);
        COLNUM.put("EL", NOWI++);
        COLNUM.put("EM", NOWI++);
        COLNUM.put("EN", NOWI++);
        COLNUM.put("EO", NOWI++);
        COLNUM.put("EP", NOWI++);
        COLNUM.put("EQ", NOWI++);
        COLNUM.put("ER", NOWI++);
        COLNUM.put("ES", NOWI++);
        COLNUM.put("ET", NOWI++);
        COLNUM.put("EU", NOWI++);
        COLNUM.put("EV", NOWI++);
        COLNUM.put("EW", NOWI++);
        COLNUM.put("EX", NOWI++);
        COLNUM.put("EY", NOWI++);
        COLNUM.put("EZ", NOWI++);


        COLNUM.put("FA", NOWI++);
        COLNUM.put("FB", NOWI++);
        COLNUM.put("FC", NOWI++);
        COLNUM.put("FD", NOWI++);
        COLNUM.put("FE", NOWI++);
        COLNUM.put("FF", NOWI++);
        COLNUM.put("FG", NOWI++);
        COLNUM.put("FH", NOWI++);
        COLNUM.put("FI", NOWI++);
        COLNUM.put("FJ", NOWI++);
        COLNUM.put("FK", NOWI++);
        COLNUM.put("FL", NOWI++);
        COLNUM.put("FM", NOWI++);
        COLNUM.put("FN", NOWI++);
        COLNUM.put("FO", NOWI++);
        COLNUM.put("FP", NOWI++);
        COLNUM.put("FQ", NOWI++);
        COLNUM.put("FR", NOWI++);
        COLNUM.put("FS", NOWI++);
        COLNUM.put("FT", NOWI++);
        COLNUM.put("FU", NOWI++);
        COLNUM.put("FV", NOWI++);
        COLNUM.put("FW", NOWI++);
        COLNUM.put("FX", NOWI++);
        COLNUM.put("FY", NOWI++);
        COLNUM.put("FZ", NOWI++);

        COLNUM.put("GA", NOWI++);
        COLNUM.put("GB", NOWI++);
        COLNUM.put("GC", NOWI++);
        COLNUM.put("GD", NOWI++);
        COLNUM.put("GE", NOWI++);
        COLNUM.put("GF", NOWI++);
        COLNUM.put("GG", NOWI++);
        COLNUM.put("GH", NOWI++);
        COLNUM.put("GI", NOWI++);
        COLNUM.put("GJ", NOWI++);
        COLNUM.put("GK", NOWI++);
        COLNUM.put("GL", NOWI++);
        COLNUM.put("GM", NOWI++);
        COLNUM.put("GN", NOWI++);
        COLNUM.put("GO", NOWI++);
        COLNUM.put("GP", NOWI++);
        COLNUM.put("GQ", NOWI++);
        COLNUM.put("GR", NOWI++);
        COLNUM.put("GS", NOWI++);
        COLNUM.put("GT", NOWI++);
        COLNUM.put("GU", NOWI++);
        COLNUM.put("GV", NOWI++);
        COLNUM.put("GW", NOWI++);
        COLNUM.put("GX", NOWI++);
        COLNUM.put("GY", NOWI++);
        COLNUM.put("GZ", NOWI++);

        COLNUM.put("HA", NOWI++);
        COLNUM.put("HB", NOWI++);
        COLNUM.put("HC", NOWI++);
        COLNUM.put("HD", NOWI++);
        COLNUM.put("HE", NOWI++);
        COLNUM.put("HF", NOWI++);
        COLNUM.put("HG", NOWI++);
        COLNUM.put("HH", NOWI++);
        COLNUM.put("HI", NOWI++);
        COLNUM.put("HJ", NOWI++);
        COLNUM.put("HK", NOWI++);
        COLNUM.put("HL", NOWI++);
        COLNUM.put("HM", NOWI++);
        COLNUM.put("HN", NOWI++);
        COLNUM.put("HO", NOWI++);
        COLNUM.put("HP", NOWI++);
        COLNUM.put("HQ", NOWI++);
        COLNUM.put("HR", NOWI++);
        COLNUM.put("HS", NOWI++);
        COLNUM.put("HT", NOWI++);
        COLNUM.put("HU", NOWI++);
        COLNUM.put("HV", NOWI++);
        COLNUM.put("HW", NOWI++);
        COLNUM.put("HX", NOWI++);
        COLNUM.put("HY", NOWI++);
        COLNUM.put("HZ", NOWI++);

        COLNUM.put("IA", NOWI++);
        COLNUM.put("IB", NOWI++);
        COLNUM.put("IC", NOWI++);
        COLNUM.put("ID", NOWI++);
        COLNUM.put("IE", NOWI++);
        COLNUM.put("IF", NOWI++);
        COLNUM.put("IG", NOWI++);
        COLNUM.put("IH", NOWI++);
        COLNUM.put("II", NOWI++);
        COLNUM.put("IJ", NOWI++);
        COLNUM.put("IK", NOWI++);
        COLNUM.put("IL", NOWI++);
        COLNUM.put("IM", NOWI++);
        COLNUM.put("IN", NOWI++);
        COLNUM.put("IO", NOWI++);
        COLNUM.put("IP", NOWI++);
        COLNUM.put("IQ", NOWI++);
        COLNUM.put("IR", NOWI++);
        COLNUM.put("IS", NOWI++);
        COLNUM.put("IT", NOWI++);
        COLNUM.put("IU", NOWI++);
        COLNUM.put("IV", NOWI++);
        COLNUM.put("IW", NOWI++);
        COLNUM.put("IX", NOWI++);
        COLNUM.put("IY", NOWI++);
        COLNUM.put("IZ", NOWI++);


        COLNUM.put("JA", NOWI++);
        COLNUM.put("JB", NOWI++);
        COLNUM.put("JC", NOWI++);
        COLNUM.put("JD", NOWI++);
        COLNUM.put("JE", NOWI++);
        COLNUM.put("JF", NOWI++);
        COLNUM.put("JG", NOWI++);
        COLNUM.put("JH", NOWI++);
        COLNUM.put("JI", NOWI++);
        COLNUM.put("JJ", NOWI++);
        COLNUM.put("JK", NOWI++);
        COLNUM.put("JL", NOWI++);
        COLNUM.put("JM", NOWI++);
        COLNUM.put("JN", NOWI++);
        COLNUM.put("JO", NOWI++);
        COLNUM.put("JP", NOWI++);
        COLNUM.put("JQ", NOWI++);
        COLNUM.put("JR", NOWI++);
        COLNUM.put("JS", NOWI++);
        COLNUM.put("JT", NOWI++);
        COLNUM.put("JU", NOWI++);
        COLNUM.put("JV", NOWI++);
        COLNUM.put("JW", NOWI++);
        COLNUM.put("JX", NOWI++);
        COLNUM.put("JY", NOWI++);
        COLNUM.put("JZ", NOWI++);

        COLNUM.put("KA", NOWI++);
        COLNUM.put("KB", NOWI++);
        COLNUM.put("KC", NOWI++);
        COLNUM.put("KD", NOWI++);
        COLNUM.put("KE", NOWI++);
        COLNUM.put("KF", NOWI++);
        COLNUM.put("KG", NOWI++);
        COLNUM.put("KH", NOWI++);
        COLNUM.put("KI", NOWI++);
        COLNUM.put("KJ", NOWI++);
        COLNUM.put("KK", NOWI++);
        COLNUM.put("KL", NOWI++);
        COLNUM.put("KM", NOWI++);
        COLNUM.put("KN", NOWI++);
        COLNUM.put("KO", NOWI++);
        COLNUM.put("KP", NOWI++);
        COLNUM.put("KQ", NOWI++);
        COLNUM.put("KR", NOWI++);
        COLNUM.put("KS", NOWI++);
        COLNUM.put("KT", NOWI++);
        COLNUM.put("KU", NOWI++);
        COLNUM.put("KV", NOWI++);
        COLNUM.put("KW", NOWI++);
        COLNUM.put("KX", NOWI++);
        COLNUM.put("KY", NOWI++);
        COLNUM.put("KZ", NOWI++);

        COLNUM.put("LA", NOWI++);
        COLNUM.put("LB", NOWI++);
        COLNUM.put("LC", NOWI++);
        COLNUM.put("LD", NOWI++);
        COLNUM.put("LE", NOWI++);
        COLNUM.put("LF", NOWI++);
        COLNUM.put("LG", NOWI++);
        COLNUM.put("LH", NOWI++);
        COLNUM.put("LI", NOWI++);
        COLNUM.put("LJ", NOWI++);
        COLNUM.put("LK", NOWI++);
        COLNUM.put("LL", NOWI++);
        COLNUM.put("LM", NOWI++);
        COLNUM.put("LN", NOWI++);
        COLNUM.put("LO", NOWI++);
        COLNUM.put("LP", NOWI++);
        COLNUM.put("LQ", NOWI++);
        COLNUM.put("LR", NOWI++);
        COLNUM.put("LS", NOWI++);
        COLNUM.put("LT", NOWI++);
        COLNUM.put("LU", NOWI++);
        COLNUM.put("LV", NOWI++);
        COLNUM.put("LW", NOWI++);
        COLNUM.put("LX", NOWI++);
        COLNUM.put("LY", NOWI++);
        COLNUM.put("LZ", NOWI++);

        COLNUM.put("MA", NOWI++);
        COLNUM.put("MB", NOWI++);
        COLNUM.put("MC", NOWI++);
        COLNUM.put("MD", NOWI++);
        COLNUM.put("ME", NOWI++);
        COLNUM.put("MF", NOWI++);
        COLNUM.put("MG", NOWI++);
        COLNUM.put("MH", NOWI++);
        COLNUM.put("MI", NOWI++);
        COLNUM.put("MJ", NOWI++);
        COLNUM.put("MK", NOWI++);
        COLNUM.put("ML", NOWI++);
        COLNUM.put("MM", NOWI++);
        COLNUM.put("MN", NOWI++);
        COLNUM.put("MO", NOWI++);
        COLNUM.put("MP", NOWI++);
        COLNUM.put("MQ", NOWI++);
        COLNUM.put("MR", NOWI++);
        COLNUM.put("MS", NOWI++);
        COLNUM.put("MT", NOWI++);
        COLNUM.put("MU", NOWI++);
        COLNUM.put("MV", NOWI++);
        COLNUM.put("MW", NOWI++);
        COLNUM.put("MX", NOWI++);
        COLNUM.put("MY", NOWI++);
        COLNUM.put("MZ", NOWI++);

        COLNUM.put("NA", NOWI++);
        COLNUM.put("NB", NOWI++);
        COLNUM.put("NC", NOWI++);
        COLNUM.put("ND", NOWI++);
        COLNUM.put("NE", NOWI++);
        COLNUM.put("NF", NOWI++);
        COLNUM.put("NG", NOWI++);
        COLNUM.put("NH", NOWI++);
        COLNUM.put("NI", NOWI++);
        COLNUM.put("NJ", NOWI++);
        COLNUM.put("NK", NOWI++);
        COLNUM.put("NL", NOWI++);
        COLNUM.put("NM", NOWI++);
        COLNUM.put("NN", NOWI++);
        COLNUM.put("NO", NOWI++);
        COLNUM.put("NP", NOWI++);
        COLNUM.put("NQ", NOWI++);
        COLNUM.put("NR", NOWI++);
        COLNUM.put("NS", NOWI++);
        COLNUM.put("NT", NOWI++);
        COLNUM.put("NU", NOWI++);
        COLNUM.put("NV", NOWI++);
        COLNUM.put("NW", NOWI++);
        COLNUM.put("NX", NOWI++);
        COLNUM.put("NY", NOWI++);
        COLNUM.put("NZ", NOWI++);

    }

    private static char[] CELLCHAR = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static int getColNum(String celName) {
        Integer i = COLNUM.get(StringUtils.nvl(celName).toUpperCase());
        if (i == null) return 0;
        return i;
    }

    public static String getCellName(int row, int cell) {
        try {
            return CELLCHAR[cell] + "" + (row + 1);
        } catch (Throwable tx) {
            return "";
        }
    }

}

package org.cmms.modules.report.util;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 * User: Erakor
 * Date: 12-6-6
 */
public class LangUtil extends ReplaceText {

    public static final String LINE = "\r\n";

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equalsIgnoreCase(str);
    }

    public static String trimToEmpty(String str) {
        if (str == null) return "";
        return str.trim();
    }

    public static int length(String str) {
        if (str == null) return 0;
        return str.getBytes().length;
    }

    public static HashMap<String, String> parseArgs(String[] args) {
        HashMap<String, String> argsM = new HashMap<String, String>() {
            public String get(Object key) {
                String v = super.get(key);
                return coverNull(v);
            }
        };
        String tmpName = "", tmpValue = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                if (!tmpName.equals("")) {
                    argsM.put(tmpName, tmpValue);
                    tmpValue = "";
                }
                tmpName = args[i].substring(1);
            } else {
                tmpValue += args[i];
            }
        }
        if (!tmpName.equals("") && !"".equals(tmpValue)) {
            argsM.put(tmpName, tmpValue);
        }
        return argsM;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }
//
//    /**
//     * 将字符串数据去空格
//     *
//     * @param s 待处理字符串数组
//     * @return 所有无素去空格的结果数组
//     */
//    public static String[] coverNullNoTrim(String[] s) {
//        for (int i = 0; i < s.length; i++) {
//            s[i] = coverNullNoTrim(s[i]);
//        }
//        return s;
//    }
//
//    /**
//     * 字符串空值检查，去字符串空格
//     *
//     * @param s 待去空格字符串
//     * @return 去空格的字符串
//     */
//    public static String coverNullNoTrim(String s) {
//        if (s == null) return "";
//        return s;
//    }


//    /**
//     * 字符串空值检查，去字符串空格
//     *
//     * @param s 待去空格字符串
//     * @return 去空格的字符串
//     */
//    public static String coverNull(String s) {
//        if (s == null) return "";
//        return s.trim();
//    }

    public static String coverNull(String s1, String s2) {
        s1 = coverNull(s1);
        if (!isEmpty(s1))
            return s1;
        return coverNull(s2);
    }

    public static String coverNullObj(Object obj) {
        if (obj == null) return "";
        return String.valueOf(obj);
    }

    public static String deleteString(String src, String str) {
        src = coverNull(src);
        src = src.replaceAll(str, "");
        return src;
    }

    private static String _deleteString(String src, String str) {
        try {
            src = src.replaceAll(str, "");
        } catch (Throwable t) {
            return src;
        }
        return src;
    }

    public static String deleteEnterStr(String src) {
        src = coverNull(src);
        src = _deleteString(src, "\r\n");
        src = _deleteString(src, "\r");
        src = _deleteString(src, "\n");
        return src
                ;
    }

    private static boolean compare(char[] c1, char[] c2, int bof) {
        if (c1.length - bof < c2.length) return false;
        for (int j = 0; j < c2.length; j++) {
            if (bof + j >= c1.length) return false;//
            if (c1[bof + j] != c2[j]) return false;
        }
        return true;
    }

    public static String deleteString1(String src, String delStr, boolean upcase) {
        if (delStr == null || "".equals(delStr)) return src;
        if (src == null || "".equals(src)) return src;
        char[] srcchars = src.toCharArray();
        char[] delchars = delStr.toCharArray();
        if (srcchars.length < delchars.length) return src;
        if (srcchars.length == delStr.length()) {
            if (upcase) {
                if (src.equals(delStr)) return "";
            } else {
                if (src.equalsIgnoreCase(delStr)) return "";
            }
        }
        char[] newchars = new char[srcchars.length];
        int srcpos = 0, lpos = 0, rpos = 0;
        for (; lpos < srcchars.length; ) {
            if (compare(srcchars, delchars, lpos)) {
                //匹配上
                System.arraycopy(srcchars, srcpos, newchars, rpos, lpos - srcpos);
                rpos += lpos - srcpos;
                lpos += delchars.length;
                srcpos = lpos;
            } else {
                lpos++;
            }
        }
        if (lpos > srcpos) {
            System.arraycopy(srcchars, srcpos, newchars, rpos, lpos - srcpos);
            rpos += lpos - srcpos;
        }
       String  nsrc = new String(newchars, 0, rpos);
        return nsrc;
    }

    public static boolean compare(String a, String b) {
        return LangUtil.coverNull(a).equalsIgnoreCase(LangUtil.coverNull(b));
    }

//    /**
//     * 合并两个字符串数组内容
//     *
//     * @param src  数组1
//     * @param dest 数组2
//     * @return 数组1和数组2的拼合内容数组
//     */
//    public static String[] connectStrings(String[] src, String[] dest) {
//        if (src == null) src = new String[]{};
//        if (dest == null) dest = new String[]{};
//        String[] strings = new String[src.length + dest.length];
//        System.arraycopy(src, 0, strings, 0, src.length);
//        System.arraycopy(dest, 0, strings, src.length, dest.length);
//        return strings;
//    }

    public static String trimToLength(String str, int len) {
        if (len == 0) return "";
        if (len < 0) return str;
        if (str == null) return "";
        byte[] array = str.getBytes();
        if (array.length < len) return str;
        return new String(array, 0, len);
    }

//    /**
//     * 去除字符串左边的空格
//     *
//     * @param str 字符串
//     * @return 去左边空格的字符串
//     */
//    public static String trimLeft(String str) {
//        if (str == null) return null;//传入为空
//        if (str.trim().length() < 1) return "";//传入的空字符串
//        for (int i = 0; i < str.length(); i++) {//去除源字符串左边的空格
//            if (str.startsWith(" ")) str = str.substring(1);
//            else break;
//        }
//        return str;
//    }
//
//    /**
//     * 字符串数组拼为字符串
//     * 字符串中以传入的字符分隔符分隔第个字符串
//     *
//     * @param strArray  字符串数据 组
//     * @param tokenChar 分隔符,如传入的分隔符为0则直接拼接
//     * @return 按分隔符拼接的字符串
//     */
//    public static String StringArray2String(String[] strArray, char tokenChar) {
//        StringBuffer str = new StringBuffer();
//        for (int i = 0; i < strArray.length; i++) {
//            str.append(strArray[i]);
//            if (tokenChar != 0)
//                str.append(tokenChar);
//        }
//        return str.toString();
//    }

//    /**
//     * 按分隔符解析字符串
//     *
//     * @param constant 需解析的字符串
//     * @return 字符串数组(按每个分隔符)
//     */
//    public static String[] String2StringArray(String constant, char tokenChar) {
//        if (constant == null || "".equals(constant)) return new String[0];
//        char[] tmp = constant.trim().toCharArray();
//        StringBuffer tmpp = new StringBuffer();
//        String[] conS = new String[0];
//        char pix = '\\';
//        char pixc = ' ';
//        for (int i = 0; i < tmp.length; i++) {
//            char tc = tmp[i];
//            if (tc == pix) {
//                if (pixc != pix) {
//                    pixc = pix;
//                    continue;
//                }
//            }
//            if (tc == tokenChar && pixc != pix) {
//                if (tmpp == null) tmpp = new StringBuffer();
//                conS = connectStrings(conS, new String[]{tmpp.toString()});
//                tmpp = new StringBuffer();
//                continue;
//            } else {
//                pixc = ' ';
//                tmpp.append(tc);
//                if (i == tmp.length - 1) conS = connectStrings(conS, new String[]{tmpp.toString()});
//            }
//        }
//        return conS;
//    }

//    /**
//     * 截取字串
//     *
//     * @param src    源字串
//     * @param startx 截取开始点
//     * @param length 截取长度
//     * @return String 截取结果字串
//     */
//    public static String subString(String src, int startx, int length) {
//        byte[] b = src.getBytes();
//        byte[] b1 = new byte[length];
//        System.arraycopy(b, startx, b1, 0, b1.length);
//        return new String(b1);
//    }
//
//    public static String subString(String src, int startx) {
//        byte[] b = src.getBytes();
//        byte[] b1 = new byte[b.length - startx];
//        System.arraycopy(b, startx, b1, 0, b1.length);
//        return new String(b1);
//    }

    /**
     * 分隔字符串
     * 分隔符可以为长字符串
     *
     * @param srcStr 待分隔字符串
     * @param split  分隔符
     * @return 分隔后的字符串值数组
     */
    public static String[] splitString(String srcStr, String split) {
        if (srcStr == null || "".equals(srcStr)) return new String[0];
        if (split == null || "".equals(split)) return new String[]{srcStr};
        char[] srcArray = srcStr.toCharArray();
        char[] splitArray = split.toCharArray();
        StringBuffer tmpBuf = new StringBuffer();
        StringArrayBuffer sab = new StringArrayBuffer(30);
        boolean lastSplit = false;
        for (int i = 0; i < srcArray.length; i++) {
            if (compareArray(srcArray, splitArray, i)) {//符合分隔符
                i += splitArray.length - 1;
                sab.addString(tmpBuf);
                tmpBuf.setLength(0);
                lastSplit = true;
            } else {//不符合分隔符
                lastSplit = false;
                tmpBuf.append(srcArray[i]);
            }
        }
        if (tmpBuf.length() > 0 || lastSplit) {
            sab.addString(tmpBuf);
            tmpBuf.setLength(0);
        }
        return sab.getStringArray();
    }

    /**
     * 分隔字符串
     * 分隔符可以为长字符串
     *
     * @param srcStr 待分隔字符串
     * @param split  分隔符
     * @return 分隔后的字符串值数组
     */
    public static String[] splitString(String srcStr, String split, String splitEnd, boolean isSplit) {
        if (srcStr == null || "".equals(srcStr)) return new String[0];
        if (split == null || "".equals(split)) return new String[]{srcStr};
        char[] srcArray = srcStr.toCharArray();
        char[] splitArray = split.toCharArray();
        char[] splitEndArray = splitEnd.toCharArray();
        StringBuffer tmpBuf = new StringBuffer();
        StringArrayBuffer sab = new StringArrayBuffer(30);
        boolean startSplit = false;
        for (int i = 0; i < srcArray.length; i++) {
            if (startSplit) {
                if (compareArray(srcArray, splitEndArray, i)) {//符合结束分隔符
                    i += splitEndArray.length - 1;
                    if (isSplit) tmpBuf.append(splitEndArray);
                    sab.addString(tmpBuf);
                    tmpBuf.setLength(0);
                    startSplit = false;
                } else {
                    tmpBuf.append(srcArray[i]);
                }
            } else {
                if (compareArray(srcArray, splitArray, i)) {//符合开始分隔符
                    sab.addString(tmpBuf);
                    tmpBuf.setLength(0);
                    startSplit = true;
                    i += splitArray.length - 1;
                    if (isSplit) tmpBuf.append(splitArray);
                } else {
                    tmpBuf.append(srcArray[i]);
                }
            }
        }
        if (tmpBuf.length() > 0 || startSplit) {
            sab.addString(tmpBuf);
            tmpBuf.setLength(0);
        }
        return sab.getStringArray();
    }

    /**
     * 比较srcArray中当前位置开始是否与splitArray中的内容相等,
     * 相等返回true,否则返回false
     * 用于字符串分隔
     *
     * @param srcArray   源
     * @param splitArray 分隔
     * @param srcPos     源起始位置
     * @return 是否相符
     */
    public static boolean compareArray(char[] srcArray, char[] splitArray, int srcPos) {
        if (srcArray.length < splitArray.length + srcPos) return false;
        for (int i = 0; i < splitArray.length; i++) {
            if (splitArray[i] != srcArray[srcPos + i]) {
                return false;
            }
        }
        return true;
    }


    public static String numberToUp(String number) {
        Number2Up up = new Number2Up();
        String s = up.cleanZero(up.splitNum(up.roundString(number)));
        return s;
    }

    /**
     * 删除字符串中的某个字符
     * 2008-05-08  优化算法
     *
     * @param src 源字符串
     * @param c   需删除的字符
     * @return 目标结果字符串
     */
    public static String delChar(String src, char c) {
        if (src == null || src.equalsIgnoreCase("") ||
                src.length() < 1) return src;
        if (src.indexOf(c) == -1) return src;
        char[] v = src.toCharArray();
        char[] n = new char[v.length];
        int npix = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] != c) {
                n[npix] = v[i];
                npix++;
            }
        }
        return new String(n, 0, npix);
//        StringBuffer des = new StringBuffer();
//        StringBuffer sb = new StringBuffer(src);
//        for(int i = 0; i < sb.length(); i ++){
//            if(sb.charAt(i) != c) des.append(sb.charAt(i));
//        }
//        return des.toString();
    }


    /**
     * 截取字串
     *
     * @param src    源字串
     * @param startx 截取开始点
     * @param length 截取长度
     * @return String 截取结果字串
     */
    public static String subString(String src, int startx, int length) {
        byte[] b = src.getBytes();
        if (b.length <= length) return src;
        byte[] b1 = new byte[length];
        System.arraycopy(b, startx, b1, 0, b1.length);
        return new String(b1);
    }

    /**
     * 截取字串
     *
     * @param src    源字串
     * @param startx 截取开始点
     * @return String 截取结果字串
     */
    public static String subString(String src, int startx) {
        byte[] b = src.getBytes();
        if (startx > b.length) return "";
        byte[] b1 = new byte[b.length - startx];
        System.arraycopy(b, startx, b1, 0, b1.length);
        return new String(b1);
    }

    /**
     * 计算字符长度
     *
     * @param str 字符串
     * @return int 字符串长度
     */
    public static int getLength(String str) {
        return str.getBytes().length;
    }

    public static byte[] gbk2utf8(String chenese) {
        char c[] = chenese.toCharArray();
        byte[] fullByte = new byte[3 * c.length];
        for (int i = 0; i < c.length; i++) {
            int m = (int) c[i];
            String word = Integer.toBinaryString(m);
//         System.out.println(word);

            StringBuffer sb = new StringBuffer();
            int len = 16 - word.length();
            //补零
            for (int j = 0; j < len; j++) {
                sb.append("0");
            }
            sb.append(word);
            sb.insert(0, "1110");
            sb.insert(8, "10");
            sb.insert(16, "10");

//         System.out.println(sb.toString());

            String s1 = sb.substring(0, 8);
            String s2 = sb.substring(8, 16);
            String s3 = sb.substring(16);

            byte b0 = Integer.valueOf(s1, 2).byteValue();
            byte b1 = Integer.valueOf(s2, 2).byteValue();
            byte b2 = Integer.valueOf(s3, 2).byteValue();
            byte[] bf = new byte[3];
            bf[0] = b0;
            fullByte[i * 3] = bf[0];
            bf[1] = b1;
            fullByte[i * 3 + 1] = bf[1];
            bf[2] = b2;
            fullByte[i * 3 + 2] = bf[2];

        }
        return fullByte;
    }

    public static String coverStringCode(String str, String srcCharSet, String desCharSet) {
        try {
            if (str == null || str.equals(""))
                return "";
            String gbk = null;
            gbk = new String(str.getBytes(srcCharSet), desCharSet);
            return gbk;
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 将unicode码ISO8859_1字符集转换为中文字符集
     *
     * @param unicode unicode ISO8859_1字符集字符串
     * @return String GBK码字符串
     */
    public static String UnicodeToChinese(String unicode) {
        try {
            if (unicode == null || unicode.equals(""))
                return "";
            String gbk = null;
            gbk = new String(unicode.getBytes("ISO8859_1"), "GBK");
            return gbk;
        } catch (UnsupportedEncodingException e) {
            return unicode;
        }
    }


    /**
     * 将中文GBK字符集转换为Unicode码ISO8859_1字符集.
     *
     * @param s 中文GBK码字符串
     * @return ISO8859_1字符集字符串
     */
    public static String ChineseToUnicode(String s) {
        try {
            if (s == null || s.equals(""))
                return "";
            String newstring = null;
            newstring = new String(s.getBytes("GBK"), "ISO8859_1");
            return newstring;
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }


    /**
     * 将双精度型数值转换为不带小数点的字符串
     *
     * @param d 双精度型数值
     * @return String 不带小数点的字符串数据
     */
    public static String double2string(double d) {
        double d1 = Math.round(d * 100);
        DecimalFormat nf = new DecimalFormat();
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(0);
        return nf.format(d1);
    }

    /**
     * 将双精度型对象转换成带指定小数点的字符串
     *
     * @param d 双精度型对象
     * @return 带指定小数点的字符串(传入为null时, 返回null)
     */
    public static String Double2String2(Double d) {
        if (d == null) return null;
        String ds = double2string(d.doubleValue());
        if (ds == null) return null;
        if (ds.equalsIgnoreCase("")) return "";
        if (ds.length() == 0) return "";
        if (ds.getBytes().length == 0) return "";
        String ps = "";
        if (ds.startsWith("-")) {
            ps = "-";
            ds = ds.substring(1);
        }
        if (ds.getBytes().length == 1) {
            return ps + "0.0" + ds;
        }
        if (ds.getBytes().length == 2) {
            return ps + "0." + ds;
        }
        ds = ps + subString(ds, 0, ds.getBytes().length - 2) + "." + subString(ds, ds.getBytes().length - 2);
        return ds;
    }

    /**
     * 计算整型数组所有元素值合
     *
     * @param i 整型数组
     * @return 所有元素合
     */
    public static int calcLen(int[] i) {
        int len = 0;
        for (int j = 0; j < i.length; j++) {
            len = len + i[j];
        }
        return len;
    }

    /**
     * 将字符串数据去空格
     *
     * @param s 待处理字符串数组
     * @return 所有无素去空格的结果数组
     */
    public static String[] coverNull(String[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = coverNull(s[i]);
        }
        return s;
    }

    /**
     * 将字符串数据去空格
     *
     * @param s 待处理字符串数组
     * @return 所有无素去空格的结果数组
     */
    public static String[] coverNullNoTrim(String[] s) {
        for (int i = 0; i < s.length; i++) {
            s[i] = coverNullNoTrim(s[i]);
        }
        return s;
    }

    /**
     * 字符串空值检查，去字符串空格
     *
     * @param s 待去空格字符串
     * @return 去空格的字符串
     */
    public static String coverNullNoTrim(String s) {
        if (s == null) return "";
        return s;
    }

    /**
     * 字符串空值检查，去字符串空格
     *
     * @param s 待去空格字符串
     * @return 去空格的字符串
     */
    public static String coverNull(String s) {
        if (s == null) return "";
        return s.trim();
    }

    /**
     * 填充空格
     *
     * @param content 源字节数组
     * @param length  需填充长度
     * @return 指定长度的目标数组
     */
    public static byte[] fillSpace(byte[] content, int length) {
        byte white = 32;
        return fill(content, length, white);
    }

    /**
     * 填充数组内容
     *
     * @param content 源字节数组
     * @param length  需填充的长度
     * @param fillStr 填充字符
     * @return 够长的字数组
     */
    public static byte[] fill(byte[] content, int length, byte fillStr) {
        if (content.length < length) {
            byte space[] = new byte[length];
            Arrays.fill(space, fillStr);
            for (int i = 0; i < content.length; i++) {
                space[i] = content[i];
            }
            return space;
        } else {
            return content;
        }
    }

    /**
     * 合并两个字节数组内容
     *
     * @param src  数组1
     * @param dest 数组2
     * @return 数组1和数组2的拼合内容数组
     */
    public static byte[] connectBytes(byte[] src, byte[] dest) {
        if (src == null) src = new byte[]{};
        if (dest == null) dest = new byte[]{};
        byte[] bytes = new byte[src.length + dest.length];
        System.arraycopy(src, 0, bytes, 0, src.length);
        System.arraycopy(dest, 0, bytes, src.length, dest.length);
        return bytes;
    }

    /**
     * 合并两个字符串数组内容
     *
     * @param src  数组1
     * @param dest 数组2
     * @return 数组1和数组2的拼合内容数组
     * @since ISTP-2.0.0.1
     */
    public static String[] connectStrings(String[] src, String[] dest) {
        if (src == null) src = new String[]{};
        if (dest == null) dest = new String[]{};
        String[] strings = new String[src.length + dest.length];
        System.arraycopy(src, 0, strings, 0, src.length);
        System.arraycopy(dest, 0, strings, src.length, dest.length);
        return strings;
    }

    /**
     * 将一个int转换成byte[4]
     *
     * @param value 待转换的整型数值
     * @return byte[] 转换字节数组结果
     */
    public static byte[] intToByte4(int value) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (value & 0x000000ff);
        bytes[2] = (byte) ((value >> 8) & 0x000000ff);
        bytes[1] = (byte) ((value >> 16) & 0x000000ff);
        bytes[0] = (byte) ((value >> 24) & 0x000000ff);
        return bytes;
    }

    /**
     * 将一个long转换成byte[4]
     *
     * @param value 待转换的长整型数值
     * @return byte[] 转换字节数组结果
     */
    public static byte[] longToByte4(long value) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (value & 0x000000ff);
        bytes[2] = (byte) ((value >> 8) & 0x000000ff);
        bytes[1] = (byte) ((value >> 16) & 0x000000ff);
        bytes[0] = (byte) ((value >> 24) & 0x000000ff);
        return bytes;
    }

    /**
     * 将字符串转换成整型数值
     *
     * @param str 字符
     * @return 转换结果数值
     */
    public static int str2int(String str) {
        str = coverNull(str);
        return Integer.parseInt(str);
    }

    /**
     * 将字节数组转换成整型数组
     *
     * @param b 字节数组
     * @return 整型数组
     */
    public static int[] byteArray2intArray(byte[] b) {
        int[] ia = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            ia[i] = b[i];
        }
        return ia;
    }

    /**
     * 将整型数组转换成字节数组
     *
     * @param i 整型数组
     * @return 字节数组
     */
    public static byte[] intArray2byteArray(int[] i) {
        byte[] b = new byte[i.length];
        for (int j = 0; j < b.length; j++) {
            b[j] = (byte) i[j];
        }
        return b;
    }


    /**
     * 替换字符串中的字符
     *
     * @param str 字符串
     * @param src 需替换的源字符
     * @param des 目的字符
     * @return
     */
    public static String replaceAll(String str, char src, char des) {
        if (str == null || str.equalsIgnoreCase("") ||
                str.length() < 1) return str;
        if (str.indexOf(src) == -1) return str;
        StringBuffer sb = new StringBuffer(str);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == src) sb.setCharAt(i, des);
        }
        return sb.toString();
    }

    /**
     * 将Double型转成字符串
     *
     * @param d Double型
     * @return 字符串
     */
    public static String Double2String(Double d) {
        if (d == null) return null;
        if (d.intValue() == d.doubleValue())
            return String.valueOf(d.intValue());
        return String.valueOf(d);
    }

    /**
     * 将字符串转成Double
     *
     * @param s 字符串
     * @return Double型
     */
    public static Double String2Double(String s) {
        if (s == null) return null;
        return Double.valueOf(s);
    }

    /**
     * 将字符串转成Integer
     *
     * @param s 字符串
     * @return Integer型
     */
    public static Integer String2Integer(String s) {
        if (s == null) return null;
        return Integer.valueOf(s);
    }

    /**
     * 将Integer型转成字符串
     *
     * @param i Integer型
     * @return 字符串
     */
    public static String Integer2String(Integer i) {
        if (i == null) return null;
        return String.valueOf(i);
    }

    /**
     * 取字符串中的某个值
     * key = value
     *
     * @param src 源字符串
     * @param key 取值的键名
     * @return 值
     */
    public static String getValue(String src, String key) {
        return getValue(src, key, true, '"');
    }

    /**
     * 取字符串中的某个值
     * key = value
     *
     * @param src     源字符串
     * @param key     取值的键名
     * @param isForce 是否强制匹配 key 字符串,即源字符串是否必须是以key开头的,为false时,只要源字符串中有key,都进行处理
     * @return 值
     */
    public static String getValue(String src, String key, boolean isForce) {
        return getValue(src, key, isForce, '"');
    }

    /**
     * 取字符串中的某个值
     * key = value
     *
     * @param src     源字符串
     * @param key     取值的键名
     * @param isForce 是否强制匹配 key 字符串,即源字符串是否必须是以key开头的,为false时,只要源字符串中有key,都进行处理
     * @param flag    截取value的分隔符,为空(' ' 32 )时,返回 = 号后的所有字符
     * @return 值
     */
    public static String getValue(String src, String key, boolean isForce, char flag) {
        //如果传入的参数中的任一一个为空,则返回空
        if (src == null || key == null) return null;
        if (src.equalsIgnoreCase("") || key.equalsIgnoreCase("")) return null;
        //检查源字符串:
        if (src.indexOf(key) < 0) return null;//源字符串中是否存在KEY字符串
        if (src.indexOf("=") < 1) return null;//源字符串中必须有"="号
        src = trimLeft(src);
        key = trimLeft(key);
        if (src.equalsIgnoreCase("") || key.equalsIgnoreCase("")) return null;
        if (isForce)
            if (src.indexOf(key) != 0) return null;
        String tmp = src.substring(src.indexOf(key));
        tmp = tmp.substring(key.length());
        tmp = trimLeft(tmp);
        if (tmp.equalsIgnoreCase("")) return null;
        if (!tmp.startsWith("=")) return null;
        tmp = tmp.substring(tmp.indexOf("=") + 1);
        if (flag != ' ') {
            tmp = tmp.substring(tmp.indexOf(flag) + 1);
            tmp = tmp.substring(0, tmp.indexOf(flag));
        }
        return tmp;
    }

    /**
     * 去除字符串左边的空格
     *
     * @param str 字符串
     * @return 去左边空格的字符串
     */
    public static String trimLeft(String str) {
        if (str == null) return null;//传入为空
        if (str.trim().length() < 1) return "";//传入的空字符串
        for (int i = 0; i < str.length(); i++) {//去除源字符串左边的空格
            if (str.startsWith(" ")) str = str.substring(1);
            else break;
        }
        return str;
    }

    /**
     * 将字符串转换成字节数组
     * 特殊处理,传入的字符串必须带管道符号,区分每个字节
     *
     * @param s 字符串
     * @return 字节数组
     * @deprecated
     */
    public static byte[] strings2bytes(String s) {
        if (s == null) return null;
        if (s.getBytes().length < 1) return new byte[0];
        String[] isHexs = String2StringArray(s, '|');
        byte[] isHexsb = new byte[isHexs.length];
        for (int i = 0; i < isHexsb.length; i++) {
            isHexsb[i] = (byte) Integer.parseInt(isHexs[i]);
        }
        return isHexsb;
    }

    /**
     * 将字节数组转换成字符串
     * 特殊处理,生成的字符串列管道符号,区分每个字节
     *
     * @param b 字节数组
     * @return 字符串
     * @deprecated
     */
    public static String bytes2Strings(byte[] b) {
        if (b == null) return null;
        if (b.length < 1) return "";
        String isHex = "";
        for (int i = 0; i < b.length; i++) {
            isHex += b[i];
            isHex += "|";
        }
        return isHex;
    }

    /**
     * 按分隔符解析字符串
     *
     * @param constant 需解析的字符串
     * @return 字符串数组(按每个分隔符)
     * @deprecated 该方法转为 #String2StringArray(String constant,char tokenChar)
     */
    public static String[] parseProtocol(String constant, char tokenChar) {
        return String2StringArray(constant, tokenChar);
    }

    /**
     * 按分隔符解析字符串
     *
     * @param constant 需解析的字符串
     * @return 字符串数组(按每个分隔符)
     * @since ISTP-2.0.0.1
     * @deprecated 该方法转为 #String2StringArray(String constant,char tokenChar)
     */
    public static String[] parseStrings(String constant, char tokenChar) {
        return String2StringArray(constant, tokenChar);
    }

    /**
     * 按分隔符解析字符串
     *
     * @param constant 需解析的字符串
     * @return 字符串数组(按每个分隔符)
     * @since ISTP-2.0.0.1
     */
    public static String[] String2StringArray(String constant, char tokenChar) {
        //R||3|1|100|12345|54321|||||||||||||||||||||
        char[] tmp = constant.trim().toCharArray();
        StringBuffer tmpp = new StringBuffer();
        String[] conS = new String[0];
        char pix = '\\';
        char pixc = ' ';
        for (int i = 0; i < tmp.length; i++) {
            char tc = tmp[i];
            if (tc == pix) {
                if (pixc != pix) {
                    pixc = pix;
                    continue;
                }
            }
//            if(tc==tokenChar){
//                if(pixc==pix){
//
//                }
//            }
            if (tc == tokenChar && pixc != pix) {
                if (tmpp == null) tmpp = new StringBuffer();
                conS = connectStrings(conS, new String[]{tmpp.toString()});
                tmpp = new StringBuffer();
                continue;
            } else {
                pixc = ' ';
                tmpp.append(tc);
                if (i == tmp.length - 1) conS = connectStrings(conS, new String[]{tmpp.toString()});
            }
        }
        return conS;
    }

    /**
     * 字符串数组拼为字符串
     * 字符串中以传入的字符分隔符分隔第个字符串
     *
     * @param strArray  字符串数据 组
     * @param tokenChar 分隔符,如传入的分隔符为0则直接拼接
     * @return 按分隔符拼接的字符串
     */
    public static String StringArray2String(String[] strArray, char tokenChar) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < strArray.length; i++) {
            str.append(strArray[i]);
            if (tokenChar != 0)
                str.append(tokenChar);
        }
        return str.toString();
    }

    /**
     * 截取字符串数组中的某几个元素
     *
     * @param srcArray 源字符串数组
     * @param src      截取索引位起
     * @param len      截取元素个数
     * @return 截取到的字符串数组
     */
    public static String[] substringArray(String[] srcArray, int src, int len) {
        if (srcArray == null) return null;
        if (srcArray.length < src) return null;
        if (srcArray.length < (src + len)) return null;
        if (srcArray.length == src) return new String[0];
        String[] rs = new String[0];
        for (int i = src, j = 0; j < len; i++, j++) {
            rs = connectStrings(rs, new String[]{srcArray[i]});
        }
        return rs;
    }


    /**
     * 截取字符串数组中的某几个元素
     *
     * @param srcArray 源字符串数组
     * @param src      截取索引位起
     * @return 截取到的字符串数组
     */
    public static String[] substringArray(String[] srcArray, int src) {
        if (srcArray == null) return null;
        if (srcArray.length < src) return null;
        if (srcArray.length == src) return new String[0];
        String[] rs = new String[0];
        for (int i = src, j = 0; j < (srcArray.length - src); i++, j++) {
            rs = connectStrings(rs, new String[]{srcArray[i]});
        }
        return rs;
    }

    /**
     * 计算整型数组元素值合长度
     *
     * @param ia 整型数组
     * @return 数据元素值的值合
     */
    public static long cellIntArrayLength(int[] ia) {
        if (ia == null) return 0l;
        if (ia.length < 1) return 0l;
        long len = 0l;
        for (int i = 0; i < ia.length; i++) {
            len += ia[i];
        }
        return len;
    }

    /**
     * 将Double型转换成字符中
     *
     * @param val     值
     * @param decmail 保留小数位
     * @return 转换后值
     */
    public static String double2String(Double val, int decmail) {
        if (val == null) val = new Double(0);
        return double2String(val.doubleValue(), decmail);
    }

    /**
     * 将Double型转换成字符中
     *
     * @param val     值
     * @param decmail 保留小数位
     * @return 转换后值
     */
    public static String double2String(double val, int decmail) {
        double pix = Math.pow(10, decmail);
        double b = (double) Math.round(val * pix);
//        System.out.println("##"+b);
        double c = b / pix;
//        System.out.println("##"+c);
        String dformat = "###################0";
        if (decmail > 0) {
            dformat += ".";
            for (int i = 0; i < decmail; i++) {
                dformat += "0";
            }
        }
        DecimalFormat df = new DecimalFormat(dformat);
        return df.format(c);
    }

    /**
     * 将DOUBLE 值保留decmail位小数
     *
     * @param val
     * @param decmail
     * @return
     */
    public static double doubleFormatDecmail(double val, int decmail) {
        return Math.round(val * (Math.pow(10.0, decmail))) / Math.pow(10.0, decmail);
    }


    public static boolean exists(String fullStr, String word) {
        if (fullStr == null || word == null)
            return false;
        fullStr = fullStr.toUpperCase();
        word = word.toUpperCase();
        return fullStr.indexOf(word) > -1;
    }

    public static boolean exists(String fullStr, Vector word) {
        boolean flag = false;
        if (word != null && !word.isEmpty()) {
            for (int j = 0; j < word.size(); j++) {
                String s1 = (String) word.get(j);
                flag = flag || exists(fullStr, s1);
            }

        }
        return flag;
    }

    public static boolean endWith(String filename, Vector key) {
        boolean b = false;
        if (key != null && !key.isEmpty()) {
            for (int j = 0; j < key.size(); j++) {
                String s1 = (String) key.get(j);
                boolean b1 = filename.endsWith(s1);
                b = b || b1;
            }

        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(10.0, 2.0));
        System.out.println(doubleFormatDecmail(456.987654, 2));
    }

}

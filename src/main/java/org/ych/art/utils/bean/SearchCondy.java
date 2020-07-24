package org.ych.art.utils.bean;

import org.ych.art.utils.utils.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询条件，
 * 输入串如 1~10 或~BC 或 6~ 或 3，RR，9t等等，取左右值，或散列值
 */
public class SearchCondy implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 默认连接符
     */
    private static final String DFT_LINK = "~";

    /**
     * 默认分隔符
     */
    private static final String DFT_TOKEN = ",";

    /**
     * 查询串
     */
    private String searchStr;

    /**
     * 单值
     */
    private String single;

    /**
     * 是否范围查询，即有~这个符号
     */
    private boolean isRang = false;

    /**
     * 左值，即 从~
     */
    private String left;

    /**
     * 右值，即到~
     */
    private String right;

    /**
     * 离散值
     */
    private List<String> dispersed;

    public SearchCondy(String searchStr) {
        this.searchStr = searchStr;
        parse(DFT_LINK, DFT_TOKEN);
    }

    public SearchCondy(String searchStr, String link, String token) {
        this.searchStr = searchStr;
        parse(link, token);
    }

    private void parse(String link, String token) {
        // 查看是否有连接符
        int linkPos = this.searchStr.indexOf(link);
        if (linkPos != -1) {
            this.isRang = true;
            String lv = this.searchStr.substring(0, linkPos);
            if (Tools.notEmpty(lv)) {
                this.left = lv;
            }

            String rv = this.searchStr.substring(linkPos + 1);
            if (Tools.notEmpty(rv)) {
                this.right = rv;
            }

            return;
        }

        int tokenPos = this.searchStr.indexOf(token);
        if (tokenPos != -1) {
            String tv[] = this.searchStr.split(token);
            this.dispersed = new ArrayList<String>(tv.length);
            for (String str : tv) {
                this.dispersed.add(str);
            }
            return;
        }

        this.single = this.searchStr;

    }

    public String getSearchStr() {
        return searchStr;
    }

    public String getSingle() {
        return single;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public List<String> getDispersed() {
        return dispersed;
    }

    public boolean isRang() {
        return isRang;
    }

}

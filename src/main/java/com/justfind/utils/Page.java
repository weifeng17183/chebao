package com.justfind.utils;

/**
 * 列表分页显示控制类
 * 
 */
public class Page {
	 // 记录总数
    private int counts = -1;

    // 每页显示记录数
    private int perPageSize = 20;

    // 总页数
    private int totalPage = 1;

    // 当前页
    private int currentPage = 0;

    // 页面显示开始记录数
    private int firstResult = 1;

    // 页面显示最后记录数
    private int lastResult = 1;

    /*
     * 排序名称
     */
    private String orderName;

    /*
     * 排序类型
     */
    private String orderType;

    private String orderBy;

    public Page(int counts, int perPageSize) {
        // 计算所有的页面数
        this.counts = counts;
        // this.totalPage = (int)Math.ceil((this.counts + this.perPageSize - 1)
        // / this.perPageSize);
        if (counts % perPageSize == 0) {
            this.totalPage = this.counts / this.perPageSize;
        } else {
            this.totalPage = this.counts / this.perPageSize + 1;
        }
    }

    public Page() {
    }

    /**
     * @return the counts
     */
    public int getCounts() {
        return counts;
    }

    /**
     * @param counts
     *            the counts to set
     */
    public void setCounts(int counts, int perPageSize) {
        this.perPageSize = perPageSize;
        // 计算所有的页面数
        this.counts = counts;
        // this.totalPage = (int)Math.ceil((this.counts + this.perPageSize - 1)
        // / this.perPageSize);
        if (counts % this.perPageSize == 0) {
            this.totalPage = this.counts / this.perPageSize;
        } else {
            this.totalPage = this.counts / this.perPageSize + 1;
        }
    }

    /**
     * @return the perPageSize
     */
    public int getPerPageSize() {
        return perPageSize;
    }

    /**
     * @param perPageSize
     *            the perPageSize to set
     */
    public void setPerPageSize(int perPageSize) {
        this.perPageSize = perPageSize;
    }

    /**
     * @return the totalPage
     */
    public int getTotalPage() {
        if (totalPage < 1) {
            return 1;
        }
        return totalPage;
    }

    /**
     * @param totalPage
     *            the totalPage to set
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the firstResult
     */
    public int getFirstResult() {
        int temp = this.currentPage - 1;
        if (temp <= 0) {
            return 0;
        }
        return this.firstResult = (this.currentPage - 1) * this.perPageSize;
    }

    /**
     * @param firstResult
     *            the firstResult to set
     */
    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    /**
     * @return the lastResult
     */
    public int getLastResult() {
        this.lastResult = this.firstResult + this.perPageSize;
        return lastResult;
    }

    /**
     * @param lastResult
     *            the lastResult to set
     */
    public void setLastResult(int lastResult) {
        this.lastResult = lastResult;
    }

    /**
     * @return the orderName
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName
     *            the orderName to set
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return the orderBy
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderBy
     *            the orderBy to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        if (this.getOrderName() == null || this.getOrderName().equals("")
                || this.getOrderType() == null
                || this.getOrderType().equals("")) {
            return null;
        }
        orderBy = "order by " + this.getOrderName() + " " + this.getOrderType();
        return orderBy;
    }

    /**
     * @param orderBy
     *            the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("currentPage:").append(this.currentPage).append(" counts:")
                .append(this.counts).append(" totalPage:")
                .append(this.totalPage);
        return sb.toString();
    }
}

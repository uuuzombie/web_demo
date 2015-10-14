package com.sky.demo.common_web.base;

import java.util.List;
import java.util.Map;

/**
 * Created by rg on 2015/6/11.
 */
public class Pager<T> {

    private List<T> rows;           //对象记录结果集

    private int pageNo = 1;         // 当前页
    private int pageSize = 10;      // 每页显示记录数
    private int totalRecords = 0;   // 总记录数
    private int pageCount = 1;      // 总页数

    private Map<String, Object> filterCondition;

    private boolean isFirstPage = false;        //是否为第一页
    private boolean isLastPage = false;         //是否为最后一页
    private boolean hasPreviousPage = false;    //是否有前一页
    private boolean hasNextPage = false;        //是否有下一页

    public Pager(int total, int pageNumber) {
        init(total, pageNumber, pageSize);
    }

    public Pager(int totalRecords, int pageNo, int pageSize) {
        init(totalRecords, pageNo, pageSize);
    }

    private void init(int totalRecords, int pageNo, int pageSize) {
        //设置基本参数
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.pageCount = (this.totalRecords - 1) / this.pageSize + 1;

        //根据输入可能错误的当前号码进行自动纠正
        if (pageNo < 1) {
            this.pageNo = 1;
        } else if (pageNo > this.pageCount) {
            this.pageNo = this.pageCount;
        } else {
            this.pageNo = pageNo;
        }

        //页面边界的判定
        judgePageBoundary();
    }

    private void judgePageBoundary() {
        isFirstPage = (pageNo == 1);
        isLastPage = (pageNo == pageCount);
        hasPreviousPage = (pageNo > 1);
        hasNextPage = (pageNo < pageCount);
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Map<String, Object> getFilterCondition() {
        return filterCondition;
    }

    public void setFilterCondition(Map<String, Object> filterCondition) {
        this.filterCondition = filterCondition;
    }



    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public int getPageCount() {
        return pageCount;
    }


    public boolean isFirstPage() {
        return isFirstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append("totalRecords=").append(totalRecords)
                .append(",pageCount=").append(pageCount)
                .append(",pageNo=").append(pageNo)
                .append(",pageSize=").append(pageSize)
                .append(",isFirstPage=").append(isFirstPage)
                .append(",isLastPage=").append(isLastPage)
                .append(",hasPreviousPage=").append(hasPreviousPage)
                .append(",hasNextPage=").append(hasNextPage);

        sb.append(",rows.size=").append(rows.size());
        sb.append("]");
        return sb.toString();
    }

}

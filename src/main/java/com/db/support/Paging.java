package com.db.support;

import java.util.List;
import java.util.Map;

/**
 * @author yangyang.zhang
 * @version 2.0
 * @created Mar 4, 2013 5:00:06 PM
 */
public class Paging<T> {
	private List<T> list = null;
	private Map<Object, T> map = null;
	private int pageIndex = 0; // 当前页数
	private int pageSize = 0; // 页面分页记录数
	private int pageCount = 0;//页面总数
	private int listSize = 0; // 记录数
	private int recordCount = 0;// 当前页记录数
	private String pageUrl; // 分页数字的上链接地址
	private List<Integer> paging;// 显示在页面上的分页数字,随着pageIndex改变
	private int pagingLeft;
	private int pagingRight;
	private double sum1 = 0.0;
	private double sum2 = 0.0;

	
	/**
	 * @desc  (当前页,页大小,记录总数)
	 * @param pageIndex
	 * @param pageSize
	 * @param recordCount
	 */
	public Paging(int pageIndex,int pageSize, int recordCount) {

		this.pageIndex = pageIndex;
		if(this.pageIndex<=0)this.pageIndex=1;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		changePageIndex();
	}
	/**
	 *  @desc  (当前页默认1,页大小,记录总数)
	 * @param pageSize
	 * @param recordCount
	 */
	public Paging(int pageSize, int recordCount) {
		this.pageIndex = 1;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		changePageIndex();
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex= pageIndex;
		changePageIndex();
	}


	

	/**
	 * @desc    返回页总数
	 * @author  shuhui.wen
	 * @version 2.0
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
		
	}

	/**
	 * @desc    返回当前页大小
	 * @author  shuhui.wen
	 * @version 2.0
	 * @return
	 */
	public int getListSize() {
		return listSize;
	}
	
	public List<Integer> getPaging() {
		return paging;
	}

	

	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
		this.listSize = list.size();
	}
	
	public Map<Object, T> getMap() {
		return map;
	}

	public void setMap(Map<Object, T> map) {
		this.map = map;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	
	
	
	public int getPageSize() {
		return pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	private void changePageIndex(){
		if (recordCount == 0){
			pageCount= 0;
			return;
		}
		if(recordCount%pageSize==0){
			pageCount= recordCount / pageSize;
		}else{
			pageCount= recordCount / pageSize;
			pageCount++;
		}
//		if(pageIndex>pageCount)this.pageIndex=pageCount;
	}

	public int getPagingLeft() {
		return pagingLeft;
	}

	public int getPagingRight() {
		return pagingRight;
	}
	public double getSum1() {
		return sum1;
	}
	public void setSum1(double sum1) {
		this.sum1 = sum1;
	}
	public double getSum2() {
		return sum2;
	}
	public void setSum2(double sum2) {
		this.sum2 = sum2;
	}
}

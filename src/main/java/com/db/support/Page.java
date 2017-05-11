package com.db.support;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author yangyang.zhang
 *
 * @param <T>
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -5316074769264320785L;
	/**
	 * 
	 */
	private List<T> list;				// list result of this page
	private int pageNumber;				// page number
	private int pageSize;				// result amount of this page
	private int totalPage;				// total page
	private int totalRow;				// total row
	public Page(List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow) {
		super();
		this.list = list;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalRow = totalRow;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

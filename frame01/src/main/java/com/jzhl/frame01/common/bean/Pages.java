package com.jzhl.frame01.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 * @author 陈龙
 *
 * @param <T>
 */
public class Pages<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * pages内的当前页数据
	 */
	private List<T> list = null;
	
	/**
	 * 当前页，默认为第一页
	 */
	private int nowPage = 0;
	
	/**
	 * 当前页, 显示的行数
	 */
	private int pageSize = 2;
	
	/**
	 * 总页数
	 */
	private int sumPage = 0;
	
	/**
	 * 总行数
	 */
	private int total = 0;
	
	/**
	 * limit分页开始下标
	 */
	private Integer startLimit;
	
	private Pages<T> page;
	
	/**
	 * 获取limit分页开始时的下标
	 * @return
	 */
	public Integer getStartLimit() {

		nowPage = (nowPage < 1) ? 1 : nowPage;
		int tempNowPage = nowPage - 1;
		
		return tempNowPage * pageSize;
	}
	
	/**
	 * 设置总数据行数 自动计算页数等信息
	 * @param sumRow
	 */
	public void setSumRow(int sumRow) {
		this.total = sumRow;

		if(sumRow % pageSize == 0){
			this.sumPage = sumRow / pageSize;
		}else{
			this.sumPage = (sumRow / pageSize) + 1;
		}
		//对于当前页大于总页数显示错误的控制
		if(this.nowPage >= this.sumPage){
			this.nowPage = this.sumPage;
		}else if(this.nowPage <= 0){
			this.nowPage = 0;
		}
	}
	
	public void setStartLimit(Integer startLimit) {
		this.startLimit = startLimit;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSumPage() {
		return sumPage;
	}

	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}

	public int getTotal() {
		return total;
	}

	public Pages<T> getPage() {
		return page;
	}

	public void setPage(Pages<T> page) {
		this.page = page;
	}


	@Override
	public String toString() {
		return "Pages{" +
				"list=" + list +
				", nowPage=" + nowPage +
				", pageSize=" + pageSize +
				", sumPage=" + sumPage +
				", total=" + total +
				", startLimit=" + startLimit +
				", page=" + page +
				'}';
	}
}

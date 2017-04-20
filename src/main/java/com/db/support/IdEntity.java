package com.db.support;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * <p> Description:  </p>
 *
 * @Author yangyang.zhang
 * @Version 1.0
 * @Date 15/4/22
 */
@MappedSuperclass
public class IdEntity implements Serializable{
    private static final long serialVersionUID = 1L;
	public static int STATUS_CANCEL = 3;//已取消
    public static int STATUS_INIT = 2;//处理中
	public static int STATUS_SUCC = 1;//成功
	public static int STATUS_FAIL = 0;//失败
	public static int STATUS_NONE = 9;//未知
	
	public static int PHASE_NONE = 9;//未知
	public static int PHASE_INIT= 0;//处理中
	public static int PHASE_RECORDED= 1;//已记账
	public static int PHASE_CHECKED = 2;//已对账
	public static int PHASE_COMFIRM= 8;//确认成功

	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(length=20)
    protected Long id;
    @Version
    private Integer optimistic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Integer getOptimistic() {
		return optimistic;
	}

	public void setOptimistic(Integer optimistic) {
		this.optimistic = optimistic;
	}

	
    
}

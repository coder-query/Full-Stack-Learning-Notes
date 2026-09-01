package com.mdk.springsecurity6study.common.entity.dept;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 *
 * @TableName mdk_umc_dept
 */
@TableName(value = "mdk_umc_dept")
@Data
public class MdkUmcDept implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 社会统一信用码
     */
    @TableField(value = "company_code")
    private String companyCode;

    /**
     * 企业联系人
     */
    @TableField(value = "company_contacts")
    private String companyContacts;

    /**
     * 联系电话
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 绑定ic卡（多个）
     */
    @TableField(value = "ic_card")
    private String icCard;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 父级名称
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 企业描述
     */
    @TableField(value = "company_info")
    private String companyInfo;

    /**
     * 门店编号
     */
    @TableField(value = "store_code")
    private String storeCode;

    /**
     * 部门名称名称
     */
    @TableField(value = "dept_name")
    private String deptName;

    /**
     * 父部门id
     */
    @TableField(value = "father_dept_id")
    private Integer fatherDeptId;

    /**
     * 排序
     */
    @TableField(value = "order_num")
    private Integer orderNum;

    /**
     * 祖籍列表
     */
    @TableField(value = "ancestors")
    private String ancestors;

    /**
     * 状态（0正常1锁定）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    private Integer delFlag;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
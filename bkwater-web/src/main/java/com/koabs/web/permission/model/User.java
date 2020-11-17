package com.koabs.web.permission.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 三一用户信息
 * </p>
 *
 * @author bk
 * @since 2020-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sany_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
      @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 登陆账号
     */
    private String accountNumber;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 账号类型0：普通账号 1：管理员账号
     */
    private Integer userType;

    /**
     * 账号状态0：启用 1：禁用 2：注销
     */
    private Integer userStatus;

    private LocalDateTime updateDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}

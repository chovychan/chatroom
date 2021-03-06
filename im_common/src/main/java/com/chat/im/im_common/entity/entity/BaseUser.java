package com.chat.im.im_common.entity.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chat.im.im_common.entity.base.BaseUuEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author: chovychan in 2022/5/8
 */

@ApiModel("用户实体")
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "im_user", resultMap = "userResultMap")
public class BaseUser extends BaseUuEntity { // 主键雪花算法
    @ApiModelProperty("openId")
    @TableField("openid")
    private String openId;

    @ApiModelProperty("姓名")
    @TableField(value = "`name`")
    private String name;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("昵称")
    private String nickName;
}

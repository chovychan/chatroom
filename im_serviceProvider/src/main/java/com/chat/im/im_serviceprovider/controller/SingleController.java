package com.chat.im.im_serviceprovider.controller;

import com.chat.im.im_common.utils.SystemMsgJsonResponse;
import com.chat.im.im_serviceprovider.component.AuthenticationInfo;
import com.chat.im.im_serviceprovider.dto.single.CreateGroupDTO;
import com.chat.im.im_serviceprovider.dto.single.SelectGroupDTO;
import com.chat.im.im_serviceprovider.dto.single.SelectUserDTO;
import com.chat.im.im_serviceprovider.service.SingleService;
import com.chat.im.im_serviceprovider.vo.account.UserInfoVO;
import com.chat.im.im_serviceprovider.vo.message.GetMessageVO;
import com.chat.im.im_serviceprovider.vo.single.GetGroupVO;
import com.chat.im.im_serviceprovider.vo.single.SelectGroupVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author: chovychan in 2022/5/11
 */
@Api(tags = "api - 个人模块")
@RequestMapping("/chat/single")
@RestController
public class SingleController {
    private final AuthenticationInfo auth;
    private final SingleService singleService;

    public SingleController(AuthenticationInfo auth, SingleService singleService) {
        this.auth = auth;
        this.singleService = singleService;
    }

    @GetMapping("/page")
    @ApiOperation(value = "[个人模块] - 分页查询聊天记录", httpMethod = "GET", response = GetMessageVO.class)
    public SystemMsgJsonResponse get(@RequestParam(value = "page", required = false, defaultValue = "1L") Long page,
                                     @RequestParam(value = "size", required = false, defaultValue = "10L") Long size,
                                     @RequestParam(value = "uid") String uid) {
        return SystemMsgJsonResponse.success(singleService.page(page, size, auth.getId(), uid));
    }


    @PostMapping("/select")
    @ApiOperation(value = "[个人模块] - 查询用户", httpMethod = "POST", response = UserInfoVO.class)
    public SystemMsgJsonResponse selectUser(@Valid @RequestBody SelectUserDTO dto) {
        return SystemMsgJsonResponse.success(singleService.selectUser(dto));
    }

    @PostMapping("/friend")
    @ApiOperation(value = "[个人模块] - 添加好友", httpMethod = "POST")
    public SystemMsgJsonResponse friend(@RequestParam("id") @NotNull(message = "目标用户id不能为空") String id) {

        return SystemMsgJsonResponse.success();
    }

    @PostMapping("/shield")
    @ApiOperation(value = "[个人模块] - 屏蔽指定的用户", httpMethod = "POST")
    public SystemMsgJsonResponse shield(@RequestParam("uid") String uid) {
        singleService.shield(auth.getId(), uid);
        return SystemMsgJsonResponse.success();
    }

    @PostMapping("/remove")
    @ApiOperation(value = "[个人模块] - 删除指定聊天记录", httpMethod = "POST")
    public SystemMsgJsonResponse remove(@RequestParam("id") Long id) {
        singleService.remove(id);
        return SystemMsgJsonResponse.success();
    }

    @PostMapping("/select-group")
    @ApiOperation(value = "[个人模块] - 查询指定群组", httpMethod = "POST", response = SelectGroupVO.class)
    public SystemMsgJsonResponse selectGroup(@Valid @RequestBody SelectGroupDTO dto) {
        return SystemMsgJsonResponse.success(singleService.selectGroup(dto));
    }

    @PostMapping("/join")
    @ApiOperation(value = "[个人模块] - 加入指定群聊", httpMethod = "POST")
    public SystemMsgJsonResponse join(@RequestParam("id") @NotNull Long id) {
        singleService.join(auth.getId(), id);
        return SystemMsgJsonResponse.success();
    }

    @GetMapping("/get-group")
    @ApiOperation(value = "[个人模块] - 分页查询加入的群聊", httpMethod = "GET", response = GetGroupVO.class)
    public SystemMsgJsonResponse getGroup() {
        return SystemMsgJsonResponse.success(singleService.getGroup(auth.getId()));
    }

    @PostMapping("/create-group")
    @ApiOperation(value = "[个人模块] - 创建/修改群聊", httpMethod = "POST")
    public SystemMsgJsonResponse createGroup(@RequestBody @Valid CreateGroupDTO dto) {
        singleService.createGroup(auth.getId(), dto);
        return SystemMsgJsonResponse.success();
    }

    @PostMapping("/del-group")
    @ApiOperation(value = "[个人模块] - 删除群聊", httpMethod = "POST")
    public SystemMsgJsonResponse delGroup(@RequestParam("id") Long id) {
        singleService.delGroup(id);
        return SystemMsgJsonResponse.success();
    }
}

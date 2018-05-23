/*
 * 作者：xuda
 * 创建时间：18-5-22 上午11:41
 * 模块名称：admin
 */

/*
 * 作者：xuda
 * 创建时间：18-5-22 上午10:03
 * 模块名称：admin
 */

package com.fyerp.admin.utils.convert;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.UserDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class User2UserDTOConverter {

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static List<UserDTO> convert(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            UserDTO convert = convert(user);
            userDTOS.add(convert);
        }
        return userDTOS;
    }
}

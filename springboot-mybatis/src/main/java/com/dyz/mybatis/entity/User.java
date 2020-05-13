package com.dyz.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dyz
 * @version 1.0
 * @date 2020/5/12 10:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
private  Integer  id;
private  String  name;
private  String password;
private  String  phone;
private Date  createTime;
}

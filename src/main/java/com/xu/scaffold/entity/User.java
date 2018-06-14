package com.xu.scaffold.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class User {

    @Id
    public String id;

    public String name;

    public String password;
}

package com.michaels.designhub.config;

import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

/**
 * @Author Baojian Hong
 * @Date 2022/9/7 15:27
 * @Version 1.0
 */
public class PostgreSQL10JsonDialect extends PostgreSQL10Dialect {
    public PostgreSQL10JsonDialect() {
        super();
        this.registerHibernateType(
                Types.OTHER, JsonNodeBinaryType.class.getName()
        );
    }
}

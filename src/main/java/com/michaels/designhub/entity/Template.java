package com.michaels.designhub.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name = "design_template")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "type")
    String type;

    @Column(name = "store_id")
    @JsonProperty("store_id")
    String storeId;

    @Column(name = "is_active")
    Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    Date createdAt;

    @Column(name = "url")
    String url;

    @Type(type = "jsonb")
    @Column(name = "vison", nullable = false, columnDefinition = "jsonb")
    private HashMap<String, Object> vison;

    @Type(type = "jsonb")
    @Column(name = "custom_attributes", nullable = false, columnDefinition = "jsonb")
    @JsonProperty("custom_attributes")
    private HashMap<String, String> customAttributes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAc;
}

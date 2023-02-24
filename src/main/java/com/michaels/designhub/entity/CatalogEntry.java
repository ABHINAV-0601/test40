package com.michaels.designhub.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "image_catalog")
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
public class CatalogEntry {

    @Id
    @Column(name = "id")
    private String id;

    @Type(type = "jsonb")
    @Column(name = "sizes", nullable = false, columnDefinition = "jsonb")
    private HashMap<String, HashMap<String, String>> sizes;

    private String provider;
    private String thumbnailurl;
    private Timestamp created_at;
    private Timestamp updated_at;

    private String createThumbnailUrl(String url) {
        url = url.replace("http://","https://");
        StringBuilder builder = new StringBuilder(url);
        builder.insert(url.indexOf("upload/") + "upload/".length(), "c_thumb,h_150/");
        System.out.println(builder.toString());
        return builder.toString();
    }

    public void addSize(String key, String size, String url) {
        HashMap<String, String> borderDict = sizes.get(key);
        borderDict.put(size, url);
    }

    public CatalogEntry() {
        super();
    }

    public CatalogEntry(Map<String, Object> resp, String provider) {
        this.thumbnailurl = createThumbnailUrl((String) resp.get("url"));
        this.provider = provider;
        this.sizes = new HashMap<String, HashMap<String, String>>();
        this.sizes.put("A", new HashMap<String,String>());
        this.sizes.put("B", new HashMap<String, String>());
    }
}


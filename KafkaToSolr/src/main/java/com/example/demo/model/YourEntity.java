package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "coll")
public class YourEntity {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "title", type = "string")
    private String title;

    @Indexed(name = "content", type = "string")
    private String content;

    public YourEntity() {
    }

    // Конструктор с параметрами (нужен для удобства создания объектов)
    public YourEntity(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    
    // Геттеры и сеттеры для полей

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

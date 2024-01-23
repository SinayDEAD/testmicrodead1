package com.example.demo.service;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@Service
public class KafkaSolrService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaSolrService.class);

    @Value("${solr.url}")
    private String solrUrl;

    public void indexJsonMessage(String json) {
        try {
            SolrClient solrClient = new HttpSolrClient.Builder(solrUrl).build();
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.add(jsonToSolrInputDocument(json));
            updateRequest.commit(solrClient, "/coll"); // Замените "your-collection-name" на имя вашей коллекции в Solr
            solrClient.close();
        } catch (IOException | SolrServerException e) {
            logger.error("Error indexing JSON message to Solr", e);
        }
    }

    private SolrInputDocument jsonToSolrInputDocument(String json) {
        SolrInputDocument solrInputDocument = new SolrInputDocument();

        try {
            JSONObject jsonObject = new JSONObject(json);

            // Пример добавления поля "id"
            if (jsonObject.has("id") && !jsonObject.isNull("id")) {
                solrInputDocument.addField("id", jsonObject.getString("id"));
            }

            // Пример добавления поля "title"
            if (jsonObject.has("title") && !jsonObject.isNull("title")) {
                solrInputDocument.addField("title", jsonObject.getString("title"));
            }

            // Пример добавления поля "content"
            if (jsonObject.has("content") && !jsonObject.isNull("content")) {
                solrInputDocument.addField("content", jsonObject.getString("content"));
            }

            // Добавьте обработку других полей

        } catch (JSONException e) {
            logger.error("Error converting JSON to SolrInputDocument", e);
        }

        return solrInputDocument;
    }
}

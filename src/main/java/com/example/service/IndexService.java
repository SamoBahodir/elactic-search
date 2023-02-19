package com.example.service;

import com.example.helper.Indices;
import com.example.helper.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexService {
    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;

    @PostConstruct
    public void tryCreateIndices() {
        final String setting = Util.loString("static/es-setting.json");
        for (final String indexName : INDICES_TO_CREATE) {
            try {
                boolean indexExists = client.exists(new GetRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    continue;
                }
                final String mappings=Util.loString("static/mappings"+indexName+".json");
                if (setting==null||mappings==null){
                    log.error("filed to create index with name'{}",indexName);
                    continue;
                }
                final CreateIndexRequest createIndexRequest=new CreateIndexRequest(indexName);
                createIndexRequest.settings(setting, XContentType.JSON);
                createIndexRequest.mapping(mappings,XContentType.JSON);
                client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
            } catch (final Exception e) {
                log.error("");
            }
        }
    }
}

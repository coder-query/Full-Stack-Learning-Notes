package org.example.elasticsearch_rest_client.index;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.example.elasticsearch_rest_client.es_constants.EsSearchConstant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class ElasticSearchIndexTest {
    RestHighLevelClient restHighLevelClient = null;

    @BeforeEach
    public void init(){
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(
                new HttpHost("192.168.211.166",9200)
        ));
    }

    // 创建索引库
    @Test
    public void createIndex() throws IOException{
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(EsSearchConstant.INDEX_NAME_USER);
        // 构建索引数据
        request.source(EsSearchConstant.INDEX_NAME_USER_SCHEME, XContentType.JSON);
        // 发送创建索引库的请求
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }


    // 删除索引库
    @Test
    public void deleteIndex() throws IOException{
        // 创建索引请求
        DeleteIndexRequest request = new DeleteIndexRequest(EsSearchConstant.INDEX_NAME_USER);
        restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    // 判断索引库是否存在
    @Test
    public void existsIndex() throws IOException{
        // 创建索引请求
        GetIndexRequest request = new GetIndexRequest(EsSearchConstant.INDEX_NAME_USER);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.err.println(exists ? "索引库已存在" : "索引库不存在");
    }





    @AfterEach
    public void close(){
        if(restHighLevelClient != null){
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

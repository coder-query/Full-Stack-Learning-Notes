package org.example.elasticsearch_rest_client.es_constants;

public interface EsSearchConstant {

    // 索引库名称
    String INDEX_NAME_USER = "user";

    // 索引库的字段Scheme
    String INDEX_NAME_USER_SCHEME = "{\n" +
            "\"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"userId\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"username\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"    \n" +
            "      },\n" +
            "      \"phone\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"email\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "      \t\t\"index\": false\n" +
            "      },\n" +
            "      \"status\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"remark\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            " }\n" +
            "}\n" +
            "}";

}

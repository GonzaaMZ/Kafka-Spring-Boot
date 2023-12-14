package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic(){

        Map<String, String> configs = new HashMap<>();
        //Delete: Borra Mensaje Compact: Mantiene el mas actual
        configs.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        //Tiempo de retencion de mensajes - por defecto -1
        configs.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
        // Tamaño maximo del segmento - 1gb
        configs.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
        //Tamaño de cada mensaje - por defecto 1MB
        configs.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");

        return TopicBuilder.name("topic-spring-example")
                .partitions(2)
                .replicas(2)
                .configs(configs)
                .build();
    }

}

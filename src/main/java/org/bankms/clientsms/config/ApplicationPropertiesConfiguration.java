package org.bankms.clientsms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    private int limitDeProduits;
    private List<Endpoint> endpoints;
    @Getter
    @Setter
    public static class Endpoint {
        private String url;
        private List<String> rt;
        private boolean enabled;
    }
}
package com.shiny.identity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.Config;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class WebhookEventListenerProvider implements EventListenerProvider {
    private final String webhookUrl;
    private final EventType[] events = {
        EventType.UPDATE_EMAIL,
        EventType.UPDATE_PROFILE
    };

    public WebhookEventListenerProvider() {
        String gatewayUrl = System.getenv("GATEWAY_URL");
        this.webhookUrl = gatewayUrl + "/webhook/identity";
    }

    @Override
    public void onEvent(Event event) {
        for (EventType eventType : events) {
            if (eventType.equals(event.getType())) {
                sendEvent(event);
                break;
            }
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        // Not implemented
    }

    private void sendEvent(Event event) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhookUrl);
            httpPost.setEntity(new StringEntity(createJson(event)));
            setHeaders(httpPost);
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createJson(Event event) {
        return String.format(
            "{\"type\":\"%s\",\"userId\":\"%s\",\"details\":\"%s\"}",
            event.getType().name(),
            event.getUserId(),
            event.getDetails()
        );
    }

    private void setHeaders(HttpPost httpPost) {
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
    }

    @Override
    public void close() {
        // Not implemented
    }

    public static class Factory implements EventListenerProviderFactory {
        @Override
        public EventListenerProvider create(KeycloakSession keycloakSession) {
            return new WebhookEventListenerProvider();
        }

        @Override
        public void init(Config.Scope scope) {
            // Not implemented
        }

        @Override
        public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
            // Not implemented
        }

        @Override
        public void close() {
            // Not implemented
        }

        @Override
        public String getId() {
            return "webhook-event-listener";
        }
    }
}
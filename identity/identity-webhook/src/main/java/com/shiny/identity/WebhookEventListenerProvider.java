package com.shiny.identity;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

public class WebhookEventListenerProvider implements EventListenerProvider {
    private final String webhookUrl = "http://localhost:8080/webhook";

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.LOGIN) {
            sendEvent(event);
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) { /* TODO document why this method is empty */ }

    private void sendEvent(Event event) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(webhookUrl);
            String json = String.format("{\"type\":\"%s\",\"userId\":\"%s\"}", event.getType().name(), event.getUserId());
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            client.execute(httpPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {}
}
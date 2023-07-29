package com.hidden.godfather.customer.service;

import com.hidden.godfather.customer.model.webHookTeams.Facts;
import com.hidden.godfather.customer.model.webHookTeams.Sections;
import com.hidden.godfather.customer.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeamsWebHookService {

    public TeamsWebHookService() {
        this.url = "https://fiapcom.webhook.office.com/webhookb2/fb2a5d75-bf78-4540-a8e2-8da96f0161ba@11dbbfe2-89b8-4549-be10-cec364e59551/IncomingWebhook/1e2c63bf01f64513b74fc0a8d344027b/89df9d8f-a5f1-4e6c-9480-f3501041abaf";
    }

    private static final Logger LOG = LoggerFactory.getLogger(TeamsWebHookService.class);
    // Define in your application.yml your personal Webhook link

    private String url;

    public void createWebHook(@NotNull String activityTitle,  String activitySubtitle, @NotNull String status) {
        List<Sections> sectionsList = new ArrayList<>();
        Sections sections = new Sections();
        sections.activityTitle = activityTitle;
        sections.activitySubtitle = activitySubtitle;
        List<Facts> factsList = new ArrayList<>();

        factsList.add(new Facts("Aplicação Owner", "Padrinho Secreto"));
        factsList.add(new Facts("Status", status));
        factsList.add(new Facts("Data do processamento", DateUtil.localDateTimeDefaultZone().toString()));

        sections.facts = factsList;
        sections.markdown = true;
        sectionsList.add(sections);
        sendWebHook(sectionsList, sections.activityTitle);
    }

    public void sendWebHook(List<Sections> sectionsList, String  summary) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> map = new HashMap<>();
        map.put("@type", "MessageCard");
        map.put("@context", "http://schema.org/extensions");
        map.put("themeColor", "0DE000");
        map.put("summary", summary);
        map.put("sections", sectionsList);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        LOG.debug("← sendWebHook summary={}, response={}", summary, response.getStatusCode());
    }
}

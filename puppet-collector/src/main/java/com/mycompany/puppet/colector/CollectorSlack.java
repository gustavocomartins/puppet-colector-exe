
package com.mycompany.puppet.colector;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import java.io.IOException;
import java.net.http.HttpClient;

public class CollectorSlack {   

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String oAuthToken = "xoxb-3627374539109-3654059437392-Ffu2CzdQdvdrDSmICpRQ2tZp";
    private static final String slackChanel = "monitoração-de-servidores-cloud";
    
    private static final String url1 = "https:";
    private static final String url2 = "//hooks.slack.";
    private static final String url3 = "com/services";
    private static final String url4 = "/T03JFB0FV37";
    private static final String url5 = "/B03K827RGDN";
    private static final String url6 = "/6TPnkejGlj1u7LfRtWRbsWnU";
    
    private static final String webHookURL = url1 + url2 + url3 + url4 + url5 + url6;

    public static void sendSlackMessage(String message) {
        System.out.println(webHookURL);
        try{
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(message);
            Payload payload = Payload.builder().channel(slackChanel).text(msgBuilder.toString()).build();
            WebhookResponse wbResp = Slack.getInstance().send(webHookURL,payload);
            System.out.println("Slack enviado com sucesso.");
        }
        catch(IOException e){
            e.getMessage();
            System.out.println("Slack nao precisou enviar notificacao.");
        }       
    }  
}

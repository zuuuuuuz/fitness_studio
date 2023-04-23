package by.it_academy.fitness.service;

import by.it_academy.fitness.core.audit.Audit;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import by.it_academy.fitness.service.api.IAuditService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuditService implements IAuditService {

    private static final String URL_AUDIT_SERVICE = "http://audit-service:8080/audit/send";
    @Override
    public void send(Audit audit) {
        if(audit == null){
            throw new SingleErrorResponse("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору");
        }
        sendToAudit(audit);
    }

    private void sendToAudit (Audit audit){

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL_AUDIT_SERVICE);

        JSONObject jsonObject = new JSONObject();
        if(audit.getUser() != null){
            jsonObject.put("uuid", audit.getUser().getUuid());
            jsonObject.put("mail", audit.getUser().getMail());
            jsonObject.put("fio", audit.getUser().getFio());
            jsonObject.put("role", audit.getUser().getRole());
        }
        jsonObject.put("text", audit.getText());
        jsonObject.put("type", audit.getType());
        jsonObject.put("id", audit.getId());
        httppost.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));
        httppost.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = response.getEntity();
    }
}

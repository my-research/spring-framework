package com.github.dhslrl321.logging;

import com.github.dhslrl321.spring.aop.FailureLogging;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FailureLoggingService {

    private final FailureLogRepository repository;

    @Transactional
    public void log(Object[] methodArgs, FailureLogging props, Exception e) {
        String json = new Gson().toJson(methodArgs[0]);
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();
        // TODO 만약 key 가 존재하지 않으면?
        String value = object.get(props.key()).getAsString();

        FailureLog log = FailureLog.builder()
                .keyName(props.key())
                .keyValue(value)
                .payload(json)
                .exception(e.toString())
                .occurredAt(LocalDateTime.now())
                .build();
        repository.save(log);
    }
}

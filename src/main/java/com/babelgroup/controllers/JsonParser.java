package com.babelgroup.controllers;

import com.babelgroup.dto.ClientDto;
import org.json.JSONObject;

public class JsonParser {

    public static ClientDto parseClientDto(String json){
        ClientDto clientDto = new ClientDto();
        JSONObject jsonObject = new JSONObject(json);
        clientDto.username = jsonObject.getString("username");
        clientDto.name = jsonObject.getString("name");
        clientDto.surname = jsonObject.getString("surname");

        return clientDto;
    }
}

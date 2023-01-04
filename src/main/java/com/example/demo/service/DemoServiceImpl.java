package com.example.demo.service;

import com.example.demo.transcript.*;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import static com.example.demo.config.Content.*;

@Service
public class DemoServiceImpl implements DemoService {

    @SneakyThrows
    @Override
    public String payStackValidateAccount(@NotNull ValidateAccountTranscriptRequest validateAccountTranscriptRequest) {


        ValidateAccountTranscript vATrans = ValidateAccountTranscript.builder()
                .account_number(validateAccountTranscriptRequest.account_number())
                .account_bank(validateAccountTranscriptRequest.account_bank())
                .build();


        Gson gson = new Gson();

        String account_number = validateAccountTranscriptRequest.account_number();
        String account_code = validateAccountTranscriptRequest.account_bank();

        String gsonRequest = gson.toJson(vATrans);

        HttpResponse<String> response;
        try {
            response = Unirest.get(VALIDATE_ACCOUNT_URL + "account_number="+ account_number + "&bank_code=" + account_code)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + PAYSTACK_TOKEN)
                    .header("Content-Type", "application/json")
                    .asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

        System.out.println(gsonRequest);
        System.out.println(response.getBody());

        //Using the JSON simple library parse the string into a json object
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(response.getBody());

        //Get the required object from the above created object
        JSONObject obj = (JSONObject) data_obj.get("data");

        //Get the required data using its key
        System.out.println(obj.get("account_name"));

        vATrans = gson.fromJson(String.valueOf(obj), ValidateAccountTranscript.class);
        ValidateAccountTranscript vATran = ValidateAccountTranscript.builder()
                .account_number((String) obj.get("account_number"))
                .account_name((String) obj.get("account_name"))
                .account_bank(account_code)
                .build();


        Gson gsons = new Gson();
        String gsonReq = gsons.toJson(vATran);

        System.out.println(gsonReq);
        return (gsonReq);

    }

    @Override
    public Object bankList() throws ParseException {

        HttpResponse<String> response;
        String gsonRequest = null;

        String[] code;
        String[] name;
        String[] longCode;
        try {
            response = Unirest.get(BANKS_LIST_URL)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + PAYSTACK_TOKEN)
                    .asString();


            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(response.getBody());
            JSONArray jA = (JSONArray) data_obj.get("data");

            code = new String[jA.size()];
            name = new String[jA.size()];
            longCode = new String[jA.size()];

            for (int i = 0; i < jA.size(); i++) {
                JSONObject jA1 = (JSONObject) jA.get(i);
                code[i] = (String) jA1.get("code");
                name[i] = (String) jA1.get("name");
                longCode[i] = (String) jA1.get("longcode");
            }

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
        BanksTranscript BaTran = BanksTranscript.builder()
                .bankName(name)
                .code(code)
                .longCode(longCode)
                .build();


        System.out.println(name);
        System.out.println(" ");
        System.out.println(BaTran);
        Gson gson = new Gson();
        String gsonReq = gson.toJson(BaTran);
        System.out.println(gsonReq);

        return gsonReq;

    }

    @Override
    public String bankTransfer(@NotNull BankTransferTranscriptRequest transcriptRequest) {

        BankTransferTranscript vATrans = BankTransferTranscript.builder()
                .account_bank(transcriptRequest.account_bank())
                .account_number(transcriptRequest.account_number())
                .amount(transcriptRequest.amount())
                .narration(transcriptRequest.narration())
                .currency(transcriptRequest.currency())
                .reference(transcriptRequest.reference())
                .callback_url(transcriptRequest.callback_url())
                .build();


        Gson gson = new Gson();

        String gsonRequest = gson.toJson(vATrans);

        HttpResponse<String> response;
        JSONObject data = null;
        JSONObject data_obj = null;
        JSONObject jsonObject = new JSONObject();
        try {
            response = Unirest.post(TRANSFER_FUNDS_URL)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + FLUTTER_WAVE_TOKEN)
                    .header("Content-Type", "application/json")
                    .body(vATrans)
                    .asString();

            System.out.println(gsonRequest);
            System.out.println(response.getBody());

            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            data_obj = (JSONObject) parse.parse(response.getBody());

            //Get the required object from the above created object
            data = (JSONObject) data_obj.get("data");
            String status = (String) data_obj.get("status");
            String message = (String) data_obj.get("message");

            if (data != null) {
                System.out.println(" ");
                System.out.println(status + " done");
                System.out.println(" ");
                System.out.println(message);
                System.out.println(" ");

                jsonObject.put("account_number", data.get("account_number"));
                jsonObject.put("account_name", data.get("full_name"));
                jsonObject.put("account_bank", data.get("bank_name"));
                jsonObject.put("amount", data.get("amount"));
                jsonObject.put("currency", data.get("bank_code"));
                jsonObject.put("narration", data.get("narration"));
                jsonObject.put("created_at", data.get("created_at"));
                jsonObject.put("responseMessage", data.get("message"));
                jsonObject.put("responseCode", "00");
                jsonObject.put("sessionId", "sess" + data.get("id"));
                jsonObject.put("status", status.toUpperCase());

                System.out.println(jsonObject);

            } else {
                System.out.println(" ");
                System.out.println(status);
                System.out.println(" ");
                System.out.println(message);
                System.out.println(" ");

                jsonObject.put("account_number", transcriptRequest.account_number());
                jsonObject.put("account_name", transcriptRequest.account_bank());
                jsonObject.put("account_bank", transcriptRequest.account_bank());
                jsonObject.put("amount", transcriptRequest.amount());
                jsonObject.put("currency", transcriptRequest.currency());
                jsonObject.put("narration", transcriptRequest.narration());
                jsonObject.put("responseMessage", message);
                jsonObject.put("responseCode", "99");
                jsonObject.put("sessionId", "");
                jsonObject.put("status", status.toUpperCase());

                System.out.println(jsonObject);

            }
            //beneficiary_name
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return String.valueOf((jsonObject));

    }
}

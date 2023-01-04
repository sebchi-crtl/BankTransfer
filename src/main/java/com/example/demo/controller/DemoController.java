package com.example.demo.controller;

import com.example.demo.service.DemoService;
import com.example.demo.transcript.BankTransferTranscriptRequest;
import com.example.demo.transcript.ValidateAccountTranscriptRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static com.example.demo.config.Content.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping(value = "core-banking/banks", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> payStackBanksList() throws ParseException {

        ResponseEntity responseEntity =
                new ResponseEntity<>(demoService
                        .bankList(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "core-banking/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> transactFetch() throws ParseException {

        ResponseEntity responseEntity =
                new ResponseEntity<>(demoService
                        .bankList(), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "core-banking/validateBankAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> payStackValidateAccount(@RequestBody ValidateAccountTranscriptRequest validateAccountTranscript) {

        ResponseEntity responseEntity =
                new ResponseEntity<>(demoService
                        .payStackValidateAccount(
                                validateAccountTranscript), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(value = "core-banking/bankTransfer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> flutterBankTransfer(@RequestBody BankTransferTranscriptRequest bankTransferTranscriptRequest) {

        ResponseEntity responseEntity =
                new ResponseEntity<>(demoService
                        .bankTransfer(
                                bankTransferTranscriptRequest), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("hello")
    public String hello() {
        return ("hello() = " );
    }

    @GetMapping(value = "check",  produces = MediaType.APPLICATION_JSON_VALUE)
    public String unirest_api() {
        HttpResponse<String> stringHttpResponse;
        try {
            stringHttpResponse = Unirest.get(GO_REST_URL)
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + GO_REST_TOKEN)
                    .asString();
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }


        return stringHttpResponse.getBody();
    }

    @GetMapping(value = "rest", produces = MediaType.APPLICATION_JSON_VALUE)
    public void rest() {
        try {
            URL url = new URL(ARBEIT_NOW_URL);

            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("GET");
            urlConn.connect();

            int status = urlConn.getResponseCode();

            if (status != 200)
                throw new RuntimeException("Status Code = " + status);
            else{

                StringBuilder infoString = new StringBuilder();
                Scanner scan = new Scanner(url.openStream());

                while(scan.hasNext())
                    infoString.append(scan.nextLine());

                scan.close();

                System.out.println(infoString);

                JSONParser parser = new JSONParser();
                JSONArray data = (JSONArray) parser.parse(String.valueOf(infoString));

                System.out.println(data.get(0));


            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

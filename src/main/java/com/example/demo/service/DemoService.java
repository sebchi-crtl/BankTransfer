package com.example.demo.service;

import com.example.demo.transcript.BankTransferTranscriptRequest;
import com.example.demo.transcript.ValidateAccountTranscriptRequest;
import org.jetbrains.annotations.NotNull;
import org.json.simple.parser.ParseException;

public interface DemoService {


    String payStackValidateAccount(ValidateAccountTranscriptRequest validateAccountTranscriptRequest);

    Object bankList() throws ParseException;

    String bankTransfer(@NotNull BankTransferTranscriptRequest transcriptRequest);
}

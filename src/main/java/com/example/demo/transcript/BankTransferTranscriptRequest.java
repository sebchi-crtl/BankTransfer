package com.example.demo.transcript;

public record BankTransferTranscriptRequest(
        int amount,
        String currency,
        String narration,
        String account_bank,
        String account_number,
        String account_name,
        String reference,
        String callback_url
) {
}

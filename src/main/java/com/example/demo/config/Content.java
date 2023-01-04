package com.example.demo.config;

import java.io.Serializable;

public class Content implements Serializable {
    //TOKENS
    public static final String PAYSTACK_TOKEN = "sk_test_df86e61fd0777c9df0039d715d878389e35462bb";
    public static final String FLUTTER_WAVE_TOKEN = "FLWSECK_TEST-SANDBOXDEMOKEY-X";

    //URL
    public static final String TRANSFER_FUNDS_URL = "https://api.flutterwave.com/v3/transfers";
    public static final String VALIDATE_ACCOUNT_URL = "https://api.paystack.co/bank/resolve?";
    public static final String BANKS_LIST_URL = "https://api.paystack.co/bank";
    public static final String TRANSFER_FUNDS_FETCH_URL = "https://api.flutterwave.com/v3/transfers/";


    //Testing
    public static final String GO_REST_TOKEN = "00b632f7e6ea361f4675b856cd07807f0cde098d296acb711bd9ea726ee8d215";
    public static final String GO_REST_URL = "https://gorest.co.in/public/v2/comments";
    public static final String ARBEIT_NOW_URL = "https://arbeitnow.com/api/job-board-api";

}

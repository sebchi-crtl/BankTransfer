# BankTransfer

This is the interview solution

* ## Bank List ##

url = core-banking/banks

request method = get

request body = null

* Note: I did get the data of the bank name because it wasn't placed in paystack API and multiple callback is not necessary, which can be done in client-side from a professional view.


* ## Validate Account ##

url = core-banking/validateBankAccount

request method = post

request body = {
    "account_number": "",
    "account_bank": ""
  }

* Note: null *


* ## Bank Transfer ##

url = core-banking/bankTransfer

request method = post

request body = {
    "account_bank": "044",
    "account_number": "0690000040",
    "amount": 7500,
    "narration": "Test",
    "currency": "NGN",
    "reference": "ref-2v21e2wfdfder 33",
    "callback_url": "https://www.fame.com/ng/"
  }

* Note: null *
* ## Transaction Fetch ##

url = core-banking/validateBankAccount

request method = get

request body = {
   

  }

* Note: null *




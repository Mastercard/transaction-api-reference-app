package com.mastercard.developer.response;

import org.openapitools.client.model.*;

public class MockTransactionApiResponse {

    public static final String PAN = "5661350122004029";
    public static final String AMOUNT = "100";

    public static ResponseAuthorisationResponseV02 getMockAuthorisationResponse() {
        ResponseAuthorisationResponseV02 authorisationResponseV02 = new ResponseAuthorisationResponseV02();
        MsgauthorisationresponseHeader42 hdr = new MsgauthorisationresponseHeader42();
        hdr.setMsgFctn(MsgauthorisationresponseMessageFunction16Code.ADVC);
        authorisationResponseV02.setHdr(hdr);
        ResponseAuthorisationResponse1 authorisationResponse1 = new ResponseAuthorisationResponse1();
        ResponseEnvironment2 env = new ResponseEnvironment2();
        MsgauthorisationresponseCardData4 card = new MsgauthorisationresponseCardData4();
        card.setPan(PAN);
        env.setCard(card);
        authorisationResponse1.setEnvt(env);
        authorisationResponseV02.setBody(authorisationResponse1);
        return authorisationResponseV02;
    }

    public static ResponseReversalResponseV02 getMockReversalResponse() {
        ResponseReversalResponseV02 reversalResponseV02 = new ResponseReversalResponseV02();
        MsgreversalresponseHeader42 header42 = new MsgreversalresponseHeader42();
        header42.setMsgFctn(MsgreversalresponseMessageFunction16Code.ADVC);
        ResponseReversalResponse2 reversalResponse2 = new ResponseReversalResponse2();
        ResponseTransaction86 transaction86 = new ResponseTransaction86();
        MsgreversalresponseTransactionAmounts1 txAmount = new MsgreversalresponseTransactionAmounts1();
        MsgreversalresponseTransactionAmount1 amt = new MsgreversalresponseTransactionAmount1();
        amt.setAmt(AMOUNT);
        txAmount.setTxAmt(amt);
        transaction86.setTxAmts(txAmount);
        reversalResponse2.setTx(transaction86);
        reversalResponseV02.setHdr(header42);
        reversalResponseV02.setBody(reversalResponse2);
        return reversalResponseV02;
    }

    public static ResponseInquiryResponseV01 getMockInquiryResponse() {
        ResponseInquiryResponseV01 inquiryResponse = new ResponseInquiryResponseV01();
        InquiryresponseHeader39 hdr = new InquiryresponseHeader39();
        hdr.setMsgFctn(InquiryresponseMessageFunction17Code.REQU);
        inquiryResponse.setHdr(hdr);
        ResponseInquiryResponse1 body = new ResponseInquiryResponse1();
        ResponseEnvironment15 envt = new ResponseEnvironment15();
        MsginquiryresponseCardData4 card = new MsginquiryresponseCardData4();
        card.setPan(PAN);
        envt.setCard(card);
        body.setEnvt(envt);
        inquiryResponse.setBody(body);
        return inquiryResponse;
    }
}

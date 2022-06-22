package com.mastercard.developer.response;

import org.openapitools.client.model.*;

public class MockTransactionApiResponse {

    public static final String PAN = "5661350122004029";

    public static ResponseAuthorisationResponseV02 getMockAuthrisationResponse() {
        ResponseAuthorisationResponseV02 authorisationResponseV02 = new ResponseAuthorisationResponseV02();
        MsgauthorisationresponseHeader42 hdr = new MsgauthorisationresponseHeader42();
        hdr.setMsgFctn(MsgauthorisationresponseMessageFunction16Code.ADVC);
        authorisationResponseV02.setHdr(hdr);
        ResponseAuthorisationResponse1 authorisationResponse1 = new ResponseAuthorisationResponse1();
        MsgauthorisationresponseEnvironment2 envt1 = new MsgauthorisationresponseEnvironment2();
        MsgauthorisationresponseCardData4 card1 = new MsgauthorisationresponseCardData4();
        card1.setpAN(PAN);
        envt1.setCard(card1);
        authorisationResponse1.setEnvt(envt1);
        authorisationResponseV02.setBody(authorisationResponse1);
        return authorisationResponseV02;
    }

    public static String getMockHealthResponse() {
        return "{\"status\": \"up\"}";
    }
}

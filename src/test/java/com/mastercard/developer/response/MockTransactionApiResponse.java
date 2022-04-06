package com.mastercard.developer.response;

import org.openapitools.client.model.*;

public class MockTransactionApiResponse {

  public static final String PAN = "5661350122004029";

  public static AuthorisationResponseV02 getMockAuthrisationResponse() {
      AuthorisationResponseV02 authorisationResponseV02 = new AuthorisationResponseV02();
      Header42 hdr = new Header42();
      hdr.setMsgFctn(Header42.MsgFctnEnum.ADVC);
      authorisationResponseV02.setHdr(hdr);
      AuthorisationResponse1 authorisationResponse1 = new AuthorisationResponse1();
      Environment2 envt = new Environment2();
      CardData4 card = new CardData4();
      card.setPan(PAN);
      envt.setCard(card);
      authorisationResponse1.setEnvt(envt);
      authorisationResponseV02.setBody(authorisationResponse1);
      return authorisationResponseV02;
    }
}

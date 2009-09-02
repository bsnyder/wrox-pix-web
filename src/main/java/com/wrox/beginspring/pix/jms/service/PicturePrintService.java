package com.wrox.beginspring.pix.jms.service;

import com.wrox.beginspring.pix.jms.beans.PixPicturePrintRequest;

public class PicturePrintService {

    public void receiveOrder(PixPicturePrintRequest request) {

        System.out.println("Received print request for request id "
                + request.getClient() + request.getRequestId());

    }

}

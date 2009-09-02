package com.wrox.beginspring.pix.soaphandler;

//XFire Imports
import java.io.ByteArrayOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.exchange.InMessage;
import org.codehaus.xfire.exchange.OutMessage;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.util.DOMUtils;
import org.codehaus.xfire.util.STAXUtils;
import org.codehaus.xfire.util.stax.W3CDOMStreamReader;
import org.codehaus.xfire.util.stax.W3CDOMStreamWriter;
import org.jdom.Element;
import org.w3c.dom.Document;

public class PhotoAppSoapAuthenticationHandler extends AbstractHandler {

    // Set via pix-servlet.xml
    private boolean debug = false;

    public void invoke(MessageContext messageContext) throws Exception {

        System.out.println("Executing PhotoAppSoapAuthenticationHandler");
        // Get Soap header message
        if (messageContext.getInMessage().getHeader() == null) {

            // throw new XFireFault("PixCredentials Missing.",
            // XFireFault.SENDER);

        } else {

            if (debug) {
                logSoapMessage(messageContext);
            }

            Element header = messageContext.getInMessage().getHeader();
            Element token = header.getChild("PixCredentials");

            if (token == null) {
                throw new XFireFault("Request must include PixCredentials.",
                        XFireFault.SENDER);
            }

            String username = token.getChild("userid").getText();
            String password = token.getChild("password").getText();

            // Provide Authentication
            // Dummy Implementation
            if (username.equalsIgnoreCase("invalid")) {

                throw new XFireFault("Authentication Failed", XFireFault.SENDER);
            }

        }

    }

    /*
     * This code is extracted from XFire DOMInHandler and Logger utility.
     */
    private void logSoapMessage(MessageContext context)
            throws ParserConfigurationException, XFireFault,
            TransformerException, XMLStreamException {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        dbf.setIgnoringComments(false);
        dbf.setIgnoringElementContentWhitespace(false);
        dbf.setNamespaceAware(true);
        dbf.setCoalescing(false);
        doc = STAXUtils.read(dbf.newDocumentBuilder(), context.getInMessage()
                .getXMLStreamReader(), false);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DOMUtils.writeXml(doc.getDocumentElement(), bos);
        System.out.println(bos.toString());
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}

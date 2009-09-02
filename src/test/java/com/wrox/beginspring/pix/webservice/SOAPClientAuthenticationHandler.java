package com.wrox.beginspring.pix.webservice;

import java.io.ByteArrayOutputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.exchange.OutMessage;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.handler.AbstractHandler;
import org.codehaus.xfire.util.DOMUtils;
import org.codehaus.xfire.util.stax.W3CDOMStreamWriter;
import org.jdom.Element;
import org.w3c.dom.Document;

public class SOAPClientAuthenticationHandler extends AbstractHandler {

    private String username = null;

    private String password = null;

    // Set via webservice-client.xml
    private boolean debug = false;

    public SOAPClientAuthenticationHandler() {
    }

    public SOAPClientAuthenticationHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void invoke(MessageContext context) throws Exception {
        Element el = context.getOutMessage().getOrCreateHeader();

        Element auth = new Element("PixCredentials");
        Element username_el = new Element("userid");
        username_el.addContent(username);
        Element password_el = new Element("password");
        password_el.addContent(password);
        auth.addContent(username_el);
        auth.addContent(password_el);
        el.addContent(auth);

        if (debug) {
            logSoapMessage(context);
        }

    }

    /*
     * This code is extracted from XFire DOMOutHandler and Logger utility.
     */
    private void logSoapMessage(MessageContext context)
            throws ParserConfigurationException, XFireFault,
            TransformerException {
        OutMessage message = (OutMessage) context.getOutMessage();
        W3CDOMStreamWriter writer = new W3CDOMStreamWriter();
        message.getSerializer().writeMessage(message, writer, context);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Document doc = writer.getDocument();
        DOMUtils.writeXml(doc, bos);
        System.out.println(bos.toString());
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}

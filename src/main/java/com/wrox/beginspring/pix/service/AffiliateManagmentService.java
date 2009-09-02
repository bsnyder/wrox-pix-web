package com.wrox.beginspring.pix.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.transaction.annotation.Transactional;

import com.wrox.beginspring.pix.model.Affiliate;
import com.wrox.beginspring.pix.model.PixUser;

@WebService(name = "AffiliateManagmentServiceImplPortType")
public interface AffiliateManagmentService {

    @Transactional
    @WebMethod
    public void enrollAffiliate(Affiliate affiliate);

    @Transactional
    @WebMethod
    public void enrollUserViaAffiliateWebSite(PixUser user, Affiliate affiliate);

    @Transactional(readOnly = true)
    @WebMethod
    public Affiliate getAffiliate(String affiliateUserName);

    @Transactional
    @WebMethod
    public Affiliate changePassword(String affiliateUserName,
            String oldPassword, String newPassword);

    @Transactional
    @WebMethod
    public void removeAffiliate(String affiliateUserName);

    @Transactional
    @WebMethod
    public void removeAffiliateWithUser(String affiliateUserName,
            String userName);
}

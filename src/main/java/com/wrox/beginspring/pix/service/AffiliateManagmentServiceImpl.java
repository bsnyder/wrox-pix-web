package com.wrox.beginspring.pix.service;

import javax.jws.WebService;

import com.wrox.beginspring.pix.dao.UserRepository;
import com.wrox.beginspring.pix.model.Affiliate;
import com.wrox.beginspring.pix.model.PixUser;

@WebService(endpointInterface = "com.wrox.beginspring.pix.service.AffiliateManagmentService")
public class AffiliateManagmentServiceImpl implements AffiliateManagmentService {

    private UserRepository userRepo;

    public UserRepository getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepository useRepo) {
        this.userRepo = useRepo;
    }

    public void enrollAffiliate(Affiliate affiliate) {
        getUserRepo().persistUser(affiliate);

    }

    public void enrollUserViaAffiliateWebSite(PixUser user, Affiliate affiliate) {

        // Purposely add it to demonstrate transactions rollback
        getUserRepo().persistUser(user);

        Affiliate fetchedAffiliate = (Affiliate) getUserRepo()
                .retreiveUserByUserName(affiliate.getUserName());
        if (fetchedAffiliate == null) {
            throw new RuntimeException("Affiliate not registered");

        }

    }

    public Affiliate getAffiliate(String affiliateUserName) {

        return (Affiliate) getUserRepo().retreiveUserByUserName(
                affiliateUserName);
    }

    public Affiliate changePassword(String affiliateUserName,
            String oldPassword, String newPassword) {

        Affiliate aff = (Affiliate) getUserRepo().retreiveUserByUserName(
                affiliateUserName);
        if (aff == null || !aff.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Wrong credentials supplied");
        } else {
            aff.setPassword(newPassword);
            getUserRepo().persistUser(aff);
        }
        return aff;

    }

    public void removeAffiliate(String affiliateUserName) {
        Affiliate aff = (Affiliate) getUserRepo().retreiveUserByUserName(
                affiliateUserName);
        getUserRepo().deleteUser(aff);

    }

    public void removeAffiliateWithUser(String affiliateUserName,
            String userName) {
        removeAffiliate(affiliateUserName);
        PixUser user = getUserRepo().retreiveUserByUserName(userName);
        getUserRepo().deleteUser(user);
    }

}

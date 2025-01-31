package web.app.viago.services;

import web.app.viago.dao.shuttles.ShuttleDaoImpl;
import web.app.viago.dao.subscribtions.SubscriptionDAO;
import web.app.viago.dao.subscribtions.SubscriptionDAOImpl;
import web.app.viago.model.Subscription;

import java.util.List;

public class SubscriptionService {
    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionService() {
        this.subscriptionDAO = new SubscriptionDAOImpl();
    }

    public List<Subscription> getSubscriptionsByUserId(int userId) {
        try{
            return subscriptionDAO.getSubscriptionsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Error while getting subscriptions by user id: " + e.getMessage());
        }
        return null;
    }

    public void createSubscription(Subscription subscription) {
        try {
            subscriptionDAO.createSubscribe(subscription);
        } catch (Exception e) {
            System.out.println("Error creating subscription: " + e.getMessage());
        }
    }

}

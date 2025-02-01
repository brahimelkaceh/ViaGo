package web.app.viago.services;

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
        try {
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


    public void updateSubscription(Subscription subscriptionUpdate) {
        try {
            subscriptionDAO.updateSubscriptionStatus(subscriptionUpdate);
        } catch (Exception e) {
            System.out.println("Error updating subscription: " + e.getMessage());
        }
    }

    public Subscription getSubscriptionByUserIdAndShuttleId(int userId, int shuttleId) {
        try {
            return subscriptionDAO.getSubscriptionByUserIdAndShuttleId(userId, shuttleId);
        } catch (Exception e) {
            System.out.println("Error while getting subscription by user id: " + e.getMessage());
            return null;
        }
    }
}

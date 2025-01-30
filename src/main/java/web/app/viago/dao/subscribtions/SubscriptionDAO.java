package web.app.viago.dao.subscribtions;

import web.app.viago.model.Subscription;

import java.util.List;

public interface SubscriptionDAO {
    void createSubscribe(Subscription subscribe);

    List<Subscription> getAllSubscriptions();

    Subscription getSubscriptionById(int id);

    String updateSubscriptionStatus(int id, String status);
}

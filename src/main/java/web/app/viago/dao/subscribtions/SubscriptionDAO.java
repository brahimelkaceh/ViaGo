package web.app.viago.dao.subscribtions;

import web.app.viago.model.Subscription;

import java.util.List;

public interface SubscriptionDAO {

    void createSubscribe(Subscription subscribe);

    List<Subscription> getAllSubscriptions();

    List<Subscription> getSubscriptionsByUserId(int userId);

    Subscription getSubscriptionById(int id);

    Subscription getSubscriptionByUserIdAndShuttleId(int userId, int shuttleId);

    void updateSubscriptionStatus(Subscription subscribe);
}

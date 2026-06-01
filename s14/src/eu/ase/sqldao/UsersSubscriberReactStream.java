package eu.ase.sqldao;

import eu.ase.iojson.User;

import java.util.concurrent.Flow;

public class UsersSubscriberReactStream implements Flow.Subscriber<User> {
    private SqlDAO sqlDAO;
    private Flow.Subscription subscription;

    public UsersSubscriberReactStream() {
        super();
        this.sqlDAO = SqlDAO.getInstance();
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("onSubscribe - new subscription");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(User item) {
        System.out.println("onNext - user received " + item.toString());
        sqlDAO.insertIntoDB(item.getId(), item.getName(), item.getEmail(), item.getPassword());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("error occured fetching user - " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("fetching user completed");
    }
}

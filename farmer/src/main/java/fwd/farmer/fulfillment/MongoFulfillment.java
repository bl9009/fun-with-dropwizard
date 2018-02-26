package fwd.farmer.fulfillment;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import fwd.common.main.OrderStatus;
import fwd.common.main.PotatoOrder;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class MongoFulfillment implements FarmerFulfillment {

    private MongoClient mongo;
    private DB db;
    private Jongo jongo;
    private MongoCollection coll;

    public MongoFulfillment(MongoClient mongoClient) {
        this.mongo = mongoClient;

        db = mongo.getDB("farmer");

        jongo = new Jongo(db);
        coll = jongo.getCollection("orders");
    }

    @Override
    public synchronized void receivedOrder(PotatoOrder order) {
        coll.insert(order);
    }

    @Override
    public synchronized PotatoOrder processNextOrder() throws NoOrdersToProcessException {
        PotatoOrder order = coll.findAndModify("{ status: # }", OrderStatus.RECEIVED)
                .with("{ $set: { status: # } }", OrderStatus.PROCESSING)
                .as(PotatoOrder.class);

        if (order == null) {
            throw new NoOrdersToProcessException();
        }

        return order;
    }

    @Override
    public synchronized void orderCompleted(PotatoOrder order) {
        coll.update("{ status: # }", OrderStatus.COMPLETED).with(order);
    }

    @Override
    public synchronized boolean ordersAvailable() {
        return coll.count("{ status: # }", OrderStatus.RECEIVED) > 0;
    }
}

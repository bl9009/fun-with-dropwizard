package fwd.farmer.main;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import fwd.common.main.OrderStatus;
import fwd.common.main.PotatoOrder;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

public class MongoFarmerFulfillment implements FarmerFulfillment {

    private MongoClient mongo;
    private DB db;
    private Jongo jongo;
    private MongoCollection coll;

    public MongoFarmerFulfillment(MongoClient mongoClient) {
        this.mongo = mongoClient;

        db = mongo.getDB("farmer");

        jongo = new Jongo(db);
        coll = jongo.getCollection("orders");
    }

    @Override
    public void receivedOrder(PotatoOrder order) {
        coll.insert(order);
    }

    @Override
    public PotatoOrder processNextOrder() throws NoOrdersToProcessException {
        PotatoOrder order = coll.findAndModify("{" +
                        "query: { 'status': # }," +
                        "update: { $set: { 'status': # } }," +
                        "sort: { '_id': 1 }," +
                        "new: true " +
                        "}",
                OrderStatus.RECEIVED, OrderStatus.PROCESSING)
                .as(PotatoOrder.class);

        if (order == null) {
            throw new NoOrdersToProcessException();
        }

        return order;
    }

    @Override
    public void orderCompleted(PotatoOrder order) {
        coll.update("{ status: # }", OrderStatus.COMPLETED).with(order);
    }

    @Override
    public boolean ordersAvailable() {
        return coll.count("{ status: # }", OrderStatus.RECEIVED) > 0;
    }
}

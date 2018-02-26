package fwd.farmer.main;

import fwd.common.main.*;
import fwd.farmer.dispatch.DispatchFailedException;
import fwd.farmer.dispatch.FarmerDispatch;
import fwd.farmer.fulfillment.FarmerFulfillment;
import fwd.farmer.fulfillment.NoOrdersToProcessException;
import fwd.farmer.warehouse.RedisWarehouse;
import fwd.farmer.warehouse.OutOfCapacityException;
import fwd.farmer.warehouse.OutOfStockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class Farmer implements PotatoProducer {

    private RedisWarehouse warehouse;
    private FarmerFulfillment fulfillment;
    private FarmerDispatch dispatch;

    private LinkedList<PotatoDelivery> deliveries;

    private int productionRate;

    Logger logger;

    public Farmer(RedisWarehouse warehouse,
                  FarmerFulfillment fulfillment,
                  FarmerDispatch dispatch,
                  int productionRate) {
        this.warehouse = warehouse;
        this.fulfillment = fulfillment;

        this.productionRate = productionRate;

        deliveries = new LinkedList();

        logger = LoggerFactory.getLogger(Farmer.class);
    }

    @Override
    public synchronized void addOrder(PotatoOrder order) {
        fulfillment.receivedOrder(order);

        logger.info(order.getCustomer() + " ordered " + order.getQuantity() + " potatoes.");
    }

    @Override
    public synchronized void produce() {
        try {
            warehouse.put(productionRate);
        }
        catch (OutOfCapacityException e) {
            logger.warn("Warehouse is full, dumping production!");
        }
    }

    @Override
    public synchronized void processOrder() {
        while (fulfillment.ordersAvailable()) {

            try {
                PotatoOrder order = fulfillment.processNextOrder();

                int quantity = order.getQuantity();

                try {
                    warehouse.fetch(quantity);
                } catch (OutOfStockException e) {
                    logger.warn("Out of stock, will put order on hold!");

                    return;
                }

                PotatoDelivery delivery = new PotatoDelivery(order.getCustomer(), quantity);

                //deliveries.push(delivery);
                dispatch(delivery);

                fulfillment.orderCompleted(order);

                logger.info("Prepared " + quantity + " potatoes for " + order.getCustomer() + ".");
            }
            catch (NoOrdersToProcessException e)
            {
                logger.warn("Could not retrieve order although orders available!");

                break;
            }
        }

        logger.info("No orders pending.");
    }

    @Override
    public synchronized void dispatch(PotatoDelivery delivery) {
        try {
            dispatch.dispatch(delivery);
        } catch (DispatchFailedException e) {
            logger.error("Could not dispatch delivery.");
        }

    }
}

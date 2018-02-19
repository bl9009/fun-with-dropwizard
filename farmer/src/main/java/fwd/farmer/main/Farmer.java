package fwd.farmer.main;

import fwd.common.kv.TransactionFailedException;
import fwd.common.main.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;

public class Farmer implements PotatoProducer {

    private FarmerWarehouse warehouse;

    private LinkedList<PotatoOrder> orders;
    private LinkedList<PotatoDelivery> deliveries;

    private int productionRate;

    Logger logger;

    public Farmer(FarmerWarehouse warehouse, int productionRate) {
        this.warehouse = warehouse;

        this.productionRate = productionRate;

        orders = new LinkedList();
        deliveries = new LinkedList();

        logger = LoggerFactory.getLogger(Farmer.class);
    }

    @Override
    public synchronized void addOrder(PotatoOrder order) {
        orders.push(order);

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
        while (!orders.isEmpty()) {
            PotatoOrder order = orders.peek();

            int quantity = order.getQuantity();

            try {
                warehouse.fetch(quantity);
            } catch (OutOfStockException e) {
                logger.warn("Out of stock, will put order on hold!");

                return;
            }

            PotatoDelivery delivery = new PotatoDelivery(order.getCustomer(), quantity);

            deliveries.push(delivery);

            orders.removeFirst();

            logger.info("Prepared " + quantity + " potatoes for " + order.getCustomer() + ".");
        }

        logger.info("No orders pending.");
    }

    @Override
    public synchronized void deliver() {

        //return new PotatoDelivery(quantity);
    }
}

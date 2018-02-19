package fwd.factory.main;

import fwd.common.main.PotatoDelivery;
import fwd.common.main.PotatoOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Factory {

    private PotatoVendor potatoVendor;

    private int productionRate;

    private int friesPerPotato;

    private int friesStock;
    private int potatoStock;

    Logger log;

    public Factory(int productionRate, int friesPerPotato, PotatoVendor potatoVendor) {
        this.productionRate = productionRate;
        this.friesPerPotato = friesPerPotato;

        this.potatoVendor = potatoVendor;

        log = LoggerFactory.getLogger(Factory.class);
    }

    public FriesDelivery deliver(FriesOrder order) {

        int quantity = order.getQuantity();

        while (friesStock < quantity)
        {
            log.info("fries out of stock, producing new ones...");

            produce();
        }

        friesStock -= quantity;

        log.info("delivering " + quantity + " fries, stock size now " + friesStock);

        return new FriesDelivery(quantity);
    }

    private void produce() {

        int minQuantity = productionRate / friesPerPotato;

        while (potatoStock < minQuantity) {
            log.info("potatoes out of stock, purchasing " + minQuantity * 10 + " potatoes....");

            purchasePotatoes(new PotatoOrder("fwd_factory_1", minQuantity * 10));
        }

        int potatoesUsed = productionRate / friesPerPotato;

        potatoStock -= potatoesUsed;

        friesStock += productionRate;

        log.info("produced " + productionRate + " fries, stock size now " + friesStock + ". Used " + potatoesUsed + " potatoes, stock size now: ", potatoStock);
    }

    private void purchasePotatoes(PotatoOrder order) {

        PotatoDelivery delivery = potatoVendor.purchase(order);

        potatoStock += delivery.getQuantity();

        log.info("potatoes delivered, stock size is now " + potatoStock);
    }
}

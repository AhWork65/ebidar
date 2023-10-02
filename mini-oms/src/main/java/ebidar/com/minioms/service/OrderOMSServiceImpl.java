package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.WalletNoBalanceException;
import ebidar.com.minioms.model.Order;
import ebidar.com.minioms.model.enums.OrderState;
import ebidar.com.minioms.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ebidar.com.minioms.model.Dto.*;

import java.math.BigDecimal;

@Service
public class OrderOMSServiceImpl implements IOrderOMSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShareOMSServiceImpl.class);

    private final OrderRepository orderRepository;
    private final IShareOMSService shareOMSService;
    private final ICustomerOMSService customerOMSService;
    private final IWalletService walletService;

    @Autowired
    public OrderOMSServiceImpl(OrderRepository orderRepository, IShareOMSService shareOMSService, ICustomerOMSService customerOMSService, IWalletService walletService) {
        this.orderRepository = orderRepository;
        this.shareOMSService = shareOMSService;
        this.customerOMSService = customerOMSService;
        this.walletService = walletService;
    }


    @Override
    public Long createOrder(OrderDto input) throws NotFound, WalletNoBalanceException {
        var customer = customerOMSService.getCustomerByExchangeCode(input.getExchangeCode());
        var share = shareOMSService.getShareByCode(input.getShareCode());
        walletService.blockBalance(customer.getWallet(),new BigDecimal(input.getAmount()),share.getSettlementDate());
        var order = orderRepository.save(new Order(share, customer, new BigDecimal(input.getAmount()), input.getCount(), input.getOrderType(), OrderState.NON));

        return order.getId();
    }

    @Override
    public void changeOrderState(Long id, OrderState orderState) throws NotFound {
        var order = orderRepository.findById(id);
        order.orElseThrow(() -> {
            LOGGER.error("order Not Found");
            return new NotFound("order Not Found");
        });
        if (orderState.equals(OrderState.ACCEPT)){
           walletService.reduceBlockPrice(order.get().getCustomer().getWallet(),order.get().getAmount() );
        }
        order.get().setOrderState(orderState);
    }
}

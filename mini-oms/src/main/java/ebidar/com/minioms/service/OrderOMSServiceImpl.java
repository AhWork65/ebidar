package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Dto.OrderDto;
import ebidar.com.minioms.model.Order;
import ebidar.com.minioms.model.enums.OrderState;
import ebidar.com.minioms.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderOMSServiceImpl implements IOrderOMSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ShareOMSServiceImpl.class);

    private final OrderRepository orderRepository;
    private final IShareOMSService shareOMSService;
    private final ICustomerOMSService customerOMSService;

    @Autowired
    public OrderOMSServiceImpl(OrderRepository orderRepository, IShareOMSService shareOMSService, ICustomerOMSService customerOMSService) {
        this.orderRepository = orderRepository;

        this.shareOMSService = shareOMSService;
        this.customerOMSService = customerOMSService;
    }


    @Override
    public Long createOrder(OrderDto input) throws  NotFound {
        var customer = customerOMSService.getCustomerByExchangeCode(input.getExchangeCode());
        var share = shareOMSService.getShareByCode(input.getShareCode());
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
        order.get().setOrderState(orderState);
    }
}

package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Dto.OrderDto;
import ebidar.com.minioms.model.enums.OrderState;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderOMSService {


    @Transactional(rollbackFor = {Exception.class})
    Long createOrder(OrderDto input) throws NotValidException, NotFound;

    @Transactional(rollbackFor = {Exception.class})
    void changeOrderState(Long id, OrderState orderState) throws NotFound;


}

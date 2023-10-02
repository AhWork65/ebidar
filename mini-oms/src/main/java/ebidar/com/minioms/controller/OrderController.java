package ebidar.com.minioms.controller;



import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Dto.OrderDto;
import ebidar.com.minioms.model.Dto.ShareDto;
import ebidar.com.minioms.model.Dto.UpdateOrderDto;
import ebidar.com.minioms.model.Share;
import ebidar.com.minioms.service.IOrderOMSService;
import ebidar.com.minioms.service.IShareOMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(maxAge = 4200)
@RequestMapping("/api/v1/order/")
public class OrderController {

    private final IOrderOMSService orderOMSService ;

    @Autowired
    public OrderController(IOrderOMSService orderOMSService) {
        this.orderOMSService = orderOMSService;
    }

    @PostMapping()
    public void createOrder(@RequestBody OrderDto input) throws NotValidException, NotFound {
        orderOMSService.createOrder(input);
    }

    @PutMapping()
    public void updateOrder(@RequestBody UpdateOrderDto input) throws NotValidException, NotFound {
        orderOMSService.changeOrderState(input.getId(), input.getOrderState());
    }

}



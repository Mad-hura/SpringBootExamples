package demo.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import demo.boot.model.OrderPay;
import demo.boot.service.IOrderService;
import demo.boot.service.OrderServiceImpl;

@RestController
@RequestMapping("/order/payment")
public class OrderController {
	
	@Autowired
	private final IOrderService orderService;
	
	@Autowired
	public OrderController(IOrderService orderService) {
		this.orderService=orderService;
	}
	
	@GetMapping
	 public ResponseEntity<?> checkOrder(@RequestParam int pinNo, @RequestParam double balance) {
        ResponseEntity<List<OrderPay>> orderPaies = orderService.getAllPayments();
        for(OrderPay o : orderPaies.getBody()) {
        	if(o.getPinNo()==pinNo && o.getBalance()>=balance) {
        		return ResponseEntity.ok("Valid");
        	}
        }
        return ResponseEntity.ok("Invalid");

    }
	


}

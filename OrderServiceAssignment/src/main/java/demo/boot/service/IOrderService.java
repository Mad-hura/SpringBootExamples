package demo.boot.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.boot.model.OrderPay;

public interface IOrderService {
	ResponseEntity<List<OrderPay>> getAllPayments();

}

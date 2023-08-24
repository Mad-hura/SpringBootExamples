package demo.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import demo.boot.model.OrderPay;

@Service
public class OrderServiceImpl implements IOrderService {

	private final RestTemplate restTemplate;

	@Autowired
	public OrderServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private String getBaseUrl() {
		return "http://localhost:8080/payment";
	}

	@Override
	public ResponseEntity<List<OrderPay>> getAllPayments() {
		ParameterizedTypeReference<List<OrderPay>> responseType = new ParameterizedTypeReference<List<OrderPay>>() {
		};
		ResponseEntity<List<OrderPay>> responseEntity = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, null,
				responseType);
		return responseEntity;
	}

}

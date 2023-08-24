package demo.boot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import demo.boot.dao.PaymentDao;
import demo.boot.model.Payment;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	private final PaymentDao paymentDAO;
	
	@Autowired
	public PaymentController (PaymentDao paymentDao) {
		this.paymentDAO=paymentDao;
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments(){
		List<Payment> pay = (List<Payment>) paymentDAO.findAll();
		return ResponseEntity.ok(pay);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Payment>> getPaymentById(@PathVariable Long id) {
		Optional<Payment> payment = paymentDAO.findById(id);
		if (payment != null) {
			return ResponseEntity.ok(payment);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
		Payment createdPayment = paymentDAO.save(payment);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable Long id, @RequestBody Payment updatedPayment) {
		Optional<Payment> existingPaymentOptional = paymentDAO.findById(id);

		if (existingPaymentOptional.isPresent()) {
			Payment existingPayment = existingPaymentOptional.get();
			existingPayment.setBalance(updatedPayment.getBalance());

			Payment updated = paymentDAO.save(existingPayment);
			return ResponseEntity.ok(updated);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
		Optional<Payment> payment = paymentDAO.findById(id);
		if (payment != null) {
			paymentDAO.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}



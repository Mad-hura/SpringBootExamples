package demo.boot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.boot.model.Payment;
@Repository
public interface PaymentDao extends CrudRepository<Payment, Long> {

}

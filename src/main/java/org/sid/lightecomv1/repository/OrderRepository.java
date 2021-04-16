package org.sid.lightecomv1.repository;

import org.sid.lightecomv1.entities.Order;
import org.sid.lightecomv1.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

package org.sid.lightecomv1.repository;

import org.sid.lightecomv1.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}

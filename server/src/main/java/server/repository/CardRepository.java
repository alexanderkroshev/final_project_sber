package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {


}

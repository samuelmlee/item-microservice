package platform.codingnomads.co.itemmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.codingnomads.co.itemmicroservice.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}

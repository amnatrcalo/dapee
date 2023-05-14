package ba.unsa.etf.nwt.systemeventsservice.Repository;

import ba.unsa.etf.nwt.systemeventsservice.Model.SystemEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemEventRepository extends JpaRepository<SystemEvent, Integer> {
}

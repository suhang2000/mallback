package scu.suncaper.mallback.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import scu.suncaper.mallback.pojo.Saler;

public interface SalerDAO extends JpaRepository<Saler, Integer> {
    Saler findBySname(String sname);

    Saler getBySnameAndPassword(String sname, String password);
}

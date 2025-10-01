package demo.nicolas.yepes.tdd_con_ia.profile.repositories;

import demo.nicolas.yepes.tdd_con_ia.profile.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}

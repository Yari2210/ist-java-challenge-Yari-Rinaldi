package ist.challenge.repository;


import id.co.asyst.commons.core.repository.BaseRepository;
import ist.challenge.model.DatabaseModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends BaseRepository<DatabaseModel, String> {

    DatabaseModel findByUsername(String username);


}

package ist.challenge.service;

import id.co.asyst.commons.core.component.Translator;
import id.co.asyst.commons.core.exception.ApplicationException;
import id.co.asyst.commons.core.payload.Status;
import id.co.asyst.commons.core.service.BaseService;
import id.co.asyst.commons.core.utils.DateUtils;
import id.co.asyst.commons.core.utils.GeneratorUtils;
import ist.challenge.model.DatabaseModel;
import ist.challenge.repository.ApplicationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ApllicationServiceImpl extends BaseService<DatabaseModel, String> implements ApllicationService {

    Logger logger = LogManager.getLogger();

    private ApplicationRepository applicationRepository;

    @Autowired
    public ApllicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
//        this.setRepository(promoRegCodeRepository);
//        this.setCustomRepository(promoRegCodeRepository);
    }

    @Override
    public Object registration(DatabaseModel databaseModel) {

        if(databaseModel.getUsername() == null || databaseModel.getPassword() == null){
            throw new ApplicationException(new Status("HTTP Status Code 400", "INVALID", "Username dan / atau password kosong"));
        }

        DatabaseModel databaseModelDB = applicationRepository.findByUsername(databaseModel.getUsername());

        if (databaseModelDB != null){
            throw new ApplicationException(new Status("HTTP Status Code 409", "INVALID", "Username sudah terpakai"));
        }

        String numberString = GeneratorUtils.GenerateId("", DateUtils.now(), 3);
        long number = Long.parseLong(numberString);
        databaseModelDB.setId(number);
        databaseModelDB.setCreatedDate(DateUtils.now());
        databaseModelDB.setCreatedBy("user");

        applicationRepository.save(databaseModel);
        return databaseModel;
    }

    @Override
    public Object login(DatabaseModel databaseModel) {

        if(databaseModel.getUsername() == null || databaseModel.getPassword() == null){
            throw new ApplicationException(new Status("HTTP Status Code 400", "INVALID", "Username dan / atau password kosong"));
        }

        DatabaseModel databaseModelDB = applicationRepository.findByUsername(databaseModel.getUsername());
        if (databaseModelDB == null){
            throw new ApplicationException(new Status("HTTP Status Code 401", "INVALID", "Username tidak ditemukan"));
        }
        if(!databaseModelDB.getPassword().equals(databaseModel.getPassword())){
            throw new ApplicationException(new Status("HTTP Status Code 401", "INVALID", "username / password tidak cocok"));
        }

        return databaseModel;
    }

    @Override
    public Object edit(DatabaseModel databaseModel) {

        if(databaseModel.getUsername() == null || databaseModel.getPassword() == null){
            throw new ApplicationException(new Status("HTTP Status Code 400", "INVALID", "Username dan / atau password kosong"));
        }

        DatabaseModel databaseModelDB = applicationRepository.findByUsername(databaseModel.getUsername());
        if (databaseModelDB != null){
            throw new ApplicationException(new Status("HTTP Status Code 409", "INVALID", "Username sudah terpakai"));
        }

        if(databaseModelDB.getPassword().equals(databaseModel.getPassword())){
            throw new ApplicationException(new Status("HTTP Status Code 400", "INVALID", "Password tidak boleh sama dengan password sebelumnya"));
        }

        databaseModelDB.setUsername(databaseModel.getUsername());
        databaseModelDB.setPassword(databaseModel.getPassword());
        databaseModelDB.setUpdatedDate(DateUtils.now());
        databaseModelDB.setUpdatedBy("user");

        applicationRepository.save(databaseModelDB);
        return databaseModelDB;
    }

    @Override
    public Object list(DatabaseModel databaseModel) {

        List<DatabaseModel> databaseModelDB = applicationRepository.findAll();

        return databaseModelDB;
    }

}

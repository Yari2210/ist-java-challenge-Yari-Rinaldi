package ist.challenge.service;


import id.co.asyst.commons.core.service.Service;
import ist.challenge.model.DatabaseModel;

public interface ApllicationService extends Service<DatabaseModel, String> {

    Object registration(DatabaseModel databaseModel);
    Object login(DatabaseModel databaseModel);
    Object edit(DatabaseModel databaseModel);
    Object list(DatabaseModel databaseModel);

}

package ist.challenge.validator;

import id.co.asyst.commons.core.payload.BaseParameter;
import id.co.asyst.commons.core.validator.BaseValidator;
import id.co.asyst.commons.core.validator.ValidatorType;
import ist.challenge.model.DatabaseModel;
import org.springframework.stereotype.Component;

@Component
public class Validator extends BaseValidator<DatabaseModel> {
    public void validate(BaseParameter<DatabaseModel> parameter) {
        validate(parameter, DatabaseModel.class);
    }

    public void validate(DatabaseModel databaseModel, String type) {
        if (ValidatorType.CREATE.equals(type)) {
            onCreate(databaseModel);
        } else if (ValidatorType.UPDATE.equals(type)) {
            onUpdate(databaseModel);
        } else if (ValidatorType.DELETE.equals(type)) {
            onDelete(databaseModel);
        }else if (ValidatorType.RETRIEVEDETAIL.equals(type)){
            onDelete(databaseModel);
        }else if(type.equals("login")){
            onregis(databaseModel);
        }
        else if(type.equals("registration")){
            onregis(databaseModel);
        }
        else if(type.equals("edit")){
            onregis(databaseModel);
        }
    }

    private void onCreate(DatabaseModel databaseModel) {
        notNull(databaseModel, "data");
        notNull(databaseModel.getUsername(), "username");
        notBlank(databaseModel.getUsername(), "username");
        notNull(databaseModel.getPassword(), "password");
        notBlank(databaseModel.getPassword(), "password");
        isMax(databaseModel.getUsername(), 25, "username");
        isMax(databaseModel.getPassword(), 25, "password");

    }

    private void onUpdate(DatabaseModel databaseModel) {
        onCreate(databaseModel);
    }

    private void onDelete(DatabaseModel databaseModel) {
        notNull(databaseModel, "data");
        notNull(databaseModel.getUsername(), "username");
        notBlank(databaseModel.getUsername(), "username");
    }

    private void onregis(DatabaseModel databaseModel) {
        notNull(databaseModel, "data");
        notNull(databaseModel.getUsername(), "username");
        notBlank(databaseModel.getUsername(), "username");
        notNull(databaseModel.getPassword(), "password");
        notBlank(databaseModel.getPassword(), "password");
        isMax(databaseModel.getUsername(), 25, "username");
        isMax(databaseModel.getPassword(), 25, "password");

    }



}

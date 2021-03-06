package be.sentas.inidial.service;

import be.sentas.inidial.model.SettingsConfig;
import com.gluonhq.connect.ConnectState;
import com.gluonhq.connect.GluonObservableObject;
import com.gluonhq.connect.gluoncloud.GluonClient;
import com.gluonhq.connect.gluoncloud.GluonClientBuilder;
import com.gluonhq.connect.gluoncloud.OperationMode;
import com.gluonhq.connect.provider.DataProvider;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.annotation.PostConstruct;

public class StorageService {

    private static final String SETTINGS_CONFIG = "settingsConfig";

    private final ObjectProperty<SettingsConfig> settingsConfig = new SimpleObjectProperty<>(new SettingsConfig());
    private GluonClient gluonClient;

    @PostConstruct
    public void postConstruct() {
        gluonClient = GluonClientBuilder.create()
                .operationMode(OperationMode.LOCAL_ONLY)
                .build();
    }

    public void retrieveSettingsConfig() {
        GluonObservableObject<SettingsConfig> gluonSettingsConfig = DataProvider.retrieveObject(
                gluonClient.createObjectDataReader(SETTINGS_CONFIG, SettingsConfig.class));
        gluonSettingsConfig.stateProperty().addListener((obs, ov, nv) -> {
            if (ConnectState.SUCCEEDED.equals(nv) && gluonSettingsConfig.get() != null) {
                settingsConfig.set(gluonSettingsConfig.get());
            }
        });
    }

    public void storeSettingsConfig() {
        DataProvider.<SettingsConfig>storeObject(settingsConfig.get(),
                gluonClient.createObjectDataWriter(SETTINGS_CONFIG, SettingsConfig.class));
    }

    public ObjectProperty<SettingsConfig> settingsConfigProperty() {
        return settingsConfig;
    }

}

package com.github.vdemeester.miniws;

import com.github.vdemeester.miniws.feature.FeatureManagerWrapper;
import com.github.vdemeester.miniws.feature.MiniwsFeatures;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.user.NoOpUserProvider;
import org.togglz.core.user.UserProvider;

import java.io.File;
import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    @Value("${feature.file}")
    private String featureFilePath;

    @Bean
    public FeatureManagerWrapper featureManagerWrapper() throws IOException {
        final FeatureManagerBuilder builder = new FeatureManagerBuilder();
        builder.name("miniws-feature-manager")
                .featureEnum(MiniwsFeatures.class)
                .stateRepository(stateRepository())
                .userProvider(userProvider());
        final FeatureManager featureManager = builder.build();
        final FeatureManagerWrapper featureManagerWrapper = FeatureManagerWrapper.getInstance();
        featureManagerWrapper.setFeatureManager(featureManager);
        return featureManagerWrapper;
    }

    @Bean
    public StateRepository stateRepository() throws IOException {
        final FileBasedStateRepository stateRepository = new FileBasedStateRepository(new File(featureFilePath));
        return stateRepository;
    }

    @Bean
    public UserProvider userProvider() {
        return new NoOpUserProvider();
    }
}

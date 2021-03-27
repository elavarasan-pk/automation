package com.hi.techpoints.cucumber.runner;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import com.hi.techpoints.cucumber.exception.CucumberException;
import com.hi.techpoints.cucumber.listener.CucumberListener;
import com.hi.techpoints.cucumber.util.Storage;
import com.hi.techpoints.cucumber.util.TestRepository;

import cucumber.runtime.ClassFinder;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import cucumber.runtime.junit.Assertions;
import cucumber.runtime.junit.FeatureRunner;
import cucumber.runtime.junit.JUnitReporter;
import cucumber.runtime.model.CucumberFeature;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Elavarasan
 * 
 */
@Slf4j
public class CreateCucumberRuntime extends ParentRunner<FeatureRunner>{

	private  JUnitReporter jUnitReporter;
    private final List<FeatureRunner> children = new ArrayList<FeatureRunner>();
    private final Runtime runtime;
    
    public CreateCucumberRuntime(Class<?> clazz) throws InitializationError, IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, CucumberException {
        super(clazz);
        ClassLoader classLoader = clazz.getClassLoader();
        Assertions.assertNoCucumberAnnotatedMethods(clazz);

        RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(clazz);
        RuntimeOptions runtimeOptions = runtimeOptionsFactory.create();

        List<String> features = TestRepository.getAllFeatures();
        log.info("Number of future files : [{}]",features.size());
		Field field = RuntimeOptions.class.getDeclaredField("featurePaths");
		field.setAccessible(true);
		field.set(runtimeOptions, features);
		
        ResourceLoader resourceLoader = new MultiLoader(classLoader);
        runtime = createRuntime(resourceLoader, classLoader, runtimeOptions);
		List<CucumberFeature> cucumberFeatures = runtimeOptions.cucumberFeatures(resourceLoader);
        jUnitReporter = new JUnitReporter(runtimeOptions.reporter(classLoader), runtimeOptions.formatter(classLoader), runtimeOptions.isStrict());
        addChildren(cucumberFeatures);
    }

    protected Runtime createRuntime(ResourceLoader resourceLoader, ClassLoader classLoader,
                                    RuntimeOptions runtimeOptions) throws InitializationError, IOException {
        ClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        return new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
    }

    @Override
    public List<FeatureRunner> getChildren() {
        return children;
    }

    @Override
    protected Description describeChild(FeatureRunner child) {
        return child.getDescription();
    }

    @Override
    protected void runChild(FeatureRunner child, RunNotifier notifier) {
	    try {
			Storage.setupFeatureParam(child);
		} catch (CucumberException e) {
			log.error("Unable to set respective future file input value on storage");
			e.printStackTrace();
		}
        child.run(notifier);
    }

    @Override
    public void run(RunNotifier notifier) {
    	notifier.addListener(new CucumberListener());
    	super.run(notifier);
        jUnitReporter.done();
        jUnitReporter.close();
        runtime.printSummary();
    }

    private void addChildren(List<CucumberFeature> cucumberFeatures) throws InitializationError {
        for (CucumberFeature cucumberFeature : cucumberFeatures) {
            children.add(new FeatureRunner(cucumberFeature, runtime, jUnitReporter));
        }
    }

}

package core;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class TestRunner extends BlockJUnit4ClassRunner {

    private TestRunListener testRunListener;

    public TestRunner(Class currentClass) throws InitializationError {
        super(currentClass);
        testRunListener = new TestRunListener();
    }

    public void run(final RunNotifier notifier) {
        notifier.addListener(testRunListener);
        super.run(notifier);
    }
}

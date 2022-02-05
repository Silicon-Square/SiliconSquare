package it.siliconsquare.logger;

import io.sentry.*;
import io.sentry.protocol.Message;
import io.sentry.protocol.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Logger {

    private static Logger instanceLog = null;

    private static final String OPERATING_SYSTEM = System.getProperty("os.name");
    private static final String JAVA_VERSION = System.getProperty("java.version");
    private static final String JAVA_VERSION_DATE = System.getProperty("java.version.date");
    private static final String DEVICE_USER_NAME = System.getProperty("user.name");

    private ITransaction globalTransaction = null;

    public static Logger getInstance() {
        if (instanceLog == null)
            instanceLog = new Logger();
        return instanceLog;
    }

    private Logger() {
        setLogger();
    }

    private void setLogger() {

        Properties properties = new Properties();
        URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(applicationProperty.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dsn = properties.getProperty("sentry.dsn");
        Sentry.init(options -> {
            options.setDsn(dsn);
            options.setSampleRate(1.0);
            options.setTracesSampleRate(1.0);
            options.setEnvironment("Dev");
            options.setTag("Application_Type", "Server");
            options.setTag("Operating_System", OPERATING_SYSTEM);
            options.setTag("Java_Version", JAVA_VERSION);
            options.setTag("Java_Version_Date", JAVA_VERSION_DATE);
            options.setTag("Device_User_Name", DEVICE_USER_NAME);
        });
    }

    /**
     * Specify to the logger, which user has been connected to the app.
     *
     * @param email
     */
    public void setUser(String email) {
        Sentry.configureScope(scope -> {
            User user = new User();
            user.setEmail(email);
            scope.setUser(user);
        });
    }

    public void captureException(Exception e) {
        Sentry.captureException(e);
        if (globalTransaction != null) {
            globalTransaction.setThrowable(e);
            globalTransaction.setStatus(SpanStatus.INTERNAL_ERROR);
            closeTransaction(globalTransaction);
        }
    }

    /**
     * Closes the Transaction which captures the performance
     *
     * @param transaction - The transaction used for
     *                    {@link #startTransaction(String, String)}
     */
    public void closeTransaction(ITransaction transaction) {
        if (transaction != null) {
            transaction.finish();
        }
        if (globalTransaction != null) {
            globalTransaction.finish();
            globalTransaction = null;
        }

    }

    public void captureMessage(String message) {
        Sentry.captureMessage(message);
    }

    public void captureMessage(String errorMessage, SentryLevel level) {
        Sentry.captureMessage(errorMessage, level);
    }

    /**
     * Begin the transaction, to capture the performance of the method/action from this moment.
     *
     * @param methodName name of the method to make nome del metodo to
     *                   facilitate the identification of provenance
     * @param taskName   description of the action that is being executed
     * @return ITransaction - the transaction to pass to
     * {@link #closeTransaction(ITransaction)}
     */
    public ITransaction startTransaction(String methodName, String taskName) {
        globalTransaction = Sentry.startTransaction(methodName, taskName);
        return globalTransaction;
    }

    public void captureException(Exception e, String errorMessage) {
        SentryEvent event = new SentryEvent();
        Message message = new Message();
        message.setMessage(errorMessage);
        event.setMessage(message);
        event.setThrowable(e);
        Sentry.captureEvent(event);
    }
}

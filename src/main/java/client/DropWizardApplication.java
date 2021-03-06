package client;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.BankResource;

/**
 * Created by: tituskc
 * Created On  Sat, Sep 02, 2017 at 5:10 PM.
 */
public class DropWizardApplication extends Application<DropwizardConfiguration>
{
    public static void main(String[] args) throws Exception {
        new DropWizardApplication().run(args);
    }
    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) throws Exception
    {
        final BankResource resource = new BankResource(
                "Titus Chirchir"
        );
        environment.jersey().register(resource);
    }
}

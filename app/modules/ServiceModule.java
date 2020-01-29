package modules;

import com.google.inject.AbstractModule;
import services.CacheService;
import services.impl.CacheServiceImpl;

public class ServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(CacheService.class).to(CacheServiceImpl.class).asEagerSingleton();
    }
}

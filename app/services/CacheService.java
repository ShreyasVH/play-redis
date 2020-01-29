package services;

public interface CacheService
{
    Boolean set(String key, String value, Integer expiry);

    String get(String key);

    Boolean exists(String key);

    Boolean delete(String key);
}

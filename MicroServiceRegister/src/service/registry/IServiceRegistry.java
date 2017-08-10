package service.registry;
public interface IServiceRegistry {
	
	public void registerService(String serviceName, String uri);
	public void unRegisterService(String serviceName, String uri);

}

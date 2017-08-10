package service.registry;

import java.io.IOException;
import java.util.Properties;

public class ServiceRegistry implements IServiceRegistry{
	
	public ServiceRegistry() {
		Properties prop=new Properties();
		try {
			prop.load(this.getClass().getResourceAsStream("zookeeper.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void registerService(String serviceName, String uri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unRegisterService(String serviceName, String uri) {
		// TODO Auto-generated method stub
		
	}

}

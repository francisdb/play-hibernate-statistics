package plugins.hibernatestatistics;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;

import play.Logger;
import play.Play;
import play.PlayPlugin;
import play.db.jpa.JPA;
import play.mvc.Router;

public class StatisticsPlugin extends PlayPlugin {

	@Override
	public void onApplicationStart() {
		if(isEnabled()){
			Logger.info("Enabling hibernate statistics");
			Statistics stats = getStatistics();
			stats.setStatisticsEnabled(true);
		}else{
			Logger.info("Disabling hibernate statistics");
			Statistics stats = getStatistics();
			stats.setStatisticsEnabled(false);
		}
	}
	
	@Override
	public void onRoutesLoaded() {
		if(isEnabled()){
			Router.addRoute("GET", "/@statistics", "hibernatestatistics.HibernateStatistics.index");
		}
	}

	public static boolean isEnabled(){
		String enabled = Play.configuration.getProperty("hibernate.statistics");
		return enabled != null && enabled.toLowerCase().equals("true");
	}
	
	public static Statistics getStatistics() {
		EntityManagerFactoryImpl entityManagerFactory = (EntityManagerFactoryImpl) JPA.entityManagerFactory;
		Statistics stats = entityManagerFactory.getSessionFactory().getStatistics();
		return stats;
	}
}

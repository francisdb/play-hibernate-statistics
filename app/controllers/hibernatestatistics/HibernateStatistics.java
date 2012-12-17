package controllers.hibernatestatistics;

import org.hibernate.stat.Statistics;

import play.mvc.Controller;
import plugins.hibernatestatistics.StatisticsPlugin;

public class HibernateStatistics extends Controller{

	public static void index() {
		if(!StatisticsPlugin.isEnabled()){
			notFound("Statistics disabled");
		}
		Statistics statistics = StatisticsPlugin.getStatistics();
		render(statistics);
	}
}

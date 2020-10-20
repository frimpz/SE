package view;
import java.util.List;

import business.IPersonService;

import model.Person;




public class ReportController {
	
	private IPersonService service = null;
	
	public IPersonService getService() {
		return service;
	}

	public void setService(IPersonService service) {
		this.service = service;
	}

	public List<Person> getPersonList() {
		return service.getPersonList();
	}

	public Long getReportPersonSalaryTotal() {
		Long total = 0l;
		for (Person person : getPersonList()) {
			total += person.getSalary();
		}
		return total;
	}

	public String getReportPersonMarriedStat() {
		Long total = (long) getPersonList().size();
		Long married = 0l;
		for (Person person : getPersonList()) {
			if (person.getMarried()) {
				married += 1;
			}
		}
		return married + "/" + total;

	}
	
}

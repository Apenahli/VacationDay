package com.example.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.example.model.Employee;
import com.example.model.Event;
import com.example.model.Vacation;
import com.example.model.VacationMonths;
import com.example.service.EmployeeService;
import com.example.service.EventService;
import com.example.service.VacationMonthsService;
import com.example.service.VacationService;

@Controller
@RequestMapping("/")
public class VacationController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private VacationService vacationService;

	@Autowired
	private VacationMonthsService vacationMonthsService;

	@Autowired
	private EventService eventService;

	@GetMapping
	public ModelAndView test() {

		ModelAndView view = new ModelAndView("vacation/Test");

		List<Vacation> vacations = vacationService.findVacatioByYear("2019-2020");

		List<Vacation> vacations2 = getCalculatedList(vacations);

		view.addObject("vacations", vacations2);

		return view;
	}

	@GetMapping("/{year}")
	public ModelAndView x(@PathVariable("year") String year) {

		ModelAndView view = new ModelAndView("redirect:/");
		ModelAndView view2 = new ModelAndView("vacation/x");

		if (year.equals("2019-2020")) {

			return view;

		} else {

			List<Vacation> vacations = vacationService.findVacatioByYear(year);
			List<Vacation> vacations2 = getCalculatedList2(vacations);
			String oldYear = splitYear(year);
			view2.addObject("vacations", vacations2);
			view2.addObject("message", year);
			view2.addObject("oldYear", oldYear);
			return view2;
		}
	}

	public String splitYear(String year) {
		String[] sysdate = year.split("-");
		String year1 = sysdate[0];
		String year2 = sysdate[1];

		int y1 = Integer.parseInt(year1);
		int y2 = Integer.parseInt(year2);
		int s1 = y1 - 1;
		int s2 = y2 - 1;
		String k = s1 + "-" + s2;

		return k;
	}

	List<Vacation> getCalculatedList2(List<Vacation> vacations) {

		List<Vacation> vacations2 = new ArrayList<Vacation>();

		for (Vacation vacation : vacations) {

			Vacation vacationObj = new Vacation();

			Employee employee = employeeService.findById(vacation.getEmployee().getId());

			String oldYear = splitYear(vacation.getYear());

			int empId = vacation.getEmployee().getId();
			int oldRemainder = 0;

			Vacation oldRemainderrr = vacationService.getOldRemander(oldYear, empId);

			if (oldRemainderrr != null) {
				oldRemainder = oldRemainderrr.getRemainder();
			}

			String stajj = employee.getEmployeeStaj();
			long oldStaj = empOldStaj(stajj);
			long newStaj = empNewStaj(employee.getStartTime());
			long endStaj = oldStaj + newStaj;

			int empStajDay = CalculateEmpStaj(endStaj);

			int basicVacationDay = vacation.getBasic_vacation_day();
			int sosialVacationDay = vacation.getSosial_vacation_day();

			vacationObj.setRemainder(oldRemainder);
			vacationObj.setId(vacation.getId());
			vacationObj.setBasic_vacation_day(basicVacationDay);
			vacationObj.setYear(vacation.getYear());
			vacationObj.setSosial_vacation_day(sosialVacationDay);
			vacationObj.setStajVacationDay(empStajDay);
			vacationObj.setEmployee(employee);

			int total = oldRemainder + empStajDay + basicVacationDay + sosialVacationDay;
			vacationObj.setTotal(total);

			int v = finalRemainder(vacation.getId());
			int remainder = total - v;
			vacationObj.setFinal_remainder(remainder);

			vacations2.add(vacationObj);

		}
		return vacations2;
	}

	List<Vacation> getCalculatedList(List<Vacation> vacations) {

		List<Vacation> vacations2 = new ArrayList<Vacation>();

		for (Vacation vacation : vacations) {

			Vacation vacationObj = new Vacation();

			Employee employee = employeeService.findById(vacation.getEmployee().getId());

			int oldRemainder = employee.getRemainder();
			// System.out.println(oldRemainder);

			String stajj = employee.getEmployeeStaj();
			long oldStaj = empOldStaj(stajj);
			long newStaj = empNewStaj(employee.getStartTime());
			long endStaj = oldStaj + newStaj;

			int empStajDay = CalculateEmpStaj(endStaj);

			int basicVacationDay = vacation.getBasic_vacation_day();
			int sosialVacationDay = vacation.getSosial_vacation_day();

			vacationObj.setId(vacation.getId());
			vacationObj.setRemainder(employee.getRemainder());
			vacationObj.setBasic_vacation_day(basicVacationDay);
			vacationObj.setYear(vacation.getYear());
			vacationObj.setSosial_vacation_day(sosialVacationDay);
			vacationObj.setStajVacationDay(empStajDay);
			vacationObj.setEmployee(employee);

			int total = oldRemainder + empStajDay + basicVacationDay + sosialVacationDay;
			vacationObj.setTotal(total);

			int v = finalRemainder(vacation.getId());
			int remainder = total - v;
			vacationObj.setFinal_remainder(remainder);

			vacations2.add(vacationObj);

		}
		return vacations2;
	}

	int finalRemainder(int x) {
		List<VacationMonths> months = vacationMonthsService.findVacationMonths(x);

		int s = 0;

		for (VacationMonths vacationMonths : months) {

			Date d1 = vacationMonths.getStartTime();
			Date d2 = vacationMonths.getEndTime();

			long diff = d2.getTime() - d1.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000);

			s = (int) (s + diffDays);
		}

		return s;
	}

	int CalculateEmpStaj(long endStaj) {

		if (endStaj > 365) {

			long year1 = (endStaj / 365);

			if (year1 >= 5 && year1 < 10) {
				return 2;
			} else if (year1 >= 10 && year1 < 15) {
				return 4;
			} else if (year1 >= 15) {
				return 6;
			} else {
				return 0;
			}
		}
		return 0;
	}

	public long empNewStaj(Date empStartTime) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String empStartTime1 = formatter.format(empStartTime);

		String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());

		String[] sysdate = empStartTime1.split("-");
		String year = sysdate[0];
		String month = sysdate[1];
		String day = sysdate[2];

		String newDate = month + "-" + day + "-" + year;

		try {

			String dateStart = newDate;
			String dateStop = date;

			SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");

			Date d1 = null;
			Date d2 = null;

			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			long diff = d2.getTime() - d1.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000);

			return diffDays;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	long empOldStaj(String staj) {
		String[] str = staj.split(":");
		String year = str[0];
		String month = str[1];
		String day = str[2];

		int year2 = Integer.parseInt(year);
		int month2 = Integer.parseInt(month);
		int day2 = Integer.parseInt(day);

		int x = 0;
		int y = 0;

		if (year2 > 0) {
			x = year2 * 365;
		}
		if (month2 > 0) {
			y = month2 * 30;
		}
		int d = x + y + day2;

		return d;
	}

	@GetMapping("test")
	public ModelAndView test2() {

		ModelAndView view = new ModelAndView("vacation/insert");

		view.addObject("vacation", new Vacation());
		view.addObject("employees", employeeService.findAll());

		return view;

	}
// ////////////////////////////////////////////////////

	@GetMapping("confirm")
	public ModelAndView confirm() {

		ModelAndView view = new ModelAndView("vacation/confirm1");

		return view;

	}

	@PostMapping("insertConfirm")
	public RedirectView insertConfirm1() {

		RedirectView view = new RedirectView("/");

		List<Vacation> vacations = vacationService.findVacatioByYear("2019-2020");

		List<Vacation> vacations2 = getCalculatedList(vacations);

		for (Vacation vacation : vacations2) {

			int finalRemainder = vacation.getFinal_remainder();
			
			vacationService.updateRemainder(finalRemainder, vacation.getId());
		}

		return view;

	}
	
	// ////////////////////////////////////////////////////
	
	@GetMapping("tx/{message}")
	public ModelAndView test22(@PathVariable("message") String year) {

		ModelAndView view = new ModelAndView("vacation/insert2");

		view.addObject("vacation", new Vacation());
		view.addObject("year", year);
		view.addObject("employees", employeeService.findAll());

		return view;

	}

	@PostMapping("insert")
	public RedirectView test3(@ModelAttribute("vacation") Vacation vacation, HttpServletRequest request) {

		RedirectView view = new RedirectView("/");
		vacation.setYear("2019-2020");
		Vacation vacation2 = vacationService.insert(vacation);
		Employee employee = employeeService.findById(vacation2.getEmployee().getId());

		employee.setEmployeeStaj("1:01:01");
		employeeService.edit(employee);

		return view;
	}

	@PostMapping("insert33")
	public RedirectView test33(@ModelAttribute("vacation") Vacation vacation, HttpServletRequest request) {

		System.out.println(vacation);

		RedirectView view = new RedirectView("/" + vacation.getYear());

		vacationService.insert(vacation);

		return view;
	}

	@GetMapping("/insert2/{id}")
	public ModelAndView test3(@PathVariable("id") int id) {

		long s = 0;

		ModelAndView view = new ModelAndView("vacation/edit");

		Vacation vacation = vacationService.findById(id);

		Employee employee = employeeService.findById(vacation.getEmployee().getId());

		List<VacationMonths> months = vacationMonthsService.findVacationMonths(id);

		Long l = empNewStaj(employee.getStartTime());

		if (l > 30) {
			s = l / 30;
		}
		if (s < 6) {
			view.addObject("msg", "6 aydan azdır");
		}

		view.addObject("vacationMonths", new VacationMonths());
		view.addObject("employee", employee);
		view.addObject("vacationMonth", months);
		view.addObject("canculV", getCalculatedObj(id));
		view.addObject("id", id);

		return view;

	}

	@GetMapping("/insert22/{id}")
	public ModelAndView test32(@PathVariable("id") int id) {

		long s = 0;

		ModelAndView view = new ModelAndView("vacation/edit2");

		Vacation vacation = vacationService.findById(id);

		Employee employee = employeeService.findById(vacation.getEmployee().getId());

		List<VacationMonths> months = vacationMonthsService.findVacationMonths(id);

		Long l = empNewStaj(employee.getStartTime());

		if (l > 30) {
			s = l / 30;
		}
		if (s < 6) {
			view.addObject("msg", "6 aydan azdır");
		}

		view.addObject("vacationMonths", new VacationMonths());
		view.addObject("employee", employee);
		view.addObject("vacationMonth", months);
		view.addObject("canculV", getCalculatedObj2(id));
		view.addObject("id", id);
		view.addObject("year", vacation.getYear());

		return view;

	}

	Vacation getCalculatedObj(int id) {

		Vacation vacation = vacationService.findById(id);

		Vacation vacationObj = new Vacation();

		Employee employee = employeeService.findById(vacation.getEmployee().getId());

		String stajj = employee.getEmployeeStaj();

		long oldStaj = empOldStaj(stajj);
		long newStaj = empNewStaj(employee.getStartTime());
		long endStaj = oldStaj + newStaj;

		int empStajDay = CalculateEmpStaj(endStaj);

		int basicVacationDay = vacation.getBasic_vacation_day();
		int sosialVacationDay = vacation.getSosial_vacation_day();

		vacationObj.setRemainder(employee.getRemainder());
		vacationObj.setId(vacation.getId());
		vacationObj.setBasic_vacation_day(basicVacationDay);
		vacationObj.setYear(vacation.getYear());
		vacationObj.setSosial_vacation_day(sosialVacationDay);
		vacationObj.setStajVacationDay(empStajDay);
		vacationObj.setEmployee(employee);

		int total = employee.getRemainder() + empStajDay + basicVacationDay + sosialVacationDay;

		vacationObj.setTotal(total);

		int v = finalRemainder(vacation.getId());
		int remainder = total - v;
		vacationObj.setFinal_remainder(remainder);

		return vacationObj;
	}

	Vacation getCalculatedObj2(int id) {

		Vacation vacation = vacationService.findById(id);

		Vacation vacationObj = new Vacation();

		Employee employee = employeeService.findById(vacation.getEmployee().getId());

		String oldYear = splitYear(vacation.getYear());

		int empId = vacation.getEmployee().getId();

		int oldRemainder = 0;

		Vacation oldRemainderrr = vacationService.getOldRemander(oldYear, empId);

		if (oldRemainderrr != null) {
			oldRemainder = oldRemainderrr.getRemainder();
		}

		String stajj = employee.getEmployeeStaj();
		long oldStaj = empOldStaj(stajj);
		long newStaj = empNewStaj(employee.getStartTime());
		long endStaj = oldStaj + newStaj;

		int empStajDay = CalculateEmpStaj(endStaj);

		int basicVacationDay = vacation.getBasic_vacation_day();
		int sosialVacationDay = vacation.getSosial_vacation_day();

		vacationObj.setRemainder(oldRemainder);
		vacationObj.setId(vacation.getId());
		vacationObj.setBasic_vacation_day(basicVacationDay);
		vacationObj.setYear(vacation.getYear());
		vacationObj.setSosial_vacation_day(sosialVacationDay);
		vacationObj.setStajVacationDay(empStajDay);
		vacationObj.setEmployee(employee);

		int total = oldRemainder + vacation.getRemainder() + empStajDay + basicVacationDay + sosialVacationDay;
		vacationObj.setTotal(total);

		int v = finalRemainder(vacation.getId());
		int remainder = total - v;
		vacationObj.setFinal_remainder(remainder);

		return vacationObj;
	}

//burda mezuniyyet araliqlari verir
	@PostMapping("/save")
	public RedirectView test4(@ModelAttribute("vacationMonths") VacationMonths vacationMonths,
			HttpServletRequest request) {

		RedirectView view = new RedirectView("/");

		int value = event(vacationMonths.getStartTime(), vacationMonths.getEndTime());

		try {
			if (value != 0) {
				Date date = vacationMonths.getEndTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dt = sdf.format(date);
				
				System.out.println(dt);
				
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));
				c.add(Calendar.DATE, value);
				dt = sdf.format(c.getTime());
			
				System.out.println(dt);
				
				SimpleDateFormat sdfx = new SimpleDateFormat("yyyy-MM-dd");
				Date result = sdfx.parse(dt);
				
				System.out.println(result);
				
				vacationMonths.setEndTime(result);
			}

			vacationMonthsService.insert(vacationMonths);
		} catch (Exception e) {

		}

		return view;

	}

//  mezuniyyet goturmek istediyin tarixde bayram gunune dusub dusmediyini yoxlayir ,
	// duwurse nece gun dusur, geriye int say qaytarir
	int event(Date start, Date end) {

		int s = 0;
		try {
			// son tarixden baslangic tarixi cixib gun sayini tapaq
			long diff = end.getTime() - start.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000);

			// gun sayi tapdiq : diffDays
			// indi ise baslangic tarixinin ustine tapdiqimiz gin sayini gelerek elde
			// edilmis tarixler listini duzeldek

			List<String> str1 = new ArrayList<String>();
			for (int i = 0; i < diffDays; i++) {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dt = sdf.format(start);
				Calendar c = Calendar.getInstance();
				c.setTime(sdf.parse(dt));
				c.add(Calendar.DATE, i);
				dt = sdf.format(c.getTime());
				str1.add(dt);
			}

			// iscinin mezuniyyet gunlerinin listi duzeltdik...

			// indi ise event listini alib (bayram tarixlerini) string list duzeldek

			List<Event> events = eventService.findAll();
			List<String> str2 = new ArrayList<String>();
			for (Event event : events) {

				Date date = convertToDateViaSqlTimestamp(event.getStart());
				SimpleDateFormat sdfg = new SimpleDateFormat("yyyy-MM-dd");
				String dt = sdfg.format(date);
				str2.add(dt);
			}

			// calendardaki tarixlerimide list sekline saldim

			// indi ise iki listi muqayise edim ve nece gunun(int) bayram gunune dusduyunu
			// hesablayim

			for (String string1 : str1) {
				for (String string2 : str2) {
					if (string1.equals(string2)) {
						// System.out.println(string1 + " equalsdir " + string2);
						s = s + 1;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@PostMapping("/save2")
	public RedirectView test42(@ModelAttribute("vacationMonths") VacationMonths vacationMonths,
			HttpServletRequest request) {

		RedirectView view = new RedirectView("/" + request.getParameter("year"));

		vacationMonthsService.insert(vacationMonths);

		return view;

	}
}

package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongo.MongoTodoData;
import com.mongo.MongoTodoListData;
import com.mongo.MongoTodoRepository;

@Controller
@EnableScheduling
@RequestMapping("/")
public class MainController {

	// MongoDBの操作
	@Autowired
	private MongoTodoRepository listrepo;

	/*
	 * toppage
	 */
	@RequestMapping(value = "/")
	public String toppage(Model model) {
		model.addAttribute("MongoTodoData", new MongoTodoData());
		List<MongoTodoData> datas = listrepo.findAll();
		model.addAttribute("datas", datas);
		return "listnew";
	}

	/*
	 * データ準備
	 */
	@RequestMapping(value = "/listnew")
	public String listnewpage(Model model) {
		model.addAttribute("MongoTodoData", new MongoTodoData());
		List<MongoTodoData> datas = listrepo.findAll();
		model.addAttribute("datas", datas);
		return "listnew";
	}

	@RequestMapping(value = "/listnew", method = RequestMethod.POST)
	public String indexFormSubmit(@ModelAttribute MongoTodoData MongoTodoData, @RequestParam("title") String title,
			Model model) {

		boolean isTitleDoubles = false; // タイトル重複回避フラグ
		List<MongoTodoData> datas = listrepo.findAll();
		model.addAttribute("MongoTodoData", MongoTodoData);

		for (MongoTodoData data : datas) {
			if (data.getTitle().equals(MongoTodoData.getTitle())) {
				// 重複リスト名はセーブしない
				isTitleDoubles = true;
			} else {
			}
		}
		if (!isTitleDoubles) {
			listrepo.save(MongoTodoData); // titleのみ
			isTitleDoubles = true;
		}
		/*
		 * todoリスト一覧へ
		 */
		model.addAttribute("datas", datas);

		return "redirect:/listnew";
	}

	/*
	 * データ準備 listnewの遷移先hrefのパラメータ（タイトル）を受け取りたい
	 */
	private static String find = "";

	/*
	 * @RequestParam("id") String id Todoリストの名前を取得
	 */
	@RequestMapping(value = "/addtodo")
	public String addFormTodo(@RequestParam("id") String id, Model model) {

		MongoTodoData MongoTodoData = new MongoTodoData();
		MongoTodoData.setTitle(find);
		model.addAttribute("MongoTodoData", MongoTodoData);
		model.addAttribute("MongoTodoListData", new MongoTodoListData());

		/*
		 * タイトルをセット
		 */
		List<MongoTodoData> lists = listrepo.findAll();
		for (MongoTodoData test : lists) {
			if (id.equals(test.getTitle())) {
				System.out.print("find title : ");
				System.out.println(test.getTitle());
				find = test.getTitle();
			}
		}

		/*
		 * Todoリスト一覧
		 */
		List<MongoTodoListData> todoAll = new ArrayList<MongoTodoListData>();
		if (lists == null) {
			lists = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : lists) {
				// Listタイトルゲット
				if (data.getTitle().equals(find)) {
					for (int j = 0; j < data.getInsideTodo().size(); ++j) {
						todoAll.add(data.getInsideTodo().get(j));
					}
				}
			}
		}
		model.addAttribute("todoAll", todoAll);
		return "addtodo";
	}

	@RequestMapping(value = "/addtodo", method = RequestMethod.POST)
	public String TodoResultForm(Model model, @ModelAttribute MongoTodoListData MongoTodoListData,
			@ModelAttribute MongoTodoData MongoTodoData) {

		model.addAttribute("MongoTodoListData", MongoTodoListData);

		// 一覧 ↓
		/*
		 * インプット
		 */
		List<MongoTodoListData> todoAll = new ArrayList<MongoTodoListData>();
		List<MongoTodoData> lists = listrepo.findAll();
		if (lists == null) {
			lists = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : lists) {
				// Listタイトルゲット
				if (data.getTitle().equals(find)) {
					data.setInsideTodo(MongoTodoListData);
					listrepo.save(data);
					// view...
					for (int j = 0; j < data.getInsideTodo().size(); ++j) {
						todoAll.add(data.getInsideTodo().get(j));
						/*
						 * タイマー if (data.getInsideTodo().get(j).getTimer() != null) { //
						 * TodoScheduler.setTimerFlag(true); // new
						 * CronTrigger(data.getInsideTodo().get(j).getTimer());
						 * System.out.println(data.getInsideTodo().get(j).getTimer()); // new
						 * CronSetting().setCron1(data.getInsideTodo().get(j).getTimer()); }
						 */
					}
				}
			}
		}

		model.addAttribute("todoAll", todoAll);
		return "addtodo";
	}

	/*
	 * @RequestParam String action クリアしたいTodoのインデックスを取得
	 */
	@RequestMapping(value = "/addtodo", method = RequestMethod.DELETE)
	public String delete(Model model, @ModelAttribute MongoTodoListData MongoTodoListData,
			@ModelAttribute MongoTodoData MongoTodoData, @RequestParam String action) {

		model.addAttribute("MongoTodoListData", MongoTodoListData);

		int index = Integer.parseInt(action);
		if (action.equals("delete")) {
			System.out.print("DELETE MODE........ :");
		}

		List<MongoTodoData> lists1 = listrepo.findAll();
		if (lists1 == null) {
			lists1 = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : lists1) {
				// Listタイトルゲット
				if (data.getTitle().equals(find)) {
					listrepo.delete(data);
					data.getInsideTodo().remove(index);
					listrepo.save(data);
					// listrepo.delete(data);
				}
			}
		}

		List<MongoTodoListData> todoAll = new ArrayList<MongoTodoListData>();
		List<MongoTodoData> lists = listrepo.findAll();
		if (lists == null) {
			lists = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : lists) {
				// Listタイトルゲット
				if (data.getTitle().equals(find)) {
					// view...
					for (int j = 0; j < data.getInsideTodo().size(); ++j) {
						todoAll.add(data.getInsideTodo().get(j));
					}
				}
			}
		}

		model.addAttribute("todoAll", todoAll);

		return "addtodo";
	}

	/*
	 * データまとめて全部消去 めんどくさいので
	 */
	@RequestMapping(value = "/check/alldelete")
	public String delForm(Model model) {
		listrepo.deleteAll();
		return "/check/alldelete";
	}

	@RequestMapping(value = "/check/alldelete", method = RequestMethod.POST)
	public String delFormto(@ModelAttribute MongoTodoData todo, Model model) {
		listrepo.deleteAll();
		return "/check/alldelete";
	}

	/*
	 * リクエスト前 一覧表示
	 */
	@RequestMapping("/search")
	public Model searchForm(Model model) {
		List<MongoTodoData> datas = listrepo.findAll();
		List<MongoTodoListData> dat = new ArrayList<MongoTodoListData>();
		if (datas == null) {
			datas = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : datas) {
				for (int j = 0; j < data.getInsideTodo().size(); ++j) {
					// System.out.println(data.getInsideTodo().get(j));
					dat.add(data.getInsideTodo().get(j));
				}
			}
		}
		model.addAttribute("dat", dat);
		return model;
	}

	/*
	 * @RequestParam("key") String key 検索パラメータ
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Model searchFormto(Model model, @RequestParam("key") String key) {

		List<MongoTodoData> datas = listrepo.findAll();
		List<MongoTodoListData> dat = new ArrayList<MongoTodoListData>();
		if (datas == null) {
			datas = new ArrayList<MongoTodoData>();
		} else {
			for (MongoTodoData data : datas) {
				for (int j = 0; j < data.getInsideTodo().size(); ++j) {
					String find = data.getInsideTodo().get(j).getMemo();
					if (key != null) {
						if (find.equals(key)) {
							System.out.println(find);
							dat.add(data.getInsideTodo().get(j));
						}
					}
				}
			}
		}

		// model.addAttribute("msg","this is MongoDB sample.");
		model.addAttribute("dat", dat);
		return model;
	}

}

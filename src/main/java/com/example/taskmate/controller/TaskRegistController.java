package com.example.taskmate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.taskmate.entity.Task;
import com.example.taskmate.form.TaskRegistForm;
import com.example.taskmate.service.TaskService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TaskRegistController {
	
	private final TaskService taskService;
	
	//タスク登録画面表示リクエスト
	@PostMapping("/task-show-regist")
	public String showRegist(@ModelAttribute TaskRegistForm form) {
		return "task-regist";
	}
	
	//タスク登録リクエスト（登録画面より）
	@PostMapping("/task-regist")
	public String regist(@Validated @ModelAttribute TaskRegistForm form,
			BindingResult result) {
		
		//入力エラーがある場合には、タスク登録画面に戻す
		if(result.hasErrors()) {
			return "task-regist";
		}
		
		//正常な場合に、タスク登録確認画面に遷移する
		return "task-confirm-regist";
	}
	
	//タスク登録リクエスト（登録確認画面より）
	@PostMapping("/task-confirm-regist")
	public String confirmRegist(@Validated @ModelAttribute TaskRegistForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		//入力エラーがある場合には、タスク登録画面に戻す
		if(result.hasErrors()) {
			return "task-regist";
		}
		
		Task task = new Task();
		task.setTaskName(form.getTaskName());
		task.setLimitDate(form.getLimitDate());
		task.setStatusCode(form.getStatusCode());
		task.setRemarks(form.getRemarks());
		
		taskService.regist(task);
		
		//フラッシュスコープに完了メッセージを表示してリダイレクト
		redirectAttributes.addFlashAttribute("msg", "(タスク登録)");
		
		return "redirect:/task-complete";
	}

	
	
}

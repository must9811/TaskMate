package com.example.taskmate.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.taskmate.entity.Task;
import com.example.taskmate.entity.TaskSummary;

@Mapper
public interface TaskRepository {
	
	//一覧全件検索
	List<TaskSummary> selectListAll();
	
	void insert(@Param("task") Task task);
	
}

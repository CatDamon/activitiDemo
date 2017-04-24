package gdx.activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Test;

public class ActivitiTest02 {

	/**
	 * 获取默认流程引擎实力，会自动读取activiti.cfg.xml文件
	 * */
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	public static void main(String[] args) {
		
	}
	
	/**
	 * 部署
	 * */
	@Test
	public void deploy () {
		Deployment deploy = processEngine.getRepositoryService() // 获取部署相关Service
					.createDeployment() // 创建部署
					.addClasspathResource("diagrams/HelloWorld.bpmn")
					.addClasspathResource("diagrams/HelloWorld.png")
					.name("gdx")
					.deploy();
		System.out.println(deploy.getId());
		System.out.println(deploy.getName());
	}
	
	/**
	 * 启动流程实例
	 * */
	@Test
	public void start (){
		ProcessInstance instanceByKey = processEngine.getRuntimeService() // 获取运行时Service
				.startProcessInstanceByKey("myProcess");
		System.out.println("流程实例ID:" + instanceByKey.getId());
		System.out.println("流程定义ID:" + instanceByKey.getProcessDefinitionId());
				
	}
	
	/**
	 * 查询任务
	 * */
	@Test
	public void findTask () {
		List<Task> list = processEngine.getTaskService() //获取任务相关Service
					.createTaskQuery() //创建任务查询
					.taskAssignee("gdx") //指定某个人
					.list();
		for (Task task : list) {
			System.out.println("任务ID："+task.getId());
			System.out.println("任务名称："+task.getName());
			System.out.println("任务创建时间："+task.getCreateTime());
			System.out.println("任务委派人："+task.getAssignee());
			System.out.println("流程实例ID"+task.getProcessDefinitionId());
		}
	}
	
	/**
	 * 完成额任务
	 * */
	@Test
	public void complete () {
		processEngine.getTaskService().complete("10004"); //流程ID
	}
	
	
}

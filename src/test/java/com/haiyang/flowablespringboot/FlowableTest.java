package com.haiyang.flowablespringboot; /**
 * @Description:
 * @author: runoob
 * @email 18513330949@163.com
 * @date:
 * @version V1.0
 */

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: runoob
 * @email 18513330949@163.com
 * @date:
 * @version V1.0
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(classes = FlowableSpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlowableTest {
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private org.flowable.engine.TaskService taskService;

	@Autowired
	private org.flowable.engine.IdentityService identityService;

	@Test
	public void test1() {

	/*	try {
			File zipTemp = new File("D:/请假审批1.bpmn20.zip");
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipTemp));
			Deployment deployment = repositoryService
					.createDeployment()
					.addZipInputStream(zipInputStream)
					.deploy();
			log.info("部署成功:{}", deployment.getId());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
*/


		/*
		 * @Date: 2021/10/17 23:40
		 * Step 2: 查询部署的流程定义
		 */
	/*	List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave_approval").list();
		List<ProcessDefinition> pages = repositoryService.createProcessDefinitionQuery().processDefinitionKey("leave_approval").listPage(0, 30);*/

		/*
		System.out.println(JSONUtil.toJsonStr(list));
		System.out.println(JSONUtil.toJsonStr(pages));*/

		/*
		 * @Date: 2021/10/17 23:40
		 * Step 3: 启动流程，创建实例
		 */
/*		String processDefinitionKey = "leave_approval1";//流程定义的key,对应请假的流程图
		String businessKey = "zzzzzzzzzz";//业务代码，根据自己的业务用
		Map<String, Object> businessMap = new HashMap<>();//流程变量，可以自定义扩充
		businessMap.put("userId","张三11113");
		businessMap.put("orderId","00011111");
		businessMap.put("remark", "备注111111");

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, businessMap);
		log.info("启动成功:{}", processInstance.getId());
		*/


		/*
		 * @Date: 2021/10/17 23:40
		 * Step 5: 学生查询可以操作的任务,并完成任务
		 */
/*
		String candidateGroup = "1"; //候选组 xml文件里面的 flowable:candidateGroups="stu_group"
		List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).orderByTaskCreateTime().desc().list();

		System.out.println("学生任务1：" +JSONUtil.toJsonStr(taskList));
		for (Task task : taskList) {
			// 申领任务
			taskService.claim(task.getId(), "my");
			// 完成
			taskService.complete(task.getId());
		}
*/


		/*
		 * @Date: 2021/10/17 23:40
		 * Step 6: 老师查询可以操作的任务,并完成任务
		 */
		String candidateGroupTe = "2"; //候选组 xml文件里面的 flowable:candidateGroups="te_group"
		List<Task> taskListTe = taskService.createTaskQuery().taskCandidateGroup(candidateGroupTe).orderByTaskCreateTime().desc().list();
		System.out.println("老师的任务：" + JSONUtil.toJsonStr(taskListTe));
		for (Task task : taskListTe) {
			// 申领任务
			taskService.claim(task.getId(), "myte");
			// 完成
			Map<String, Object> variables = new HashMap<>();
			variables.put("command","agree"); //携带变量，用于网关流程的条件判定，这里的条件是同意
			taskService.addComment(task.getId(),task.getProcessInstanceId(),"已经批准了");  //添加评论
			taskService.complete(task.getId(), variables);
		}

	/*	String candidateGroupTe = "3"; //候选组 xml文件里面的 flowable:candidateGroups="te_group"
		List<Task> taskListTe = taskService.createTaskQuery().taskCandidateGroup(candidateGroupTe).orderByTaskCreateTime().desc().list();
		System.out.println("校长的任务：" + JSONUtil.toJsonStr(taskListTe));
		for (Task task : taskListTe) {
			// 申领任务
			taskService.claim(task.getId(), "myshcool");
			// 完成
			Map<String, Object> variables = new HashMap<>();
			variables.put("command","refuse"); //携带变量，用于网关流程的条件判定，这里的条件是同意
			taskService.complete(task.getId(), variables);
		}


		String candidateGroup = "1";
		List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup(candidateGroup).orderByTaskCreateTime().desc().list();
		System.out.println("学生任务1：" +JSONUtil.toJsonStr(taskList));*/


		//我发起的流程实例列表
/*		List<HistoricProcessInstance> list = historyService
				.createHistoricProcessInstanceQuery()
				.startedBy("张三11111")
				.orderByProcessInstanceStartTime()
				.asc()
				.list();
		System.out.println(JSONUtil.toJsonStr(list));*/

		/*
		 * @Date: 2021/10/18 0:17
		 * Step 7: 历史查询，因为一旦流程执行完毕，活动的数据都会被清空，上面查询的接口都查不到数据，但是提供历史查询接口
		 */
		// 历史流程实例
	    List<HistoricProcessInstance> historicProcessList = historyService.createHistoricProcessInstanceQuery().processDefinitionKey("leave_approval1").list();
		System.out.println(JSONUtil.toJsonStr(historicProcessList));
		// 历史任务
		List<HistoricTaskInstance> historicTaskList = historyService.createHistoricTaskInstanceQuery().processDefinitionKey("leave_approval1").orderByTaskCreateTime().asc().list();
		System.out.println(JSONUtil.toJsonStr(historicTaskList));


		// 实例历史变量 , 任务历史变量
		// historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId);
		// historyService.createHistoricVariableInstanceQuery().taskId(taskId);

		// *****************************************************分隔符********************************************************************
		// *****************************************************分隔符********************************************************************
		// 可能还需要的API
		// 移动任务，人为跳转任务
		// runtimeService.createChangeActivityStateBuilder().processInstanceId(processInstanceId)
		//       .moveActivityIdTo(currentActivityTaskId, newActivityTaskId).changeState();

		// 如果在数据库配置了分组和用户，还会用到
//		List<User> users = identityService.createUserQuery().list();    //用户查询，用户id对应xml 里面配置的用户
//		List<Group> groups = identityService.createGroupQuery().list(); //分组查询，分组id对应xml 里面配置的分组 如 stu_group，te_group 在表里是id的值





	}
}

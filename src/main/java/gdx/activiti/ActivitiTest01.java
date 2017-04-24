package gdx.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ActivitiTest01 {
	/**
     * 生成25张Activiti表
     */
    @Test
    public void testCreateTable() {
        // 引擎配置
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        pec.setJdbcDriver("com.mysql.jdbc.Driver");
        pec.setJdbcUrl("jdbc:mysql://localhost:3306/cms");
        pec.setJdbcUsername("root");
        pec.setJdbcPassword("");
         
        /**
         * false 不能自动创建表
         * create-drop 先删除表再创建表
         * true 自动创建和更新表  
         */
        pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
         
        // 获取流程引擎对象
        ProcessEngine processEngine=pec.buildProcessEngine();
    }
    public static void main(String[] args) {
    	ActivitiTest01 tct = new ActivitiTest01();
    	tct.testCreateTable();
	}
}

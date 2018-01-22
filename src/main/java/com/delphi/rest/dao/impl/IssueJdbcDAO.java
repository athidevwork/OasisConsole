/**
 * 
 */
package com.delphi.rest.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.delphi.rest.dao.IssueDAO;
import com.delphi.rest.entity.policy.PolicyDataType;
import com.delphi.rest.entity.policy.PolicyTermType;
import com.delphi.rest.entity.policy.PolicyTransactionType;
import com.delphi.rest.entity.policy.PolicyType;
import com.delphi.rest.util.OasisConsoleUtil;

/**
 * @author Athi
 *
 */
@Component("IssueJdbcDAO")
public class IssueJdbcDAO implements IssueDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
	static Logger LOGGER = LoggerFactory.getLogger(IssueJdbcDAO.class);

	static String POLICY_STMT = "select POLICY_PK,POLICY_NO,POLICY_CYCLE_CODE,POLICY_TYPE_CODE"+               
			",POL_CURR_STATUS_CODE,POL_CURR_REASON_CODE,PROCESS_STATUS_CODE,LEGACY_POLICY_NO" +              
			",INCEPTION_DATE,SID,SERIAL# SERIAL_NUMBER,LOGON_TIME,WIP_B,LOCK_TIME,LEGACY_ISSUE_COMPANY_FK,FORMATTED_POL_NO " +              
			"from policy where policy_no = upper(?)";
	
	static String POLICY_TERM_STMT = "select policy_term_history_pk term_pk, term_base_record_fk term_base_fk," +
			"base_record_b base_record,pol_rel_stat_type_code, policy_term_code term,substr(record_mode_code,1,9) record_mode_code," + 
			"official_record_fk official, transaction_log_fk trans, closing_trans_log_fk close," +
			"to_char(effective_from_date,'mm/dd/yyyy') effec_from,to_char(effective_to_date,'mm/dd/yyyy') effec_to," +
			"to_char(accounting_from_date,'mm/dd/yyyy') acct_from,to_char(accounting_to_date,'mm/dd/yyyy') acct_to," +
			"last_transaction_fk last_trans,issue_state_code,issue_company_entity_fk," +	
			"process_location_code,short_term_b,renewal_cycle_code,renewal_INDICATOR_code,peer_groups_code " +
			"from policy_term_history where policy_fk = ( select policy_pk from policy where policy_no = upper(?)) " +
			"order by 2,3 desc,1 asc";
	
	static String POLICY_TRANS_STMT = "select * from transaction_log " +
			"where policy_fk = ( select policy_pk from policy " +
			"where policy_no = upper(?)) order by transaction_log_pk desc";
	
	@Override
	public PolicyDataType getPolicyData(String env, String policy) {
		LOGGER.debug("Running get policy for policy " + policy + " in env " + env);
		OasisConsoleUtil.setPropsForEnv(env);
		
		PolicyDataType policyData = new PolicyDataType();
		
		LOGGER.debug("*******Policy Data***********");
        List<PolicyType> list = jdbcTemplate.query(POLICY_STMT, new String[] { policy }, 
				(rs, rowNum) -> new PolicyType(rs.getString("POLICY_PK"), 
						rs.getString("POLICY_NO"), rs.getString("POLICY_CYCLE_CODE"),
						rs.getString("POLICY_TYPE_CODE"), rs.getString("POL_CURR_STATUS_CODE"),
						rs.getString("POL_CURR_REASON_CODE"), rs.getString("PROCESS_STATUS_CODE"),
						rs.getString("LEGACY_POLICY_NO"), rs.getString("INCEPTION_DATE"),
						rs.getString("SID"), rs.getString("SERIAL_NUMBER"),
						rs.getString("LOGON_TIME"), rs.getString("WIP_B"),
						rs.getString("LOCK_TIME"), rs.getString("LEGACY_ISSUE_COMPANY_FK"),
						rs.getString("FORMATTED_POL_NO")));
		list.forEach(System.out::println);
		policyData.setPolicy(list);
		
		LOGGER.debug("*******Policy Term Data***********");
		List<PolicyTermType> termList = jdbcTemplate.query(POLICY_TERM_STMT, new String[] { policy }, 
				(rs, rowNum) -> new PolicyTermType(rs.getString("term_pk"), 
						rs.getString("term_base_fk"), rs.getString("base_record"),
						rs.getString("pol_rel_stat_type_code"), rs.getString("term"),
						rs.getString("record_mode_code"), rs.getString("official"),
						rs.getString("trans"), rs.getString("close"),
						rs.getString("effec_from"), rs.getString("effec_to"),
						rs.getString("acct_from"), rs.getString("acct_to"),
						rs.getString("last_trans"), rs.getString("issue_state_code"),
						rs.getString("issue_company_entity_fk"), rs.getString("process_location_code"),
						rs.getString("short_term_b"), rs.getString("renewal_cycle_code"),
						rs.getString("renewal_INDICATOR_code"), rs.getString("peer_groups_code")));
		termList.forEach(System.out::println);
		policyData.setPolicyTerm(termList);		
		
		LOGGER.debug("*******Policy Transaction Data***********");
		List<PolicyTransactionType> transList = jdbcTemplate.query(POLICY_TRANS_STMT, new String[] { policy }, 
				(rs, rowNum) -> new PolicyTransactionType(rs.getString("TRANSACTION_LOG_PK"), 
						rs.getString("POLICY_FK"), rs.getString("TRANSACTION_TYPE_CODE"),
						rs.getString("TRANSACTION_CODE"), rs.getString("TRANSACTION_STATUS_CODE"),
						rs.getString("ENDORSEMENT_QUOTE_ID"), rs.getString("EFFECTIVE_FROM_DATE"),
						rs.getString("ACCOUNTING_DATE"), rs.getString("ENDORSEMENT_CODE"),
						rs.getString("COMMENTS")));
		transList.forEach(System.out::println);
		policyData.setPolicyTransaction(transList);	
		
		return policyData;
	}
}

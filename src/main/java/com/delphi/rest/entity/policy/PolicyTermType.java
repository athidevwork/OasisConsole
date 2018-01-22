/**
 * 
 */
package com.delphi.rest.entity.policy;

/**
 * @author Athi
 *
 */
public class PolicyTermType {
	String term_pk, term_base_fk,base_record,pol_rel_stat_type_code,term,record_mode_code; 
	String official, trans, close, effec_from, effec_to, acct_from, acct_to;
	String last_trans,issue_state_code,issue_company_entity_fk,process_location_code;
	String short_term_b,renewal_cycle_code,renewal_INDICATOR_code,peer_groups_code;
	
	public PolicyTermType(String term_pk, String term_base_fk, String base_record, String pol_rel_stat_type_code,
			String term, String record_mode_code, String official, String trans, String close, String effec_from,
			String effec_to, String acct_from, String acct_to, String last_trans, String issue_state_code,
			String issue_company_entity_fk, String process_location_code, String short_term_b,
			String renewal_cycle_code, String renewal_INDICATOR_code, String peer_groups_code) {
		super();
		this.term_pk = term_pk;
		this.term_base_fk = term_base_fk;
		this.base_record = base_record;
		this.pol_rel_stat_type_code = pol_rel_stat_type_code;
		this.term = term;
		this.record_mode_code = record_mode_code;
		this.official = official;
		this.trans = trans;
		this.close = close;
		this.effec_from = effec_from;
		this.effec_to = effec_to;
		this.acct_from = acct_from;
		this.acct_to = acct_to;
		this.last_trans = last_trans;
		this.issue_state_code = issue_state_code;
		this.issue_company_entity_fk = issue_company_entity_fk;
		this.process_location_code = process_location_code;
		this.short_term_b = short_term_b;
		this.renewal_cycle_code = renewal_cycle_code;
		this.renewal_INDICATOR_code = renewal_INDICATOR_code;
		this.peer_groups_code = peer_groups_code;
	}
	public String getTerm_pk() {
		return term_pk;
	}
	public void setTerm_pk(String term_pk) {
		this.term_pk = term_pk;
	}
	public String getTerm_base_fk() {
		return term_base_fk;
	}
	public void setTerm_base_fk(String term_base_fk) {
		this.term_base_fk = term_base_fk;
	}
	public String getBase_record() {
		return base_record;
	}
	public void setBase_record(String base_record) {
		this.base_record = base_record;
	}
	public String getPol_rel_stat_type_code() {
		return pol_rel_stat_type_code;
	}
	public void setPol_rel_stat_type_code(String pol_rel_stat_type_code) {
		this.pol_rel_stat_type_code = pol_rel_stat_type_code;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getRecord_mode_code() {
		return record_mode_code;
	}
	public void setRecord_mode_code(String record_mode_code) {
		this.record_mode_code = record_mode_code;
	}
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public String getEffec_from() {
		return effec_from;
	}
	public void setEffec_from(String effec_from) {
		this.effec_from = effec_from;
	}
	public String getEffec_to() {
		return effec_to;
	}
	public void setEffec_to(String effec_to) {
		this.effec_to = effec_to;
	}
	public String getAcct_from() {
		return acct_from;
	}
	public void setAcct_from(String acct_from) {
		this.acct_from = acct_from;
	}
	public String getAcct_to() {
		return acct_to;
	}
	public void setAcct_to(String acct_to) {
		this.acct_to = acct_to;
	}
	public String getLast_trans() {
		return last_trans;
	}
	public void setLast_trans(String last_trans) {
		this.last_trans = last_trans;
	}
	public String getIssue_state_code() {
		return issue_state_code;
	}
	public void setIssue_state_code(String issue_state_code) {
		this.issue_state_code = issue_state_code;
	}
	public String getIssue_company_entity_fk() {
		return issue_company_entity_fk;
	}
	public void setIssue_company_entity_fk(String issue_company_entity_fk) {
		this.issue_company_entity_fk = issue_company_entity_fk;
	}
	public String getProcess_location_code() {
		return process_location_code;
	}
	public void setProcess_location_code(String process_location_code) {
		this.process_location_code = process_location_code;
	}
	public String getShort_term_b() {
		return short_term_b;
	}
	public void setShort_term_b(String short_term_b) {
		this.short_term_b = short_term_b;
	}
	public String getRenewal_cycle_code() {
		return renewal_cycle_code;
	}
	public void setRenewal_cycle_code(String renewal_cycle_code) {
		this.renewal_cycle_code = renewal_cycle_code;
	}
	public String getRenewal_INDICATOR_code() {
		return renewal_INDICATOR_code;
	}
	public void setRenewal_INDICATOR_code(String renewal_INDICATOR_code) {
		this.renewal_INDICATOR_code = renewal_INDICATOR_code;
	}
	public String getPeer_groups_code() {
		return peer_groups_code;
	}
	public void setPeer_groups_code(String peer_groups_code) {
		this.peer_groups_code = peer_groups_code;
	}
	@Override
	public String toString() {
		return "PolicyTermType [term_pk=" + term_pk + ", term_base_fk=" + term_base_fk + ", base_record=" + base_record
				+ ", pol_rel_stat_type_code=" + pol_rel_stat_type_code + ", term=" + term + ", record_mode_code="
				+ record_mode_code + ", official=" + official + ", trans=" + trans + ", close=" + close
				+ ", effec_from=" + effec_from + ", effec_to=" + effec_to + ", acct_from=" + acct_from + ", acct_to="
				+ acct_to + ", last_trans=" + last_trans + ", issue_state_code=" + issue_state_code
				+ ", issue_company_entity_fk=" + issue_company_entity_fk + ", process_location_code="
				+ process_location_code + ", short_term_b=" + short_term_b + ", renewal_cycle_code="
				+ renewal_cycle_code + ", renewal_INDICATOR_code=" + renewal_INDICATOR_code + ", peer_groups_code="
				+ peer_groups_code + "]";
	}
}

/**
 * 
 */
package com.delphi.rest.entity.policy;

import java.util.List;

/**
 * @author Athi
 *
 */
public class PolicyDataType {
	List<PolicyType> policy;
	List<PolicyTermType> policyTerm;
	List<PolicyTransactionType> policyTransaction;
	public List<PolicyType> getPolicy() {
		return policy;
	}
	public void setPolicy(List<PolicyType> policy) {
		this.policy = policy;
	}
	public List<PolicyTermType> getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(List<PolicyTermType> policyTerm) {
		this.policyTerm = policyTerm;
	}
	public List<PolicyTransactionType> getPolicyTransaction() {
		return policyTransaction;
	}
	public void setPolicyTransaction(List<PolicyTransactionType> policyTransaction) {
		this.policyTransaction = policyTransaction;
	}	
}

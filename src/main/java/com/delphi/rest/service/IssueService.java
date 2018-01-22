/**
 * 
 */
package com.delphi.rest.service;

import com.delphi.rest.entity.policy.PolicyDataType;

/**
 * @author Athi
 *
 */
public interface IssueService {
	public PolicyDataType getPolicyData(String env, String policy);
}

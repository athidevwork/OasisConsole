/**
 * 
 */
package com.delphi.rest.dao;

import com.delphi.rest.entity.policy.PolicyDataType;

/**
 * @author Athi
 *
 */
public interface IssueDAO {
	public PolicyDataType getPolicyData(String env, String policy);
}

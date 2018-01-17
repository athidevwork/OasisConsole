package com.delphi.rest.dao;

import com.delphi.rest.entity.OasisConfigType;

public interface OasisConsoleDAO {
	//public OasisConfigType getConfig();
	public OasisConfigType getConfig(String env);
}
